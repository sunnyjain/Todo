package com.example.sunnyjain.todo3.ui.addtask

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import javax.inject.Inject

/*this is to make sure data is safe whenever our view goes into configuration changes. eg view rotation*/
class AddTaskViewModel
@Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {
    
    fun addTask(task: Task){
        taskRepo.saveTask(task)
    }
}