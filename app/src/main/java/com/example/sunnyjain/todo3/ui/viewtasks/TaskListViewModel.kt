package com.example.sunnyjain.todo3.ui.viewtasks

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import javax.inject.Inject

class TaskListViewModel
@Inject constructor(taskRepo: TaskRepo) : ViewModel() {

    val taskList: LiveData<List<Task>> = taskRepo.loadTasks()

}