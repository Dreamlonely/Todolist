package com.example.todo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.CalendarFragment
import com.example.todo.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val calendarFragment = CalendarFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
        }

        // Start on Home
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, homeFragment, "home")
            .commit()
        b.bottomNav.selectedItemId = R.id.nav_home

        b.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, homeFragment, "home")
                        .commit()
                    true
                }

                R.id.nav_calendar -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, calendarFragment, "calendar")
                        .commit()
                    true
                }

                R.id.nav_tools -> {
                    // Always open tools from Home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, homeFragment, "home")
                        .commit()
                    b.bottomNav.selectedItemId = R.id.nav_home
                    // Let HomeFragment show the tools bottom sheet
                    homeFragment.showTools()
                    true
                }

                else -> false
            }
        }
    }
}
