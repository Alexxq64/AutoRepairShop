package com.example.autorepairshop.data.provider

import android.net.Uri
import android.provider.BaseColumns

object WorkshopContract {
    const val AUTHORITY = "com.example.autorepairshop.provider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/workshops")
    const val TABLE_NAME = "workshops"
    const val COL_ID = BaseColumns._ID
    const val COL_ADDRESS = "address"
    const val COL_PHONE = "phone"
    const val COL_LATITUDE = "latitude"
    const val COL_LONGITUDE = "longitude"
}