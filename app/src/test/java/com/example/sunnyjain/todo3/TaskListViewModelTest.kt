package com.example.sunnyjain.todo3

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.sunnyjain.todo3.repository.TaskListDataContract
import com.example.sunnyjain.todo3.ui.viewtasks.TaskListViewModel
import com.nhaarman.mockito_kotlin.mock
import org.apache.tools.ant.Task
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class TaskListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<List<Task>> = mock()

    @Before
    fun setUpTaskDetailViewModel() {

    }
}


