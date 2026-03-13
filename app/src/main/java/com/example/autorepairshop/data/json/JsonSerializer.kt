package com.example.autorepairshop.data.json

import com.example.autorepairshop.model.Workshop
import android.content.Context
import com.example.autorepairshop.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileNotFoundException

object JsonSerializer {

    private val gson = Gson()
    private const val APPOINTMENTS_FILE = "appointments.json"
    private const val SERVICES_FILE = "services.json"
    private const val PRODUCTS_FILE = "products.json"
    private const val BLOGPOSTS_FILE = "blogposts.json"
    private const val WORKSHOPS_FILE = "workshops.json"

    // Appointment
    fun appointmentsToJson(appointments: List<Appointment>): String {
        return gson.toJson(appointments)
    }

    fun appointmentsFromJson(json: String): List<Appointment> {
        val type = object : TypeToken<List<Appointment>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveAppointmentsToFile(context: Context, appointments: List<Appointment>) {
        val json = appointmentsToJson(appointments)
        context.openFileOutput(APPOINTMENTS_FILE, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadAppointmentsFromFile(context: Context): List<Appointment> {
        return try {
            val json = context.openFileInput(APPOINTMENTS_FILE).bufferedReader().use { it.readText() }
            appointmentsFromJson(json)
        } catch (e: FileNotFoundException) {
            emptyList()
        }
    }

    // Service
    fun servicesToJson(services: List<Service>): String {
        return gson.toJson(services)
    }

    fun servicesFromJson(json: String): List<Service> {
        val type = object : TypeToken<List<Service>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveServicesToFile(context: Context, services: List<Service>) {
        val json = servicesToJson(services)
        context.openFileOutput(SERVICES_FILE, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadServicesFromFile(context: Context): List<Service> {
        return try {
            val json = context.openFileInput(SERVICES_FILE).bufferedReader().use { it.readText() }
            servicesFromJson(json)
        } catch (e: FileNotFoundException) {
            emptyList()
        }
    }

    // Product
    fun productsToJson(products: List<Product>): String {
        return gson.toJson(products)
    }

    fun productsFromJson(json: String): List<Product> {
        val type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveProductsToFile(context: Context, products: List<Product>) {
        val json = productsToJson(products)
        context.openFileOutput(PRODUCTS_FILE, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadProductsFromFile(context: Context): List<Product> {
        return try {
            val json = context.openFileInput(PRODUCTS_FILE).bufferedReader().use { it.readText() }
            productsFromJson(json)
        } catch (e: FileNotFoundException) {
            emptyList()
        }
    }

    // BlogPost
    fun blogPostsToJson(blogPosts: List<BlogPost>): String {
        return gson.toJson(blogPosts)
    }

    fun blogPostsFromJson(json: String): List<BlogPost> {
        val type = object : TypeToken<List<BlogPost>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveBlogPostsToFile(context: Context, blogPosts: List<BlogPost>) {
        val json = blogPostsToJson(blogPosts)
        context.openFileOutput(BLOGPOSTS_FILE, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadBlogPostsFromFile(context: Context): List<BlogPost> {
        return try {
            val json = context.openFileInput(BLOGPOSTS_FILE).bufferedReader().use { it.readText() }
            blogPostsFromJson(json)
        } catch (e: FileNotFoundException) {
            emptyList()
        }
    }

    // Workshop
    fun workshopsToJson(workshops: List<Workshop>): String {
        return gson.toJson(workshops)
    }

    fun workshopsFromJson(json: String): List<Workshop> {
        val type = object : TypeToken<List<Workshop>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveWorkshopsToFile(context: Context, workshops: List<Workshop>) {
        val json = workshopsToJson(workshops)
        context.openFileOutput(WORKSHOPS_FILE, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadWorkshopsFromFile(context: Context): List<Workshop> {
        return try {
            val json = context.openFileInput(WORKSHOPS_FILE).bufferedReader().use { it.readText() }
            workshopsFromJson(json)
        } catch (e: FileNotFoundException) {
            emptyList()
        }
    }
}