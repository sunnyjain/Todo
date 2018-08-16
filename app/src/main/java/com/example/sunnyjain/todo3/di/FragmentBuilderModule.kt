package com.example.sunnyjain.todo3.di

import com.example.sunnyjain.todo3.ui.viewtasks.TaskListViewModule
import com.example.sunnyjain.todo3.ui.viewtasks.TasksListView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [TaskListViewModule::class])
    abstract fun contributeTasksListViewFragment(): TasksListView

}