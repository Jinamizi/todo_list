package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class ToDoAdapter(private val todos: MutableList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) // serve the purpose of holding a reference to a single view within a RecyclerView
    private lateinit var binding: ItemTodoBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        binding = ItemTodoBinding.bind(view)
        return ToDoViewHolder(view)
    }

    fun addTodo(todo: ToDo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeIf { !it.isChecked }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        tvTodoTitle.paintFlags = if (isChecked) tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG else tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            binding.tvTodoTitle.text = curTodo.title
            binding.cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(binding.tvTodoTitle, curTodo.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(binding.tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
}
