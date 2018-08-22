package com.example.sunnyjain.todo3.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val title:String,
        val description: String,
        val category: String
)