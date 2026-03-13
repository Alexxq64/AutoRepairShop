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
import com.example.autorepairshop.data.provider.WorkshopContract
import com.example.autorepairshop.model.Workshop
import com.example.autorepairshop.ui.adapters.WorkshopsAdapter

class WorkshopsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WorkshopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workshops, container, false)
        recyclerView = view.findViewById(R.id.recyclerWorkshops)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WorkshopsAdapter(emptyList(), requireContext())
        recyclerView.adapter = adapter

        loadWorkshops()

        requireContext().contentResolver.registerContentObserver(
            WorkshopContract.CONTENT_URI,
            true,
            object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean) {
                    loadWorkshops()
                }
            }
        )

        return view
    }

    private fun loadWorkshops() {
        val workshops = mutableListOf<Workshop>()
        val cursor = requireContext().contentResolver.query(
            WorkshopContract.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val idIndex = it.getColumnIndex(WorkshopContract.COL_ID)
            val addressIndex = it.getColumnIndex(WorkshopContract.COL_ADDRESS)
            val phoneIndex = it.getColumnIndex(WorkshopContract.COL_PHONE)
            val latIndex = it.getColumnIndex(WorkshopContract.COL_LATITUDE)
            val lonIndex = it.getColumnIndex(WorkshopContract.COL_LONGITUDE)

            while (it.moveToNext()) {
                val workshop = Workshop(
                    id = it.getLong(idIndex),
                    address = it.getString(addressIndex),
                    phone = it.getString(phoneIndex),
                    latitude = if (it.isNull(latIndex)) null else it.getDouble(latIndex),
                    longitude = if (it.isNull(lonIndex)) null else it.getDouble(lonIndex)
                )
                workshops.add(workshop)
            }
        }
        adapter.updateList(workshops)
    }
}