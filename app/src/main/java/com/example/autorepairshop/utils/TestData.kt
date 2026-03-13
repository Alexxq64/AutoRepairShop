package com.example.autorepairshop.utils

import android.content.ContentValues
import android.content.Context
import com.example.autorepairshop.data.provider.*

object TestData {

    fun insertTestData(context: Context) {
        context.contentResolver.delete(ServiceContract.CONTENT_URI, null, null)
        context.contentResolver.delete(ProductContract.CONTENT_URI, null, null)
        context.contentResolver.delete(BlogPostContract.CONTENT_URI, null, null)
        context.contentResolver.delete(WorkshopContract.CONTENT_URI, null, null)
        context.contentResolver.delete(AppointmentContract.CONTENT_URI, null, null)

        insertServices(context)
        insertProducts(context)
        insertBlogPosts(context)
        insertWorkshops(context)
        insertAppointments(context)
    }

    private fun insertServices(context: Context) {
        val services = listOf(
            ContentValues().apply {
                put(ServiceContract.COL_NAME, "Замена масла")
                put(ServiceContract.COL_PRICE, 3000)
                put(ServiceContract.COL_DURATION, "1 час")
            },
            ContentValues().apply {
                put(ServiceContract.COL_NAME, "Диагностика двигателя")
                put(ServiceContract.COL_PRICE, 2000)
                put(ServiceContract.COL_DURATION, "30 мин")
            },
            ContentValues().apply {
                put(ServiceContract.COL_NAME, "Замена тормозных колодок")
                put(ServiceContract.COL_PRICE, 4000)
                put(ServiceContract.COL_DURATION, "1.5 часа")
            }
        )
        services.forEach {
            context.contentResolver.insert(ServiceContract.CONTENT_URI, it)
        }
    }

    private fun insertProducts(context: Context) {
        val products = listOf(
            ContentValues().apply {
                put(ProductContract.COL_NAME, "Масло моторное 5W-40")
                put(ProductContract.COL_DESCRIPTION, "Синтетическое масло 4л")
                put(ProductContract.COL_PRICE, 2500)
                put(ProductContract.COL_IMAGE_URL, "")
            },
            ContentValues().apply {
                put(ProductContract.COL_NAME, "Тормозные колодки")
                put(ProductContract.COL_DESCRIPTION, "Передние, комплект")
                put(ProductContract.COL_PRICE, 3500)
                put(ProductContract.COL_IMAGE_URL, "")
            },
            ContentValues().apply {
                put(ProductContract.COL_NAME, "Воздушный фильтр")
                put(ProductContract.COL_DESCRIPTION, "Оригинал")
                put(ProductContract.COL_PRICE, 1200)
                put(ProductContract.COL_IMAGE_URL, "")
            }
        )
        products.forEach {
            context.contentResolver.insert(ProductContract.CONTENT_URI, it)
        }
    }

    private fun insertBlogPosts(context: Context) {
        val posts = listOf(
            ContentValues().apply {
                put(BlogPostContract.COL_TITLE, "Когда менять масло")
                put(BlogPostContract.COL_CONTENT, "Моторное масло рекомендуется менять каждые 10 000 км пробега или раз в год, в зависимости от того, что наступит раньше. Регулярная замена масла продлевает жизнь двигателя.")
                put(BlogPostContract.COL_DATE, "10.03.2026")
                put(BlogPostContract.COL_IMAGE_URL, "")
            },
            ContentValues().apply {
                put(BlogPostContract.COL_TITLE, "Подготовка авто к лету")
                put(BlogPostContract.COL_CONTENT, "Перед летним сезоном проверьте систему кондиционирования, состояние шин и уровень охлаждающей жидкости. Рекомендуем пройти комплексную диагностику.")
                put(BlogPostContract.COL_DATE, "15.03.2026")
                put(BlogPostContract.COL_IMAGE_URL, "")
            }
        )
        posts.forEach {
            context.contentResolver.insert(BlogPostContract.CONTENT_URI, it)
        }
    }

    private fun insertWorkshops(context: Context) {
        val workshops = listOf(
            ContentValues().apply {
                put(WorkshopContract.COL_ADDRESS, "ул. Ленина, 15")
                put(WorkshopContract.COL_PHONE, "+7 (999) 123-45-67")
                put(WorkshopContract.COL_LATITUDE, 55.7558)
                put(WorkshopContract.COL_LONGITUDE, 37.6176)
            },
            ContentValues().apply {
                put(WorkshopContract.COL_ADDRESS, "пр. Мира, 42")
                put(WorkshopContract.COL_PHONE, "+7 (999) 765-43-21")
                put(WorkshopContract.COL_LATITUDE, 55.7512)
                put(WorkshopContract.COL_LONGITUDE, 37.6284)
            }
        )
        workshops.forEach {
            context.contentResolver.insert(WorkshopContract.CONTENT_URI, it)
        }
    }

    private fun insertAppointments(context: Context) {
        val appointments = listOf(
            ContentValues().apply {
                put(AppointmentContract.COL_SERVICE_NAME, "Замена масла")
                put(AppointmentContract.COL_DATE, "20.03.2026")
                put(AppointmentContract.COL_TIME, "10:00")
                put(AppointmentContract.COL_STATUS, "Предстоящая")
                put(AppointmentContract.COL_PRICE, 3000)
            },
            ContentValues().apply {
                put(AppointmentContract.COL_SERVICE_NAME, "Диагностика")
                put(AppointmentContract.COL_DATE, "15.03.2026")
                put(AppointmentContract.COL_TIME, "14:30")
                put(AppointmentContract.COL_STATUS, "Прошедшая")
                put(AppointmentContract.COL_PRICE, 2000)
            }
        )
        appointments.forEach {
            context.contentResolver.insert(AppointmentContract.CONTENT_URI, it)
        }
    }
}