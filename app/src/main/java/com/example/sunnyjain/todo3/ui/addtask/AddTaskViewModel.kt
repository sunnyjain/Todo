package com.example.sunnyjain.todo3.ui.addtask

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*this is to make sure data is safe whenever our view goes into configuration changes. eg view rotation*/
class AddTaskViewModel
@Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {
    var title: String = ""
    var description: String = ""
    val task: MutableLiveData<Task> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun addTask() {
        /*here i can manage whether teh data i received is proper and validated*/
        /*here 0 is treated as non-set while inserting*/
        taskRepo.saveTask(Task(0, title, description, "High"))
    }

    fun getTaskById(id: Long) {
        compositeDisposable.add(
                taskRepo.loadTaskById(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            task.value = it
                        }, {
                            Log.e("fatta bc", "bc")
                            task.value = null
                        })

        )
    }

    fun updateTask(task: Task) {

        Completable.fromAction { taskRepo.updateTask(task) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Log.e("update", "complete")
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

}