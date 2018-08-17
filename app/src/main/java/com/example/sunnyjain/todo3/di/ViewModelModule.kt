package com.example.sunnyjain.todo3.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sunnyjain.todo3.ui.viewtasks.TaskListViewModel
import com.example.sunnyjain.todo3.viewmodel.TodoViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel::class)
    abstract fun bindTaskListViewModel(taskListViewModel: TaskListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory

}