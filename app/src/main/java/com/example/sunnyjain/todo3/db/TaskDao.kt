package com.example.sunnyjain.todo3.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.Single

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Long

    @Query("SELECT * FROM Task")
    fun retrieveAllTasks(): LiveData<MutableList<Task>>

    @Query("SELECT * FROM Task where id = :id")
    fun findById(id: Long) : Single<Task>

    @Update
    fun updateVal(task: Task): Int

    @Delete
    fun deleteVal(task: Task)
}