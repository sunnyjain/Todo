package com.example.sunnyjain.todo3.ui.addtask

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sunnyjain.todo3.extz.toLiveData
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*this is to make sure data is safe whenever our view goes into configuration changes. eg view rotation*/
class AddTaskViewModel
@Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {
    var title: String = ""
    var description: String = ""

    private val compositeDisposable = CompositeDisposable()

    val taskLiveData: LiveData<Task> by lazy {
        taskRepo.fetchTask.toLiveData(compositeDisposable)
    }
    fun addTask() {
        taskRepo.saveTask(Task(0, title, description, "High"))
    }

    fun getTaskById(id: Long) {
        taskRepo.fetchTaskListFor(id)
    }

    fun updateTask(task: Task?) {
        if(task == null){
            Log.e("val", "null")
            return
        }
        taskRepo.updateTask(task)
    }

    override fun onCleared() {
        super.onCleared()
        taskRepo.clear()
        compositeDisposable.clear()
    }
}