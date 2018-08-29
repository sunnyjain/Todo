package com.example.sunnyjain.todo3.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.sunnyjain.todo3.AppExecutors
import com.example.sunnyjain.todo3.db.TaskDao
import com.example.sunnyjain.todo3.extz.performOnBackOutOnMain
import com.example.sunnyjain.todo3.extz.success
import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
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
) : TaskRepoCommonInterface {

    override val fetchTask: PublishSubject<Task> = PublishSubject.create()
    private val compositeDisposable = CompositeDisposable()

    override fun fetchTaskListFor(taskId: Long?) {
        if (taskId == null) return
        compositeDisposable.add(taskDao.findById(taskId).performOnBackOutOnMain().subscribe({
            fetchTask.success(it)
        }, {
            //for now not providing any outcome so leaving it!
        }))

    }

    fun loadTasks(): LiveData<List<Task>> {
        return taskDao.retrieveAllTasks()
    }
    fun updateTask(task: Task) {
        compositeDisposable.add(
        Single.create<Int> {
            taskDao.updateVal(task)
        }.performOnBackOutOnMain().subscribe(
                {
                    if(it != 0)
                        fetchTask.success(task)
                    else
                        Log.e("val", "not working")
                }, {}))
    }

    fun saveTask(task: Task) {
        compositeDisposable.add(
                Single.create<Long> {
                    taskDao.insert(task)
                }.performOnBackOutOnMain().subscribe(
                        {
                            if (it != null)
                                fetchTask.success(task)
                        }, {}))
    }

    fun clear() {
        compositeDisposable.clear()
    }
}