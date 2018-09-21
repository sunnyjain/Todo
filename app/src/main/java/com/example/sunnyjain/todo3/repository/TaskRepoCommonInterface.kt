package com.example.sunnyjain.todo3.repository

import com.example.sunnyjain.todo3.vo.Task
import io.reactivex.subjects.PublishSubject

interface TaskRepoCommonInterface {
    val fetchTask: PublishSubject<Task>
    fun fetchTaskListFor(taskId: Long?)
}