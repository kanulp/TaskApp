package com.kanulp.taskapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanulp.taskapp.data.TaskModel
import kotlin.collections.ArrayList

class TaskViewModel : ViewModel() {

    private var taskListLiveData : MutableLiveData<MutableList<TaskModel>> = MutableLiveData()
    var taskList : ArrayList<TaskModel>? = ArrayList()

    fun getAllTask(): MutableLiveData<MutableList<TaskModel>> {
        return taskListLiveData
    }

    fun addTask(task_title: String, task_detail: String,task_date:String){
        var notesModel = TaskModel(task_title, task_detail, task_date)
        taskList?.add(notesModel)
        taskListLiveData?.value = taskList
    }

    fun getTask(position: Int) : TaskModel?{
        return taskListLiveData?.value?.get(position)
    }

    fun updateTask(position: Int, task_title: String, task_detail: String,task_date: String){
        taskListLiveData?.value?.get(position)?.task_title  =  task_title
        taskListLiveData?.value?.get(position)?.task_detail  = task_detail
        taskListLiveData?.value?.get(position)?.task_date = task_date
    }

}