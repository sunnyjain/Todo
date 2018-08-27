package com.example.sunnyjain.todo3.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sunnyjain.todo3.ui.addtask.AddTaskView
import com.example.sunnyjain.todo3.ui.addtask.AddTaskViewModel
import com.example.sunnyjain.todo3.ui.viewtasks.TaskListViewModel
import com.example.sunnyjain.todo3.viewmodel.TodoViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel::class)
    abstract fun bindTaskListViewModel(taskListViewModel: TaskListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddTaskViewModel::class)
    abstract fun bindAddTaskListViewModel(addTaskViewModel: AddTaskViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory

}