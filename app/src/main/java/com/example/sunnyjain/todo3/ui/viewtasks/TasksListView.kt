package com.example.sunnyjain.todo3.ui.viewtasks


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.adapter.TaskListAdapter
import com.example.sunnyjain.todo3.di.Injectable
import com.example.sunnyjain.todo3.utils.RecyclerItemTouchHelper
import com.example.sunnyjain.todo3.utils.SwipeController
import com.example.sunnyjain.todo3.utils.SwipeControllerActions
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.fragment_view_tasks_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TasksListView : Fragment(), View.OnClickListener, Injectable, RecyclerItemTouchHelper.OnTodoItemClickListener {

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
        val swipeController = SwipeController(object : SwipeControllerActions(){
            override fun onRightClicked(position: Int) {
                Log.e("position", position.toString())
                super.onRightClicked(position)
            }
        })
        val itemTouchHelper = ItemTouchHelper(swipeController)

//        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
//        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
                swipeController.onDraw(c)
            }
        })
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

    override fun onTodoClicked(position: Int) {
        Log.e("poisiton clicked", position.toString())
    }
}
