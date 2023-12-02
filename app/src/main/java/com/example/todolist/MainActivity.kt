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

        todoAdapter = ToDoAdapter(todo)

        binding.rvToDoItems.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity) //todo use a grid layout
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