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
        // 1) Intent to open MainActivity
        val intent = android.content.Intent(context, com.example.todo.MainActivity::class.java).apply {
            flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                    android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("taskId", taskId)   // optional: pass the task id if you want later
        }

        // 2) PendingIntent so notification can launch the activity
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        } else {
            android.app.PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = android.app.PendingIntent.getActivity(
            context,
            taskId.hashCode(),         // unique-ish request code
            intent,
            pendingIntentFlags
        )

        // 3) Build notification with the content intent
        val notification = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // replace with your own icon later
            .setContentTitle("Task Reminder")
            .setContentText(taskTitle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)   // 👈 this makes tap open the app
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(taskId.hashCode(), notification)
    }

}
