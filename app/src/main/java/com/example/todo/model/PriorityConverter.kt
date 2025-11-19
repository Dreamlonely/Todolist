// com/example/todo/model/PriorityConverter.kt
package com.example.todo.model

import androidx.room.TypeConverter

class PriorityConverter {

    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)
}
