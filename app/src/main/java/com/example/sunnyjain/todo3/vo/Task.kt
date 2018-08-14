package com.example.sunnyjain.todo3.vo

import android.arch.persistence.room.Entity

@Entity(primaryKeys = ["id"])
data class Task(
        val id: Long,
        val title:String,
        val description: String,
        val category: String
)