package com.aditya.todoapp

import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    val tasks: StateFlow<List<Task>>
    suspend fun addTask(title: String)
    suspend fun toggleTask(id: Int)
    suspend fun deleteTask(id: Int)
}