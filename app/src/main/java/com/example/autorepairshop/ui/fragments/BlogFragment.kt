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
import com.example.autorepairshop.data.provider.BlogPostContract
import com.example.autorepairshop.model.BlogPost
import com.example.autorepairshop.ui.adapters.BlogAdapter

class BlogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BlogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        recyclerView = view.findViewById(R.id.recyclerBlog)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = BlogAdapter(emptyList())
        recyclerView.adapter = adapter

        loadPosts()

        requireContext().contentResolver.registerContentObserver(
            BlogPostContract.CONTENT_URI,
            true,
            object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean) {
                    loadPosts()
                }
            }
        )

        return view
    }

    private fun loadPosts() {
        val posts = mutableListOf<BlogPost>()
        val cursor = requireContext().contentResolver.query(
            BlogPostContract.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use {
            val idIndex = it.getColumnIndex(BlogPostContract.COL_ID)
            val titleIndex = it.getColumnIndex(BlogPostContract.COL_TITLE)
            val contentIndex = it.getColumnIndex(BlogPostContract.COL_CONTENT)
            val dateIndex = it.getColumnIndex(BlogPostContract.COL_DATE)
            val imageIndex = it.getColumnIndex(BlogPostContract.COL_IMAGE_URL)

            while (it.moveToNext()) {
                val post = BlogPost(
                    id = it.getLong(idIndex),
                    title = it.getString(titleIndex),
                    content = it.getString(contentIndex),
                    date = it.getString(dateIndex),
                    imageUrl = it.getString(imageIndex)
                )
                posts.add(post)
            }
        }
        adapter.updateList(posts)
    }
}