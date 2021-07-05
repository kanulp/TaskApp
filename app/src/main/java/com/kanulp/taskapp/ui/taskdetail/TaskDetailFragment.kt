package com.kanulp.taskapp.ui.taskdetail

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kanulp.taskapp.R
import com.kanulp.taskapp.util.Validator
import com.kanulp.taskapp.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TaskDetailFragment : Fragment(){

    var viewModel : TaskViewModel? = null

    var position : Int? = null
    var fromEdit : Boolean? = false
    private val myCalendar = Calendar.getInstance()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_task_detail, container, false)
        bindView(v)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
    }

    private fun bindView(view: View) {
        view.findViewById<EditText>(R.id.ed_task_date)?.setOnClickListener{
            DatePickerDialog(requireContext(), datePickerListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        view.findViewById<Button>(R.id.btn_action).setOnClickListener{
            saveTask()
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        position = arguments?.getInt("position")
        if(position!=null) {
            btn_action?.text = "Update"
            tv_title?.text = "Update Task"
            fromEdit = true
            var model = viewModel?.getTask(position = position!!)
            ed_task_title?.setText(model?.task_title)
            ed_task_desc?.setText(model?.task_detail)
            ed_task_date?.setText(model?.task_date)
        }
        else{
            btn_action?.text = "Save"
            tv_title?.text = "Add New Task"
        }
    }
    var datePickerListener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        ed_task_date?.setText(sdf.format(myCalendar.time))
    }

    private fun saveTask(){

        if(checkValidation()){
            val title = ed_task_title?.text.toString()
            val desc = ed_task_desc?.text.toString()
            val time = ed_task_date?.text.toString()
            if(fromEdit!!){
                viewModel?.updateTask(position ?: 0, title, desc, time)
                Toast.makeText(activity, "Task Updated Successfully!", Toast.LENGTH_SHORT).show()
            }else {
                viewModel?.addTask(title, desc, time)
                Toast.makeText(activity, "Task Added Successfully!", Toast.LENGTH_SHORT).show()
            }
            findNavController().navigate(R.id.action_TaskDetailFragment_to_TaskListFragment)

        }
    }
    private fun checkValidation():Boolean{
        if(!Validator.validateEmptyInput(ed_task_title?.text.toString())) {
            ed_task_title.error = "Please Enter Title."
            return false
        }
        if(!Validator.validateEmptyInput(ed_task_desc?.text.toString())) {
            ed_task_desc.error = "Please Enter Description."
            return false
        }
        if(!Validator.validateEmptyInput(ed_task_date?.text.toString())) {
            ed_task_date.error = "Please Enter Date."
            return false
        }
        if(!Validator.validateDate(ed_task_date?.text.toString())) {
            ed_task_date.error = "Please Enter Valid Date."
            return false
        }
        return true
    }
}