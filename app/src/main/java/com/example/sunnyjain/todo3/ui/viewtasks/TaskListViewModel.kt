package com.example.sunnyjain.todo3.ui.viewtasks

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TaskListViewModel
@Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {
    val taskList: LiveData<MutableList<Task>> = taskRepo.loadTasks()

    fun removeTaskAt(task: Task) {
        taskRepo.removeTask(task)
    }
}