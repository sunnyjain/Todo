package com.example.sunnyjain.todo3.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var title:String,
        var description: String,
        val category: String
)