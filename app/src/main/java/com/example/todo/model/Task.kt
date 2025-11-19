package com.example.todo.model

import java.util.UUID

enum class Priority { HIGH, MEDIUM, LOW }

data class Task(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var priority: Priority = Priority.MEDIUM,
    var done: Boolean = false,
    var order: Int = 0
)
