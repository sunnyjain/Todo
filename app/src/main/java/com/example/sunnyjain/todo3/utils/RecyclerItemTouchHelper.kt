package com.example.sunnyjain.todo3.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import com.example.sunnyjain.todo3.adapter.TaskListAdapter
import com.example.sunnyjain.todo3.extz.toPx

class RecyclerItemTouchHelper(dragDirs: Int, swipeDirs: Int, val listener: OnTodoItemClickListener) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView = (viewHolder as TaskListAdapter.MyViewHolder).viewForeground
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as TaskListAdapter.MyViewHolder).viewForeground
        var dxTemp = dX
        dxTemp = Math.max(dxTemp, (-100).toPx())
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dxTemp, dY,
                actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val foregroundView = (viewHolder as TaskListAdapter.MyViewHolder).viewForeground
        var dxTemp = dX
        dxTemp = Math.max(dxTemp, (-100).toPx())
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dxTemp, dY,
                actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        val foregroundView = (viewHolder as TaskListAdapter.MyViewHolder).viewForeground
        getDefaultUIUtil().clearView(foregroundView)
    }

    /**
     * so this method helps me in deciding the amount of swipe in an area of the viewholder i need to make
     * */
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder?): Float {
        return 0.85f
    }

    /**
     * making the swipe more easier for the user.
     * */
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue - 5f
    }


    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        /*(viewHolder as TaskListAdapter.MyViewHolder).deleteView.setOnClickListener{
            listener.onTodoClicked(viewHolder.adapterPosition)
        }*/
        viewHolder.
        (viewHolder as TaskListAdapter.MyViewHolder).deleteView.setOnTouchListener { _, m ->
            if (m.action == ACTION_UP) {
                Log.e("clicked", "intercepted")
                listener.onTodoClicked(viewHolder.adapterPosition)
            }
            true
        }
    }

    interface OnTodoItemClickListener {
        fun onTodoClicked(position: Int)
    }
}