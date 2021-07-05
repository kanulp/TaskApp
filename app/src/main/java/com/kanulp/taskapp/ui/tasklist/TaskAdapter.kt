package com.kanulp.taskapp.ui.tasklist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kanulp.taskapp.R
import com.kanulp.taskapp.data.TaskModel

class TaskAdapter(var context: Activity, var list: List<TaskModel>, var listener: OnItemClickListener) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model,position,listener)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int?)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(mView: View) : RecyclerView.ViewHolder(
            mView
    ){
        var tv_task_title: TextView? = null
        var tv_task_date: TextView? = null
        var tv_task_desc: TextView? = null
        init {
            tv_task_title = mView.findViewById<View>(R.id.tv_task_title) as TextView
            tv_task_date = mView.findViewById<View>(R.id.tv_task_date) as TextView
            tv_task_desc = mView.findViewById<View>(R.id.tv_task_desc) as TextView
        }

        fun bind(model: TaskModel,position:Int,clickListener: OnItemClickListener){
            tv_task_title?.text = model.task_title
            tv_task_date?.text = "Created On : ${model.task_date}"
            tv_task_desc?.text = model.task_detail
            itemView.setOnClickListener{
                clickListener.onItemClick(position)
            }
        }

    }
}