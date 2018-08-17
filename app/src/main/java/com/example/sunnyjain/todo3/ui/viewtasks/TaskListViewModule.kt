package com.example.sunnyjain.todo3.ui.viewtasks

import android.app.Application
import com.example.sunnyjain.todo3.AppController
import com.example.sunnyjain.todo3.adapter.TaskListAdapter
import com.example.sunnyjain.todo3.repository.TaskRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TaskListViewModule {

    @Provides
    fun providesTaskListAdapter(taskListView: TasksListView): TaskListAdapter{
        return TaskListAdapter(taskListView)
    }
}