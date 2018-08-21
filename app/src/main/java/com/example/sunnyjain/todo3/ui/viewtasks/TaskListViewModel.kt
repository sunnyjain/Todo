package com.example.sunnyjain.todo3.ui.viewtasks

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import javax.inject.Inject

class TaskListViewModel
@Inject constructor(taskRepo: TaskRepo) : ViewModel() {
    val taskList: MutableLiveData<List<Task>> = taskRepo.loadTasks()
}