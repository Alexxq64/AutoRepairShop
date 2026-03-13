package com.example.autorepairshop.model

// Модель услуги
data class Service(
    val id: Long,
    val name: String,
    val price: Int,
    val duration: String
)