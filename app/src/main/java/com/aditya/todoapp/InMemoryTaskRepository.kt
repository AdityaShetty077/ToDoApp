package com.aditya.todoapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryTaskRepository : TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    override val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    private var nextId = 1


    override suspend fun addTask(title: String) {
        _tasks.value += Task(nextId,title)
        nextId++
    }

    override suspend fun toggleTask(id: Int) {
        _tasks.value = _tasks.value.map { if (it.id == id) it.copy(isDone = !it.isDone) else it }
    }

    override suspend fun deleteTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

}