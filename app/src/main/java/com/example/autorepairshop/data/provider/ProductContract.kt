package com.example.autorepairshop.data.provider

import android.net.Uri
import android.provider.BaseColumns

object ProductContract {
    const val AUTHORITY = "com.example.autorepairshop.provider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/products")
    const val TABLE_NAME = "products"
    const val COL_ID = BaseColumns._ID
    const val COL_NAME = "name"
    const val COL_DESCRIPTION = "description"
    const val COL_PRICE = "price"
    const val COL_IMAGE_URL = "imageUrl"
}