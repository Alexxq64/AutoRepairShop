package com.example.autorepairshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autorepairshop.R
import com.example.autorepairshop.model.BlogPost

class BlogAdapter(
    private var posts: List<BlogPost>
) : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    class BlogViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivBlogImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvBlogTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvBlogDate)
        val tvPreview: TextView = itemView.findViewById(R.id.tvBlogPreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false) as ViewGroup
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val post = posts[position]
        holder.tvTitle.text = post.title
        holder.tvDate.text = post.date
        holder.tvPreview.text = post.content.take(100) + "..."
        // Заглушка для изображения
        holder.ivImage.setImageResource(android.R.drawable.ic_menu_report_image)
    }

    override fun getItemCount(): Int = posts.size

    fun updateList(newList: List<BlogPost>) {
        posts = newList
        notifyDataSetChanged()
    }
}