package com.example.sunnyjain.todo3.ui.viewtasks


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.adapter.TaskListAdapter
import com.example.sunnyjain.todo3.di.Injectable
import com.example.sunnyjain.todo3.repository.TaskRepo
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.fragment_view_tasks_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TasksListView : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var taskRepo: TaskRepo

    @Inject
    lateinit var adapter: TaskListAdapter

    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_tasks_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TaskListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask1.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        taskListViewModel.taskList.observe(this, Observer<List<Task>> { t ->
            adapter.tasksList = t!!
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.addTask1 -> {
                taskRepo.updateTask("task5")
            }
        }
    }

}
