package com.example.autorepairshop.data.provider

import android.net.Uri
import android.provider.BaseColumns

object ServiceContract {
    const val AUTHORITY = "com.example.autorepairshop.provider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/services")
    const val TABLE_NAME = "services"
    const val COL_ID = BaseColumns._ID
    const val COL_NAME = "name"
    const val COL_PRICE = "price"
    const val COL_DURATION = "duration"
}