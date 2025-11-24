// com/example/todo/model/Task.kt
package com.example.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val priority: Priority,
    val done: Boolean = false,

    // Use a safe column name in DB to avoid confusion with SQL keyword ORDER
    @ColumnInfo(name = "task_order")
    val order: Int = 0,

    val dueDate: Long? = null
)
