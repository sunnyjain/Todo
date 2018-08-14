package com.example.sunnyjain.todo3.repository

import android.arch.lifecycle.LiveData
import com.example.sunnyjain.todo3.AppExecutors
import com.example.sunnyjain.todo3.db.TaskDao
import com.example.sunnyjain.todo3.vo.Task
import javax.inject.Inject
import javax.inject.Singleton

/**
 * So basically this repo file retrieve the info from respective places either fr
 * from a local database or from network
 * */
@Singleton
class TaskRepo @Inject constructor(
        private val appExecutors: AppExecutors,
        private val taskDao: TaskDao
){
    fun loadTasks(): LiveData<List<Task>> {
     return taskDao.retrieveAllTasks()
    }
}