package com.example.todolist

// represents the data associated with each to-do item,
// TODO: add description, due date, etc.
data class ToDo (
    val title: String,
    var isChecked: Boolean = false
)