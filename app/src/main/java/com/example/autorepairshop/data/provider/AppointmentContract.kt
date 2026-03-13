package com.example.autorepairshop.data.provider

import android.net.Uri
import android.provider.BaseColumns

object AppointmentContract {
    const val AUTHORITY = "com.example.autorepairshop.provider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/appointments")
    const val TABLE_NAME = "appointments"
    const val COL_ID = BaseColumns._ID
    const val COL_SERVICE_NAME = "serviceName"
    const val COL_DATE = "date"
    const val COL_TIME = "time"
    const val COL_STATUS = "status"
    const val COL_PRICE = "price"
}