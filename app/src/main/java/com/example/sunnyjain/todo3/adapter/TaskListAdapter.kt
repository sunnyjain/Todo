package com.example.sunnyjain.todo3.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.ui.viewtasks.TasksListView
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.item_task_view.view.*
import java.util.*
import javax.inject.Inject

class TaskListAdapter @Inject constructor(taskListView: TasksListView) : RecyclerView.Adapter<TaskListAdapter.MyViewHolder>(){
    var tasksList: List<Task> = ArrayList(0)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_view, parent, false))
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = tasksList[position].title
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.title!!
    }
}