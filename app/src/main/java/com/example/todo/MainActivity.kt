package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.DialogAddTaskBinding
import com.example.todo.model.Priority
import com.example.todo.ui.TaskAdapter
import com.example.todo.ui.TaskViewModel
import com.example.todo.ui.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val vm: TaskViewModel by viewModels {
        TaskViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val adapter = TaskAdapter(onToggle = vm::toggleDone)
        b.recycler.layoutManager = LinearLayoutManager(this)
        b.recycler.adapter = adapter

        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                vm.move(vh.bindingAdapterPosition, target.bindingAdapterPosition)
                return true
            }

            override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {
                vm.deleteAt(vh.bindingAdapterPosition)
            }

            override fun isLongPressDragEnabled() = true
            override fun isItemViewSwipeEnabled() = true
        })
        helper.attachToRecyclerView(b.recycler)

        lifecycleScope.launch {
            vm.tasks.collectLatest { adapter.submitList(it.toList()) }
        }

        b.fabAdd.setOnClickListener { showAddDialog() }
    }

    private fun showAddDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(LayoutInflater.from(this))
        dialogBinding.spnPriority.adapter = ArrayAdapter.createFromResource(
            this, R.array.priorities, android.R.layout.simple_spinner_dropdown_item
        )
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.new_task))
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.add_task)) { _, _ ->
                val title = dialogBinding.edtTitle.text?.toString()?.trim().orEmpty()
                val prio = when(dialogBinding.spnPriority.selectedItem.toString()) {
                    "HIGH" -> Priority.HIGH
                    "LOW" -> Priority.LOW
                    else -> Priority.MEDIUM
                }
                if (title.isNotEmpty()) vm.addTask(title, prio)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
}
