package com.example.sunnyjain.todo3.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sunnyjain.todo3.vo.Task


@Database(
        entities = [
            Task::class
        ],
        version = 1,
        exportSchema = false
)
abstract class TaskDB: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}