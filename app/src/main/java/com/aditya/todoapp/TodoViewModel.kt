package com.aditya.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TaskRepository = InMemoryTaskRepository()) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.tasks

    fun addTask(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch { repository.addTask(title) }
    }

    fun toggleTask(id: Int) {
        viewModelScope.launch { repository.toggleTask(id) }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch { repository.deleteTask(id) }
    }

}