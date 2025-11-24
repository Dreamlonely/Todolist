// com/example/todo/data/TaskDao.kt
package com.example.todo.data

import androidx.room.*
import com.example.todo.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // READ - sorted by our explicit task_order column
    @Query("SELECT * FROM tasks ORDER BY task_order ASC")
    fun getAllTasks(): Flow<List<Task>>

    // CREATE
    @Insert
    suspend fun insert(task: Task)

    // UPDATE single task
    @Update
    suspend fun updateTask(task: Task)

    // UPDATE multiple tasks (for reordering)
    @Update
    suspend fun updateTasks(tasks: List<Task>)

    // DELETE
    @Delete
    suspend fun delete(task: Task)
}
