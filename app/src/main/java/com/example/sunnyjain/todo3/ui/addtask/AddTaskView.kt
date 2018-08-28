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
import kotlinx.android.synthetic.main.fragment_add_task_view.*
import javax.inject.Inject
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbInterface
import android.hardware.usb.UsbEndpoint
import android.hardware.usb.UsbDeviceConnection
import android.content.Context.USB_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.hardware.usb.UsbManager
import android.hardware.usb.UsbDevice


class AddTaskView : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addTaskViewModel: AddTaskViewModel
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //init the viewmodel
        addTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)
        addTaskViewModel.task.observe(this, Observer { t -> Log.e("value", t?.title ?: "") })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveTask.setOnClickListener(this)
        rootView = view
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val id = it.getLong("id", -1)
            //populating the data
            addTaskViewModel.getTaskById(id)
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
                addTaskViewModel.addTask()
                Navigation.findNavController(v).navigate(R.id.action_addTaskView_to_tasksListView2)
            }
        }
    }
}
