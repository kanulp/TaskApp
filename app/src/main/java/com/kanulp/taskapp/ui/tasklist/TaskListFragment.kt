package com.kanulp.taskapp.ui.tasklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kanulp.taskapp.R
import com.kanulp.taskapp.data.TaskModel
import com.kanulp.taskapp.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TaskListFragment : Fragment() {

    private var viewModel: TaskViewModel? = null
    var recyclerView : RecyclerView? = null
    var taskAdapter : TaskAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = v.findViewById(R.id.rv_task_list)
        v.findViewById<FloatingActionButton>(R.id.fab_task_detail).setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_TaskListFragment_to_TaskDetailFragment)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        setupListUpdate()
    }

    private fun setupListUpdate() {

        viewModel?.getAllTask()?.observe(viewLifecycleOwner, object : Observer<List<TaskModel>> {
            override fun onChanged(t: List<TaskModel>?) {
                if (t != null) {
                    taskAdapter = TaskAdapter(activity!!, t, object: TaskAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int?) {
                            val bundle = bundleOf("position" to position)
                            findNavController().navigate(R.id.action_TaskListFragment_to_TaskDetailFragment, bundle)
                        }
                    })
                    recyclerView?.layoutManager = LinearLayoutManager(context);
                    recyclerView?.adapter = taskAdapter;
                } else {
                    Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}