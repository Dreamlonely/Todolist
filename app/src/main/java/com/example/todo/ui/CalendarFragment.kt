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
import com.prolificinteractive.materialcalendarview.CalendarDay
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

    // Dot color
    private val dotPurple = 0xFF6200EE.toInt()
    private val selectionLightPurple = 0x88D1C4E9.toInt() // transparent light purple

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

        val dayAdapter = TaskAdapter(onToggle = vm::toggleDone, onEdit = {})
        b.recyclerDayTasks.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerDayTasks.adapter = dayAdapter

        // MaterialCalendarView date click
        b.calendarView.setOnDateChangedListener { _, date, _ ->
            // Convert CalendarDay to millis at start of day
            val cal = Calendar.getInstance().apply {
                set(date.year, date.month - 1, date.day, 0, 0, 0) // subtract 1: Calendar.MONTH is 0-based
                set(Calendar.MILLISECOND, 0)
            }
            selectedDayStartMillis = cal.timeInMillis
            updateTasksForSelectedDay(dayAdapter)
        }

        b.calendarView.setSelectionColor(selectionLightPurple)

        viewLifecycleOwner.lifecycleScope.launch {
            vm.tasks.collectLatest { tasks ->
                allTasks = tasks
                updateTasksForSelectedDay(dayAdapter)
                updateCalendarDots()
            }
        }
    }

    private fun updateCalendarDots() {
        val datesWithTasks = allTasks.mapNotNull { it.dueDate }.map { millis ->
            val cal = Calendar.getInstance().apply { timeInMillis = millis }
            // Calendar.MONTH is 0-based, CalendarDay expects 1-based
            CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))
        }.toSet()

        b.calendarView.removeDecorators()
        b.calendarView.addDecorator(EventDecorator(datesWithTasks, dotPurple))
    }

    private fun updateTasksForSelectedDay(adapter: TaskAdapter) {
        val tasksForDay = allTasks.filter { sameDay(it.dueDate, selectedDayStartMillis) }
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        b.txtTasksForDay.text = "Tasks for ${formatter.format(selectedDayStartMillis)}"
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
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
