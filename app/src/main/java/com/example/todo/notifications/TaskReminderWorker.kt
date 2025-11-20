package com.example.todo.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class TaskReminderWorker(
    context: android.content.Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        val taskId = inputData.getString("taskId") ?: return Result.failure()
        val taskTitle = inputData.getString("taskTitle") ?: "Task Reminder"

        NotificationHelper(applicationContext).showNotification(taskTitle, taskId)

        return Result.success()
    }
}
