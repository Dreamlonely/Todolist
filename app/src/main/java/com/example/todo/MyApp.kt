package com.example.todo

import android.app.Application
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationChannelCompat
import androidx.annotation.RequiresApi
import android.Manifest
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Create notification channel once when the app launches
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                "task_channel",
                "Task Reminders",
                android.app.NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(android.app.NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
