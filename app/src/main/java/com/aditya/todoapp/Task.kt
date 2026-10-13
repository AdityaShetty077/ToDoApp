package com.aditya.todoapp

data class Task(
    val id : Int,
    val title : String,
    val isDone : Boolean = false
): java.io.Serializable {

}