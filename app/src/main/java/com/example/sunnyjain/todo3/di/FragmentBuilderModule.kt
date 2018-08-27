package com.example.sunnyjain.todo3.di

import com.example.sunnyjain.todo3.ui.addtask.AddTaskView
import com.example.sunnyjain.todo3.ui.viewtasks.TasksListView
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeTasksListViewFragment(): TasksListView

    @ContributesAndroidInjector
    abstract fun contributeAddTaskListViewFragment(): AddTaskView

}