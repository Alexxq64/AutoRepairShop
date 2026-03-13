package com.example.autorepairshop.model

// Модель автомастерской
data class Workshop(
    val id: Long,
    val address: String,
    val phone: String,
    val latitude: Double?,
    val longitude: Double?
)