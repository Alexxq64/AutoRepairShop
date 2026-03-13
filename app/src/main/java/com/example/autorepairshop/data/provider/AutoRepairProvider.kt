package com.example.autorepairshop.data.provider

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.provider.BaseColumns
import com.example.autorepairshop.data.database.DatabaseHelper

class AutoRepairProvider : ContentProvider() {

    private lateinit var dbHelper: DatabaseHelper
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        // Appointment
        addURI(AppointmentContract.AUTHORITY, "appointments", APPOINTMENTS)
        addURI(AppointmentContract.AUTHORITY, "appointments/#", APPOINTMENT_ID)
        // Service
        addURI(ServiceContract.AUTHORITY, "services", SERVICES)
        addURI(ServiceContract.AUTHORITY, "services/#", SERVICE_ID)
        // Product
        addURI(ProductContract.AUTHORITY, "products", PRODUCTS)
        addURI(ProductContract.AUTHORITY, "products/#", PRODUCT_ID)
        // BlogPost
        addURI(BlogPostContract.AUTHORITY, "blogposts", BLOGPOSTS)
        addURI(BlogPostContract.AUTHORITY, "blogposts/#", BLOGPOST_ID)
        // Workshop
        addURI(WorkshopContract.AUTHORITY, "workshops", WORKSHOPS)
        addURI(WorkshopContract.AUTHORITY, "workshops/#", WORKSHOP_ID)
    }

    override fun onCreate(): Boolean {
        dbHelper = DatabaseHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        val cursor = when (val match = uriMatcher.match(uri)) {
            APPOINTMENTS -> db.query(
                AppointmentContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder
            )
            APPOINTMENT_ID -> {
                val id = uri.lastPathSegment
                db.query(
                    AppointmentContract.TABLE_NAME,
                    projection,
                    "${BaseColumns._ID} = ?",
                    arrayOf(id),
                    null, null, sortOrder
                )
            }
            SERVICES -> db.query(
                ServiceContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder
            )
            SERVICE_ID -> {
                val id = uri.lastPathSegment
                db.query(
                    ServiceContract.TABLE_NAME,
                    projection,
                    "${BaseColumns._ID} = ?",
                    arrayOf(id),
                    null, null, sortOrder
                )
            }
            PRODUCTS -> db.query(
                ProductContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder
            )
            PRODUCT_ID -> {
                val id = uri.lastPathSegment
                db.query(
                    ProductContract.TABLE_NAME,
                    projection,
                    "${BaseColumns._ID} = ?",
                    arrayOf(id),
                    null, null, sortOrder
                )
            }
            BLOGPOSTS -> db.query(
                BlogPostContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder
            )
            BLOGPOST_ID -> {
                val id = uri.lastPathSegment
                db.query(
                    BlogPostContract.TABLE_NAME,
                    projection,
                    "${BaseColumns._ID} = ?",
                    arrayOf(id),
                    null, null, sortOrder
                )
            }
            WORKSHOPS -> db.query(
                WorkshopContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder
            )
            WORKSHOP_ID -> {
                val id = uri.lastPathSegment
                db.query(
                    WorkshopContract.TABLE_NAME,
                    projection,
                    "${BaseColumns._ID} = ?",
                    arrayOf(id),
                    null, null, sortOrder
                )
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        cursor?.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val id = when (val match = uriMatcher.match(uri)) {
            APPOINTMENTS -> db.insert(AppointmentContract.TABLE_NAME, null, values)
            SERVICES -> db.insert(ServiceContract.TABLE_NAME, null, values)
            PRODUCTS -> db.insert(ProductContract.TABLE_NAME, null, values)
            BLOGPOSTS -> db.insert(BlogPostContract.TABLE_NAME, null, values)
            WORKSHOPS -> db.insert(WorkshopContract.TABLE_NAME, null, values)
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (id <= 0) throw SQLException("Failed to insert row")
        val newUri = ContentUris.withAppendedId(uri, id)
        context!!.contentResolver.notifyChange(newUri, null)
        return newUri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val count = when (val match = uriMatcher.match(uri)) {
            APPOINTMENTS -> db.delete(AppointmentContract.TABLE_NAME, selection, selectionArgs)
            APPOINTMENT_ID -> {
                val id = uri.lastPathSegment
                db.delete(AppointmentContract.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            SERVICES -> db.delete(ServiceContract.TABLE_NAME, selection, selectionArgs)
            SERVICE_ID -> {
                val id = uri.lastPathSegment
                db.delete(ServiceContract.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            PRODUCTS -> db.delete(ProductContract.TABLE_NAME, selection, selectionArgs)
            PRODUCT_ID -> {
                val id = uri.lastPathSegment
                db.delete(ProductContract.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            BLOGPOSTS -> db.delete(BlogPostContract.TABLE_NAME, selection, selectionArgs)
            BLOGPOST_ID -> {
                val id = uri.lastPathSegment
                db.delete(BlogPostContract.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            WORKSHOPS -> db.delete(WorkshopContract.TABLE_NAME, selection, selectionArgs)
            WORKSHOP_ID -> {
                val id = uri.lastPathSegment
                db.delete(WorkshopContract.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (count > 0) context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val count = when (val match = uriMatcher.match(uri)) {
            APPOINTMENTS -> db.update(AppointmentContract.TABLE_NAME, values, selection, selectionArgs)
            APPOINTMENT_ID -> {
                val id = uri.lastPathSegment
                db.update(AppointmentContract.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            SERVICES -> db.update(ServiceContract.TABLE_NAME, values, selection, selectionArgs)
            SERVICE_ID -> {
                val id = uri.lastPathSegment
                db.update(ServiceContract.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            PRODUCTS -> db.update(ProductContract.TABLE_NAME, values, selection, selectionArgs)
            PRODUCT_ID -> {
                val id = uri.lastPathSegment
                db.update(ProductContract.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            BLOGPOSTS -> db.update(BlogPostContract.TABLE_NAME, values, selection, selectionArgs)
            BLOGPOST_ID -> {
                val id = uri.lastPathSegment
                db.update(BlogPostContract.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            WORKSHOPS -> db.update(WorkshopContract.TABLE_NAME, values, selection, selectionArgs)
            WORKSHOP_ID -> {
                val id = uri.lastPathSegment
                db.update(WorkshopContract.TABLE_NAME, values, "${BaseColumns._ID} = ?", arrayOf(id))
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (count > 0) context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String {
        return when (val match = uriMatcher.match(uri)) {
            APPOINTMENTS -> "vnd.android.cursor.dir/vnd.${AppointmentContract.AUTHORITY}.appointments"
            APPOINTMENT_ID -> "vnd.android.cursor.item/vnd.${AppointmentContract.AUTHORITY}.appointments"
            SERVICES -> "vnd.android.cursor.dir/vnd.${ServiceContract.AUTHORITY}.services"
            SERVICE_ID -> "vnd.android.cursor.item/vnd.${ServiceContract.AUTHORITY}.services"
            PRODUCTS -> "vnd.android.cursor.dir/vnd.${ProductContract.AUTHORITY}.products"
            PRODUCT_ID -> "vnd.android.cursor.item/vnd.${ProductContract.AUTHORITY}.products"
            BLOGPOSTS -> "vnd.android.cursor.dir/vnd.${BlogPostContract.AUTHORITY}.blogposts"
            BLOGPOST_ID -> "vnd.android.cursor.item/vnd.${BlogPostContract.AUTHORITY}.blogposts"
            WORKSHOPS -> "vnd.android.cursor.dir/vnd.${WorkshopContract.AUTHORITY}.workshops"
            WORKSHOP_ID -> "vnd.android.cursor.item/vnd.${WorkshopContract.AUTHORITY}.workshops"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    companion object {
        private const val APPOINTMENTS = 1
        private const val APPOINTMENT_ID = 2
        private const val SERVICES = 3
        private const val SERVICE_ID = 4
        private const val PRODUCTS = 5
        private const val PRODUCT_ID = 6
        private const val BLOGPOSTS = 7
        private const val BLOGPOST_ID = 8
        private const val WORKSHOPS = 9
        private const val WORKSHOP_ID = 10
    }
}