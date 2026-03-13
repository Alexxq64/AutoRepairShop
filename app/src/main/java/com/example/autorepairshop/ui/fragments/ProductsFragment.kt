package com.example.autorepairshop.ui.fragments

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autorepairshop.R
import com.example.autorepairshop.data.provider.ProductContract
import com.example.autorepairshop.model.Product
import com.example.autorepairshop.ui.adapters.ProductsAdapter

class ProductsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        recyclerView = view.findViewById(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ProductsAdapter(emptyList())
        recyclerView.adapter = adapter

        loadProducts()

        requireContext().contentResolver.registerContentObserver(
            ProductContract.CONTENT_URI,
            true,
            object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean) {
                    loadProducts()
                }
            }
        )

        return view
    }

    private fun loadProducts() {
        val products = mutableListOf<Product>()
        val cursor = requireContext().contentResolver.query(
            ProductContract.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val idIndex = it.getColumnIndex(ProductContract.COL_ID)
            val nameIndex = it.getColumnIndex(ProductContract.COL_NAME)
            val descIndex = it.getColumnIndex(ProductContract.COL_DESCRIPTION)
            val priceIndex = it.getColumnIndex(ProductContract.COL_PRICE)
            val imageIndex = it.getColumnIndex(ProductContract.COL_IMAGE_URL)

            while (it.moveToNext()) {
                val product = Product(
                    id = it.getLong(idIndex),
                    name = it.getString(nameIndex),
                    description = it.getString(descIndex),
                    price = it.getInt(priceIndex),
                    imageUrl = it.getString(imageIndex)
                )
                products.add(product)
            }
        }
        adapter.updateList(products)
    }
}