package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val todo = mutableListOf<ToDo>()
        todo.addAll(listOf(
            ToDo("Complete homework", true),
            ToDo("Go to the gym", false),
            ToDo("Buy groceries", true),
            ToDo("Call a friend", false)
        ))

        todoAdapter = ToDoAdapter(todo)

        binding.rvToDoItems.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        binding.btnAdd.setOnClickListener {
            val todoTitle = binding.etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etToDoTitle.text.clear()
            }
        }
        binding.deleteBtn.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}