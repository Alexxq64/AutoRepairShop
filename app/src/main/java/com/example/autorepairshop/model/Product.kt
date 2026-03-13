package com.example.autorepairshop.model

// Модель товара
data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String
)