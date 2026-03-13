package com.example.autorepairshop.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autorepairshop.R
import com.example.autorepairshop.model.Workshop

class WorkshopsAdapter(
    private var workshops: List<Workshop>,
    private val context: android.content.Context
) : RecyclerView.Adapter<WorkshopsAdapter.WorkshopViewHolder>() {

    class WorkshopViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val tvAddress: TextView = itemView.findViewById(R.id.tvWorkshopAddress)
        val tvPhone: TextView = itemView.findViewById(R.id.tvWorkshopPhone)
        val btnCall: Button = itemView.findViewById(R.id.btnCall)
        val btnMap: Button = itemView.findViewById(R.id.btnMap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkshopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workshop, parent, false) as ViewGroup
        return WorkshopViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkshopViewHolder, position: Int) {
        val workshop = workshops[position]
        holder.tvAddress.text = workshop.address
        holder.tvPhone.text = workshop.phone

        holder.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${workshop.phone}"))
            context.startActivity(intent)
        }

        holder.btnMap.setOnClickListener {
            val uri = if (workshop.latitude != null && workshop.longitude != null) {
                Uri.parse("geo:${workshop.latitude},${workshop.longitude}?q=${workshop.address}")
            } else {
                Uri.parse("geo:0,0?q=${workshop.address}")
            }
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = workshops.size

    fun updateList(newList: List<Workshop>) {
        workshops = newList
        notifyDataSetChanged()
    }
}