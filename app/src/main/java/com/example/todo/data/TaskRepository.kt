// com/example/todo/data/TaskRepository.kt
package com.example.todo.data

import android.content.Context
import androidx.work.*
import com.example.todo.model.Task
import com.example.todo.notifications.TaskReminderWorker
import kotlinx.coroutines.flow.firstOrNull
import java.util.concurrent.TimeUnit

class TaskRepository(
    private val dao: TaskDao,
    private val context: Context
) {

    // READ
    val tasks = dao.getAllTasks()

    // CREATE
    suspend fun addTask(task: Task) {
        dao.insert(task)
        scheduleNotification(task)
    }

    // UPDATE
    suspend fun updateTask(task: Task) {
        dao.updateTask(task)
        scheduleNotification(task)
    }

    // TOGGLE done
    suspend fun toggleDone(id: String) {
        val currentList = dao.getAllTasks().firstOrNull() ?: return
        val task = currentList.find { it.id == id } ?: return
        dao.updateTask(task.copy(done = !task.done))
    }

    // DELETE at position (position in DB order)
    suspend fun deleteAt(position: Int) {
        val currentList = dao.getAllTasks().firstOrNull() ?: return
        if (position !in currentList.indices) return

        val mutable = currentList.toMutableList()
        val removed = mutable.removeAt(position)
        dao.delete(removed)

        cancelNotification(removed.id)

        val reordered = mutable.mapIndexed { index, t -> t.copy(order = index) }
        dao.updateTasks(reordered)
    }

    // MOVE (reorder) – indices are in DB order
    suspend fun move(from: Int, to: Int) {
        val currentList = dao.getAllTasks().firstOrNull() ?: return
        if (from !in currentList.indices || to !in currentList.indices) return

        val mutable = currentList.toMutableList()
        val item = mutable.removeAt(from)
        mutable.add(to, item)

        val reordered = mutable.mapIndexed { index, t -> t.copy(order = index) }
        dao.updateTasks(reordered)
    }

    // -------------------
    // Notifications
    // -------------------
    private fun scheduleNotification(task: Task) {
        cancelNotification(task.id)

        val due = task.dueDate ?: return
        val delay = due - System.currentTimeMillis()
        if (delay <= 0) return

        val data = workDataOf(
            "taskId" to task.id,
            "taskTitle" to task.title
        )

        val request = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            task.id,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    private fun cancelNotification(taskId: String) {
        WorkManager.getInstance(context).cancelUniqueWork(taskId)
    }
}
