package com.example.sunnyjain.todo3.ui.addtask


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunnyjain.todo3.R
import com.example.sunnyjain.todo3.di.Injectable
import kotlinx.android.synthetic.main.fragment_add_task_view.*
import javax.inject.Inject

class AddTaskView : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addTaskViewModel: AddTaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //init the viewmodel
        addTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveTask.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.saveTask -> {
                addTaskViewModel.title = title.text.toString()
                addTaskViewModel.description = description.text.toString()
                addTaskViewModel.addTask()
            }
        }
    }

}
