package com.example.todo.notifications


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.todo.R

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper(private val context: Context) {

    init {
        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationConstants.CHANNEL_ID,
            NotificationConstants.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = NotificationConstants.CHANNEL_DESC
        }
        context.getSystemService(NotificationManager::class.java)
            ?.createNotificationChannel(channel)
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showNotification(taskTitle: String, taskId: String) {
        val notification = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Replace with your icon later if we want to make it professional
            .setContentTitle("Task Reminder")
            .setContentText(taskTitle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(taskId.hashCode(), notification)
    }
}
