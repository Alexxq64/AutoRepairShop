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
import com.example.autorepairshop.data.provider.AppointmentContract
import com.example.autorepairshop.model.Appointment
import com.example.autorepairshop.ui.adapters.AppointmentsAdapter

class AppointmentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AppointmentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_appointments, container, false)
        recyclerView = view.findViewById(R.id.recyclerAppointments)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = AppointmentsAdapter(emptyList())
        recyclerView.adapter = adapter

        loadAppointments()

        requireContext().contentResolver.registerContentObserver(
            AppointmentContract.CONTENT_URI,
            true,
            object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean) {
                    loadAppointments()
                }
            }
        )

        return view
    }

    private fun loadAppointments() {
        val appointments = mutableListOf<Appointment>()
        val cursor = requireContext().contentResolver.query(
            AppointmentContract.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val idIndex = it.getColumnIndex(AppointmentContract.COL_ID)
            val serviceNameIndex = it.getColumnIndex(AppointmentContract.COL_SERVICE_NAME)
            val dateIndex = it.getColumnIndex(AppointmentContract.COL_DATE)
            val timeIndex = it.getColumnIndex(AppointmentContract.COL_TIME)
            val statusIndex = it.getColumnIndex(AppointmentContract.COL_STATUS)
            val priceIndex = it.getColumnIndex(AppointmentContract.COL_PRICE)

            while (it.moveToNext()) {
                val appointment = Appointment(
                    id = it.getLong(idIndex),
                    serviceName = it.getString(serviceNameIndex),
                    date = it.getString(dateIndex),
                    time = it.getString(timeIndex),
                    status = it.getString(statusIndex),
                    price = it.getInt(priceIndex)
                )
                appointments.add(appointment)
            }
        }
        adapter.updateList(appointments)
    }
}