package com.example.autorepairshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autorepairshop.R
import com.example.autorepairshop.model.Service

class ServicesAdapter(
    private var services: List<Service>
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvServiceName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvServicePrice)
        val tvDuration: TextView = itemView.findViewById(R.id.tvServiceDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service, parent, false) as ViewGroup
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.tvName.text = service.name
        holder.tvPrice.text = "${service.price} ₽"
        holder.tvDuration.text = service.duration
    }

    override fun getItemCount(): Int = services.size

    fun updateList(newList: List<Service>) {
        services = newList
        notifyDataSetChanged()
    }
}