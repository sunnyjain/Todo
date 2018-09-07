package com.example.sunnyjain.todo3.ui.viewtasks


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.adapter.TaskListAdapter
import com.example.sunnyjain.todo3.di.Injectable
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.fragment_view_tasks_list.*
import javax.inject.Inject
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.sunnyjain.todo3.utils.RecyclerItemTouchHelper


/**
 * A simple [Fragment] subclass.
 */
class TasksListView : Fragment(), View.OnClickListener, Injectable, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener  {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: TaskListAdapter

    private lateinit var taskListViewModel: TaskListViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask1.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        taskListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TaskListViewModel::class.java)
        taskListViewModel.taskList.observe(this, Observer<List<Task>> { t ->
            adapter.tasksList = t!!
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.addTask1 -> findNavController(view).navigate(R.id.action_tasksListView_to_addTaskView2)

            R.id.mainItemView -> {
                val position = recyclerView.getChildAdapterPosition(view)
                val bundle = Bundle()
                bundle.putLong("id", adapter.tasksList[position].id)
                findNavController(view).navigate(R.id.action_tasksListView_to_addTaskView2, bundle)
            }
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {

    }

}
