package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

import  java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//TODO give option to display all items or only items that have been done


class ToDoAdapter(private val todos: MutableList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) // serve the purpose of holding a reference to a single view within a RecyclerView
    private lateinit var binding: ItemTodoBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        binding = ItemTodoBinding.bind(view)
        return ToDoViewHolder(view)
    }

    fun addTodo(todo: ToDo) {
        todos.add(0, todo) //add to do at the beginning of the list
        notifyItemInserted(0)
    }

    //TODO put done todos in a different screen, add date and time it was done
    //make it not delete, but rather not be displayed
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


   /* fun convertDateToTimeZone(date: Date, zoneId: ZoneId = ZoneId.of("America/New_York")): LocalDateTime {
        val instant = date.toInstant()
        return LocalDateTime.ofInstant(instant, zoneId)
    }*/

    private fun formatDate(date: Date): String{
        val format = SimpleDateFormat("hh:mm:ss a dd MMM yy", Locale.getDefault())
        return format.format(date)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curTodo = todos[position]
        binding.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            tvDateCreated.text = formatDate(curTodo.dateCreated)
            toggleStrikeThrough(tvTodoTitle, cbDone.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
}
