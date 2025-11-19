package com.example.todo.data

import androidx.room.*
import com.example.todo.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    // READ
    @Query("SELECT * FROM tasks ORDER BY `order` ASC")
    fun getAllTasks(): Flow<List<Task>>

    // CREATE
    @Insert
    suspend fun insert(task: Task)

    // UPDATE single
    @Update
    suspend fun update(task: Task)

    // UPDATE many (for reordering)
    @Update
    suspend fun updateTasks(tasks: List<Task>)

    // DELETE
    @Delete
    suspend fun delete(task: Task)
}
