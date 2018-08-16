package com.example.sunnyjain.todo3.ui.viewtasks

import dagger.Module
import dagger.Provides

@Module
abstract class TaskListViewModule {

    @Provides
    fun taskListView() : TasksListView {
        return taskListView()
    }
}