package com.example.autorepairshop.model

// Модель статьи
data class BlogPost(
    val id: Long,
    val title: String,
    val content: String,
    val date: String,
    val imageUrl: String
)