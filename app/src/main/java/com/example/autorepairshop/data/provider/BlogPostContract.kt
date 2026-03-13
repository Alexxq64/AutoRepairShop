package com.example.autorepairshop.data.provider

import android.net.Uri
import android.provider.BaseColumns

object BlogPostContract {
    const val AUTHORITY = "com.example.autorepairshop.provider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/blogposts")
    const val TABLE_NAME = "blogposts"
    const val COL_ID = BaseColumns._ID
    const val COL_TITLE = "title"
    const val COL_CONTENT = "content"
    const val COL_DATE = "date"
    const val COL_IMAGE_URL = "imageUrl"
}