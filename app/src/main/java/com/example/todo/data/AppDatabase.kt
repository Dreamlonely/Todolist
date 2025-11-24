// com/example/todo/data/AppDatabase.kt
package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.model.PriorityConverter
import com.example.todo.model.Task

@Database(
    entities = [Task::class],
    version = 3,                // 🔸 bump version because schema changed
    exportSchema = false
)
@TypeConverters(PriorityConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_db"
                )
                    .fallbackToDestructiveMigration()   // 🔸 avoid migration crashes
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
