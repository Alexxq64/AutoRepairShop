package com.example.autorepairshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.autorepairshop.ui.fragments.*
import com.example.autorepairshop.utils.TestData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TestData.insertTestData(this)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_appointments -> {
                    replaceFragment(AppointmentsFragment())
                    true
                }
                R.id.navigation_services -> {
                    replaceFragment(ServicesFragment())
                    true
                }
                R.id.navigation_products -> {
                    replaceFragment(ProductsFragment())
                    true
                }
                R.id.navigation_blog -> {
                    replaceFragment(BlogFragment())
                    true
                }
                R.id.navigation_workshops -> {
                    replaceFragment(WorkshopsFragment())
                    true
                }
                R.id.navigation_yclients -> {
                    replaceFragment(YClientsFragment())
                    true
                }
                else -> false
            }
        }

        // Устанавливаем начальный фрагмент
        bottomNavigation.selectedItemId = R.id.navigation_appointments
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}