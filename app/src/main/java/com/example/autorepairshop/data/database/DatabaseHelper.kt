package com.example.autorepairshop.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.autorepairshop.data.provider.AppointmentContract
import com.example.autorepairshop.data.provider.BlogPostContract
import com.example.autorepairshop.data.provider.ProductContract
import com.example.autorepairshop.data.provider.ServiceContract
import com.example.autorepairshop.data.provider.WorkshopContract

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "autorepair.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Таблица записей
        db.execSQL("""
            CREATE TABLE ${AppointmentContract.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${AppointmentContract.COL_SERVICE_NAME} TEXT NOT NULL,
                ${AppointmentContract.COL_DATE} TEXT NOT NULL,
                ${AppointmentContract.COL_TIME} TEXT NOT NULL,
                ${AppointmentContract.COL_STATUS} TEXT NOT NULL,
                ${AppointmentContract.COL_PRICE} INTEGER NOT NULL
            )
        """.trimIndent())

        // Таблица услуг
        db.execSQL("""
            CREATE TABLE ${ServiceContract.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${ServiceContract.COL_NAME} TEXT NOT NULL,
                ${ServiceContract.COL_PRICE} INTEGER NOT NULL,
                ${ServiceContract.COL_DURATION} TEXT
            )
        """.trimIndent())

        // Таблица товаров
        db.execSQL("""
            CREATE TABLE ${ProductContract.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${ProductContract.COL_NAME} TEXT NOT NULL,
                ${ProductContract.COL_DESCRIPTION} TEXT NOT NULL,
                ${ProductContract.COL_PRICE} INTEGER NOT NULL,
                ${ProductContract.COL_IMAGE_URL} TEXT NOT NULL
            )
        """.trimIndent())

        // Таблица статей
        db.execSQL("""
            CREATE TABLE ${BlogPostContract.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${BlogPostContract.COL_TITLE} TEXT NOT NULL,
                ${BlogPostContract.COL_CONTENT} TEXT NOT NULL,
                ${BlogPostContract.COL_DATE} TEXT NOT NULL,
                ${BlogPostContract.COL_IMAGE_URL} TEXT NOT NULL
            )
        """.trimIndent())

        // Таблица мастерских
        db.execSQL("""
            CREATE TABLE ${WorkshopContract.TABLE_NAME} (
                ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${WorkshopContract.COL_ADDRESS} TEXT NOT NULL,
                ${WorkshopContract.COL_PHONE} TEXT NOT NULL,
                ${WorkshopContract.COL_LATITUDE} REAL,
                ${WorkshopContract.COL_LONGITUDE} REAL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${AppointmentContract.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${ServiceContract.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${ProductContract.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${BlogPostContract.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${WorkshopContract.TABLE_NAME}")
        onCreate(db)
    }
}