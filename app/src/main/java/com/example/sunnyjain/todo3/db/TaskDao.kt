package com.example.sunnyjain.todo3.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.sunnyjain.todo3.vo.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("SELECT * FROM Task")
    fun retrieveAllTasks(): List<Task>

    @Query("UPDATE Task SET title = :changedTitle")
    fun updateVal(changedTitle: String)
}