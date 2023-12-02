package com.example.todolist

import java.util.Date

// represents the data associated with each to-do item,
// TODO: add description, due date, etc.
data class ToDo (
    var title: String,
    var isChecked: Boolean = false,
    val dateCreated: Date = Date()
)