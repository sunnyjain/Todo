package com.example.sunnyjain.todo3.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.ui.viewtasks.TasksListView
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.item_task_view.view.*
import javax.inject.Inject


class TaskListAdapter @Inject constructor(private val taskListView: TasksListView) : RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {
    var tasksList: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_view, parent, false))
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = tasksList[position].title
        holder.description.text = tasksList[position].description
        holder.itemView.setOnClickListener(taskListView)

    }

    fun removeAtPos(position: Int){
        tasksList.removeAt(position)
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, tasksList.size)
    }

    fun updateList(taskListNew: MutableList<Task>) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getOldListSize(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getNewListSize(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        tasksList = taskListNew
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val title = view.title!!
        val description = view.description!!
    }


}