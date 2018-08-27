package com.example.sunnyjain.todo3.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sunnyjain.todo3.AppExecutors
import com.example.sunnyjain.todo3.db.TaskDao
import com.example.sunnyjain.todo3.vo.Task
import javax.inject.Inject
import javax.inject.Singleton
import android.provider.ContactsContract.CommonDataKinds.Note
import android.os.AsyncTask



/**
 * So basically this repo file retrieve the info from respective places either fr
 * from a local database or from network
 * */
@Singleton
class TaskRepo @Inject constructor(
        private val appExecutors: AppExecutors,
        private val taskDao: TaskDao
) {
    fun loadTasks(): LiveData<List<Task>> {
        return taskDao.retrieveAllTasks()
    }

    fun updateTask(updateValue: String) {
        UpdateTaskAsyncTask(taskDao).execute(updateValue)
    }

    fun saveTask(task: Task){
        taskDao.insert(task)
    }

    private class UpdateTaskAsyncTask(private val taskDao: TaskDao) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg updateVal: String): Void? {
            taskDao.updateVal(updateVal[0], 6)
            return null
        }
    }
}