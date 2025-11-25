package com.example.todo.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.FragmentCalendarBinding
import com.example.todo.model.Task
import com.prolificinteractive.materialcalendarview.*
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {

    private var _b: FragmentCalendarBinding? = null
    private val b get() = _b!!

    private val vm: TaskViewModel by activityViewModels {
        TaskViewModelFactory(requireActivity().application)
    }

    private var allTasks: List<Task> = emptyList()
    private var selectedDayStartMillis: Long = startOfToday()

    private var dateTextColor = Color.BLACK
    private var selectionColor = Color.argb(136, 209, 196, 233) // default light mode
    private val dotColor = 0xFF6200EE.toInt() // purple

    private lateinit var currentMonthDecorator: DayViewDecorator

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

        // Detect dark/light mode
        val isDarkMode = resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK ==
                android.content.res.Configuration.UI_MODE_NIGHT_YES

        // Colors based on mode
        dateTextColor = if (isDarkMode) Color.WHITE else Color.BLACK
        selectionColor = if (isDarkMode) Color.argb(85, 187, 134, 252) else Color.argb(136, 209, 196, 233)
        val arrowColor = if (isDarkMode) Color.WHITE else Color.BLACK

        // Apply selection color
        b.calendarView.setSelectionColor(selectionColor)

        // Apply arrow tint
        val arrowFilter = PorterDuffColorFilter(arrowColor, PorterDuff.Mode.SRC_IN)
        b.calendarView.leftArrow.setColorFilter(arrowFilter)
        b.calendarView.rightArrow.setColorFilter(arrowFilter)

        // Decorator for current month day text color
        currentMonthDecorator = object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                val month = b.calendarView.currentDate.month
                val year = b.calendarView.currentDate.year
                return day.month == month && day.year == year
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(ForegroundColorSpan(dateTextColor))
            }
        }
        b.calendarView.addDecorator(currentMonthDecorator)

        // Refresh decorator & dots on month change
        b.calendarView.setOnMonthChangedListener { _, _ ->
            b.calendarView.removeDecorator(currentMonthDecorator)
            b.calendarView.addDecorator(currentMonthDecorator)
            updateCalendarDots()
        }

        val dayAdapter = TaskAdapter(onToggle = vm::toggleDone, onEdit = {})
        b.recyclerDayTasks.layoutManager = LinearLayoutManager(requireContext())
        b.recyclerDayTasks.adapter = dayAdapter

        // Highlight selected day and show tasks
        b.calendarView.setOnDateChangedListener { _, date, _ ->
            val cal = Calendar.getInstance().apply {
                set(date.year, date.month - 1, date.day, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            selectedDayStartMillis = cal.timeInMillis
            updateTasksForSelectedDay(dayAdapter)
        }

        // Observe tasks from ViewModel
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
            Calendar.getInstance().apply { timeInMillis = millis }
        }.map { cal ->
            CalendarDay.from(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))
        }.toSet()

        // Keep month decorator
        b.calendarView.removeDecorators().apply {
            b.calendarView.addDecorator(currentMonthDecorator)
        }

        // Add dots for tasks
        b.calendarView.addDecorator(EventDecorator(datesWithTasks, dotColor))
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

    class EventDecorator(
        private val dates: Set<CalendarDay>,
        private val color: Int
    ) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay): Boolean = dates.contains(day)
        override fun decorate(view: DayViewFacade) {
            view.addSpan(DotSpan(8f, color))
        }
    }
}
