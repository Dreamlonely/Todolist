package com.example.todo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.TaskRepository
import com.example.todo.model.Priority
import com.example.todo.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel(
    private val repository: TaskRepository,
    app: Application
) : AndroidViewModel(app) {

    val tasks = repository.tasks
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Add a new task
    fun addTask(title: String, prio: Priority, dueDate: Long?) = viewModelScope.launch {
        val task = Task(
            id = UUID.randomUUID().toString(),
            title = title,
            priority = prio,
            dueDate = dueDate
        )
        repository.addTask(task)
    }

    // Update existing task
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun toggleDone(id: String) = viewModelScope.launch {
        repository.toggleDone(id)
    }

    fun deleteAt(position: Int) = viewModelScope.launch {
        repository.deleteAt(position)
    }

    fun move(from: Int, to: Int) = viewModelScope.launch {
        repository.move(from, to)
    }
}
