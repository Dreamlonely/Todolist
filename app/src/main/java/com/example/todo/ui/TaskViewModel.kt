package com.example.todo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.todo.model.Priority
import com.example.todo.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun addTask(title: String, prio: Priority) {
        val current = _tasks.value.toMutableList()
        val nextOrder = current.size
        current.add(Task(title = title, priority = prio, order = nextOrder))
        _tasks.value = current
    }

    fun toggleDone(id: String) {
        _tasks.value = _tasks.value.map { if (it.id == id) it.copy(done = !it.done) else it }
    }

    fun deleteAt(position: Int) {
        val current = _tasks.value.toMutableList()
        if (position in current.indices) {
            current.removeAt(position)
            current.forEachIndexed { idx, t -> t.order = idx }
            _tasks.value = current
        }
    }

    fun move(from: Int, to: Int) {
        val current = _tasks.value.toMutableList()
        if (from in current.indices && to in current.indices) {
            val item = current.removeAt(from)
            current.add(to, item)
            current.forEachIndexed { idx, t -> t.order = idx }
            _tasks.value = current
        }
    }
}
