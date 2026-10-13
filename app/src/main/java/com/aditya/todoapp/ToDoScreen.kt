package com.aditya.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun ToDoScreen(modifier: Modifier = Modifier)
{
    var text by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var nextId by remember { mutableStateOf(1) }

    Column(modifier = modifier.padding(16.dp)) {
       TextField(
           value = text,
           onValueChange = {newText -> text = newText},
           label = {Text("Enter Text")}
       )
        Button(
            onClick = {
                if(text.isNotBlank())
                {
                    tasks = tasks + Task(nextId,text)
                    text = ""
                    nextId ++
                }
            }
        ) {
            Text("Add Tasks")
        }
        LazyColumn() {
            items(tasks, key = {it.id}, itemContent = {
                task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )  {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = {
                            tasks = tasks.map {
                                if(it.id==task.id)
                                {
                                  it.copy(isDone = !it.isDone)

                                }else
                                {
                                    it
                                }
                            }

                        }
                    )
                    Text(text = task.title,
                        modifier = Modifier.weight(1f),
                        textDecoration = if (task.isDone) TextDecoration.LineThrough else null)


                    TextButton(onClick = {
                        tasks = tasks.filter { it.id !=task.id }
                    }) {
                        Text("Delete")
                    }
                }

            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreensPreview() {
    ToDoScreen()
}


