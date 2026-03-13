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
import com.example.autorepairshop.data.provider.ServiceContract
import com.example.autorepairshop.model.Service
import com.example.autorepairshop.ui.adapters.ServicesAdapter

class ServicesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ServicesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services, container, false)
        recyclerView = view.findViewById(R.id.recyclerServices)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ServicesAdapter(emptyList())
        recyclerView.adapter = adapter

        loadServices()

        requireContext().contentResolver.registerContentObserver(
            ServiceContract.CONTENT_URI,
            true,
            object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean) {
                    loadServices()
                }
            }
        )

        return view
    }

    private fun loadServices() {
        val services = mutableListOf<Service>()
        val cursor = requireContext().contentResolver.query(
            ServiceContract.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val idIndex = it.getColumnIndex(ServiceContract.COL_ID)
            val nameIndex = it.getColumnIndex(ServiceContract.COL_NAME)
            val priceIndex = it.getColumnIndex(ServiceContract.COL_PRICE)
            val durationIndex = it.getColumnIndex(ServiceContract.COL_DURATION)

            while (it.moveToNext()) {
                val service = Service(
                    id = it.getLong(idIndex),
                    name = it.getString(nameIndex),
                    price = it.getInt(priceIndex),
                    duration = it.getString(durationIndex)
                )
                services.add(service)
            }
        }
        adapter.updateList(services)
    }
}