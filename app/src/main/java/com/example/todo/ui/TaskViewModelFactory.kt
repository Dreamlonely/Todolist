package com.example.todo.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.AppDatabase
import com.example.todo.data.TaskRepository

class TaskViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            val db = AppDatabase.getInstance(app)
            val repo = TaskRepository(db.taskDao(), app)
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repo, app) as T // ✅ pass 'app' here
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
