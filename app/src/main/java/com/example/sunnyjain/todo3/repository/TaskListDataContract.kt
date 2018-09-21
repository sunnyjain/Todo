package com.example.sunnyjain.todo3.repository

import android.arch.lifecycle.LiveData
import io.reactivex.subjects.PublishSubject
import com.example.sunnyjain.todo3.vo.Task

interface TaskListDataContract {
    interface Repository {
        val fetchTask: PublishSubject<Task>
        fun fetchTaskListFor(taskId: Long?)
        fun loadTasks(): LiveData<MutableList<Task>>
        fun updateTask(task: Task)
        fun saveTask(task: Task)
        fun removeTask(task: Task)
        fun clear()
    }
}