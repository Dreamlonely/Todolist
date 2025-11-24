package com.example.todo.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.DialogAddTaskBinding
import com.example.todo.databinding.FragmentHomeBinding
import com.example.todo.model.Priority
import com.example.todo.model.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    private var _b: FragmentHomeBinding? = null
    private val b get() = _b!!

    private val vm: TaskViewModel by activityViewModels {
        TaskViewModelFactory(requireActivity().application)
    }

    private var allTasks: List<Task> = emptyList()
    private var currentQuery: String = ""

    private enum class SortMode { BY_ORDER, PRIORITY_HIGH_FIRST, DUE_SOONEST_FIRST, DUE_LATEST_FIRST }
    private var currentSort = SortMode.BY_ORDER

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentHomeBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter(vm::toggleDone, ::showEditTaskDialog)
        b.recycler.layoutManager = LinearLayoutManager(requireContext())
        b.recycler.adapter = adapter
        (b.recycler.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

        // Drag & swipe (only when list matches DB order & no search)
        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {
            override fun onMove(
                rv: RecyclerView,
                vh: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                if (!canMutateByPosition()) return false
                vm.move(vh.bindingAdapterPosition, target.bindingAdapterPosition)
                return true
            }

            override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {
                if (!canMutateByPosition()) {
                    adapter.notifyItemChanged(vh.bindingAdapterPosition)
                    return
                }
                vm.deleteAt(vh.bindingAdapterPosition)
            }

            override fun isLongPressDragEnabled() = canMutateByPosition()
            override fun isItemViewSwipeEnabled() = canMutateByPosition()
        })
        helper.attachToRecyclerView(b.recycler)

        // Observe tasks
        viewLifecycleOwner.lifecycleScope.launch {
            vm.tasks.collectLatest { tasks ->
                allTasks = tasks
                applyFilterAndSort(adapter)
            }
        }

        // FAB
        b.fabAdd.setOnClickListener { showAddDialog() }
    }

    // Called from MainActivity when Tools item is tapped
    fun showTools() {
        val adapter = b.recycler.adapter as? TaskAdapter ?: return
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_tools, null)
        dialog.setContentView(view)

        val edtSearch = view.findViewById<EditText>(R.id.edtSearchTools)
        edtSearch.setText(currentQuery)
        edtSearch.addTextChangedListener { text ->
            currentQuery = text?.toString().orEmpty()
            applyFilterAndSort(adapter)
        }

        view.findViewById<View>(R.id.sortDefault).setOnClickListener {
            currentSort = SortMode.BY_ORDER
            applyFilterAndSort(adapter)
            dialog.dismiss()
        }

        view.findViewById<View>(R.id.sortHighLow).setOnClickListener {
            currentSort = SortMode.PRIORITY_HIGH_FIRST
            applyFilterAndSort(adapter)
            dialog.dismiss()
        }

        view.findViewById<View>(R.id.sortDueSoon).setOnClickListener {
            currentSort = SortMode.DUE_SOONEST_FIRST
            applyFilterAndSort(adapter)
            dialog.dismiss()
        }

        view.findViewById<View>(R.id.sortDueLatest).setOnClickListener {
            currentSort = SortMode.DUE_LATEST_FIRST
            applyFilterAndSort(adapter)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun canMutateByPosition(): Boolean =
        currentSort == SortMode.BY_ORDER && currentQuery.isBlank()

    private fun applyFilterAndSort(adapter: TaskAdapter) {
        var list = if (currentQuery.isBlank()) allTasks
        else {
            val q = currentQuery.lowercase()
            allTasks.filter { it.title.lowercase().contains(q) }
        }

        list = when (currentSort) {
            SortMode.BY_ORDER -> list.sortedBy { it.order }
            SortMode.PRIORITY_HIGH_FIRST -> list.sortedBy { it.priority.ordinal }
            SortMode.DUE_SOONEST_FIRST ->
                list.sortedWith(
                    compareBy<Task> { it.dueDate == null }
                        .thenBy { it.dueDate ?: Long.MAX_VALUE }
                )
            SortMode.DUE_LATEST_FIRST ->
                list.sortedWith(
                    compareBy<Task> { it.dueDate == null }
                        .thenByDescending { it.dueDate ?: Long.MIN_VALUE }
                )
        }

        adapter.submitList(list)
    }

    private fun showAddDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
        dialogBinding.spnPriority.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.priorities, android.R.layout.simple_spinner_dropdown_item
        )

        val calendar = Calendar.getInstance()
        var selectedDueDate: Long? = null
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        dialogBinding.txtDueDate.setOnClickListener {
            pickDateTime(calendar) { timeInMillis ->
                selectedDueDate = timeInMillis
                dialogBinding.txtDueDate.text = "Due: ${formatter.format(calendar.time)}"
            }
        }

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.new_task))
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.add_task)) { _, _ ->
                val title = dialogBinding.edtTitle.text?.toString()?.trim().orEmpty()
                if (title.isNotEmpty()) {
                    val prio = when (dialogBinding.spnPriority.selectedItem.toString()) {
                        "HIGH" -> Priority.HIGH
                        "LOW" -> Priority.LOW
                        else -> Priority.MEDIUM
                    }
                    vm.addTask(title, prio, selectedDueDate)
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun showEditTaskDialog(task: Task) {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
        dialogBinding.edtTitle.setText(task.title)
        dialogBinding.spnPriority.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.priorities, android.R.layout.simple_spinner_item
        )
        dialogBinding.spnPriority.setSelection(
            when (task.priority) {
                Priority.HIGH -> 0
                Priority.MEDIUM -> 1
                Priority.LOW -> 2
            }
        )

        val calendar = Calendar.getInstance()
        var selectedDueDate = task.dueDate
        task.dueDate?.let { calendar.timeInMillis = it }

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        dialogBinding.txtDueDate.text = task.dueDate
            ?.let { "Due: ${formatter.format(calendar.time)}" }
            ?: "No due date"

        dialogBinding.txtDueDate.setOnClickListener {
            pickDateTime(calendar) { timeInMillis ->
                selectedDueDate = timeInMillis
                dialogBinding.txtDueDate.text = "Due: ${formatter.format(calendar.time)}"
            }
        }

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Edit Task")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val newTitle = dialogBinding.edtTitle.text.toString().trim()
                if (newTitle.isNotEmpty()) {
                    val updatedTask = task.copy(
                        title = newTitle,
                        priority = when (dialogBinding.spnPriority.selectedItemPosition) {
                            0 -> Priority.HIGH
                            1 -> Priority.MEDIUM
                            else -> Priority.LOW
                        },
                        dueDate = selectedDueDate
                    )
                    vm.updateTask(updatedTask)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun pickDateTime(calendar: Calendar, onPicked: (Long) -> Unit) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, y, m, d ->
            calendar.set(Calendar.YEAR, y)
            calendar.set(Calendar.MONTH, m)
            calendar.set(Calendar.DAY_OF_MONTH, d)

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(requireContext(), { _, h, min ->
                calendar.set(Calendar.HOUR_OF_DAY, h)
                calendar.set(Calendar.MINUTE, min)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                onPicked(calendar.timeInMillis)
            }, hour, minute, true).show()
        }, year, month, day).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
