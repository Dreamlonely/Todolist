package com.example.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.FragmentCalendarBinding
import com.example.todo.model.Task
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarFragment : Fragment() {

    private var _b: FragmentCalendarBinding? = null
    private val b get() = _b!!

    private val vm: TaskViewModel by activityViewModels {
        TaskViewModelFactory(requireActivity().application)
    }

    private var allTasks: List<Task> = emptyList()
    private var selectedDayStartMillis: Long = startOfToday()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentCalendarBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dayAdapter = TaskAdapter(
            onToggle = vm::toggleDone,
            onEdit = { /* optional: edit from calendar; you can hook this up later */ }
        )
        b.recyclerDayTasks.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerDayTasks.adapter = dayAdapter

        b.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            selectedDayStartMillis = cal.timeInMillis
            updateTasksForSelectedDay(dayAdapter)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.tasks.collectLatest { tasks ->
                allTasks = tasks
                updateTasksForSelectedDay(dayAdapter)
            }
        }
    }

    private fun updateTasksForSelectedDay(adapter: TaskAdapter) {
        val tasksForDay = allTasks.filter { task ->
            sameDay(task.dueDate, selectedDayStartMillis)
        }

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateText = formatter.format(selectedDayStartMillis)
        b.txtTasksForDay.text = "Tasks for $dateText"

        adapter.submitList(tasksForDay)
    }

    private fun sameDay(taskDueMillis: Long?, dayStartMillis: Long): Boolean {
        if (taskDueMillis == null) return false
        val calTask = Calendar.getInstance().apply { timeInMillis = taskDueMillis }
        val calDay = Calendar.getInstance().apply { timeInMillis = dayStartMillis }

        return calTask.get(Calendar.YEAR) == calDay.get(Calendar.YEAR) &&
                calTask.get(Calendar.DAY_OF_YEAR) == calDay.get(Calendar.DAY_OF_YEAR)
    }

    private fun startOfToday(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
