package com.aditya.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToDoScreen(modifier: Modifier = Modifier)
{
    var text by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Column() {
        TextField(
            value = text,
            onValueChange = {newText -> text = newText},
            label = {Text("Enter")}
        )
        Button(onClick = {
            if(text.isNotBlank()) {
                tasks = tasks + text
                text = ""
            }
        }) {
            Text("Add")
        }
        tasks.forEach { newTask -> Text(newTask) }
    }
}

