package com.example.autorepairshop.model

// Модель записи на услугу
data class Appointment(
    val id: Long,
    val serviceName: String,
    val date: String,
    val time: String,
    val status: String,
    val price: Int
)