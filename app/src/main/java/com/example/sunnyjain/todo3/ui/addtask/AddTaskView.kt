package com.example.sunnyjain.todo3.ui.addtask


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.di.Injectable
import com.example.sunnyjain.todo3.vo.Task
import kotlinx.android.synthetic.main.fragment_add_task_view.*
import javax.inject.Inject


class AddTaskView : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addTaskViewModel: AddTaskViewModel

    lateinit var task: Task
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveTask.setOnClickListener(this)
        addTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)
        addTaskViewModel.taskLiveData.observe(this, Observer { t ->
            t.let {
                title.setText(t?.title ?: "")
                description.setText(t?.description ?: "")
                Log.e("value", t?.title ?: "")
                task = it!!
            }
        })
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val id = it.getLong("id", -1)
            //populating the data
            addTaskViewModel.getTaskById(id)
            saveTask.text = if (id == -1L) "Add Task" else "Update Task"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.saveTask -> {
                //validation
                if (title.text.toString().isBlank()) {
                    Toast.makeText(v.context, "please enter a title", Toast.LENGTH_SHORT).show()
                    return
                }
                if (description.text.toString().isBlank()) {
                    Toast.makeText(v.context, "please enter a description", Toast.LENGTH_SHORT).show()
                    return
                }
                addTaskViewModel.title = title.text.toString()
                addTaskViewModel.description = description.text.toString()
                if (saveTask.text == "Add Task") {
                    addTaskViewModel.addTask()
                }
                else {
                    //new data
                    task.description = addTaskViewModel.description
                    task.title = addTaskViewModel.title
                    addTaskViewModel.updateTask(task)
                }
                Navigation.findNavController(v).navigate(R.id.action_addTaskView_to_tasksListView2)
            }
        }
    }
}
