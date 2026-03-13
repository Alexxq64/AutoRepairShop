package com.example.autorepairshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autorepairshop.R
import com.example.autorepairshop.model.Appointment

class AppointmentsAdapter(
    private var appointments: List<Appointment>
) : RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder>() {

    class AppointmentViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val tvService: TextView = itemView.findViewById(R.id.tvAppointmentService)
        val tvDate: TextView = itemView.findViewById(R.id.tvAppointmentDate)
        val tvTime: TextView = itemView.findViewById(R.id.tvAppointmentTime)
        val tvStatus: TextView = itemView.findViewById(R.id.tvAppointmentStatus)
        val tvPrice: TextView = itemView.findViewById(R.id.tvAppointmentPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false) as ViewGroup
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.tvService.text = appointment.serviceName
        holder.tvDate.text = appointment.date
        holder.tvTime.text = appointment.time
        holder.tvStatus.text = appointment.status
        holder.tvPrice.text = "${appointment.price} ₽"
    }

    override fun getItemCount(): Int = appointments.size

    fun updateList(newList: List<Appointment>) {
        appointments = newList
        notifyDataSetChanged()
    }
}