package com.example.sunnyjain.todo3;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.sunnyjain.todo3.ui.addtask.AddTaskView
import com.example.sunnyjain.todo3.ui.viewtasks.TasksListView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialFragment()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun initialFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, TasksListView())
                .commit()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.mainFrame, fragment)
                .addToBackStack(null)
                .commit()
    }
}
