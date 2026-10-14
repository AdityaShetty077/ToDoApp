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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aditya.todoapp.ui.theme.ToDoAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ToDoScreen(modifier: Modifier = Modifier,
               viewModel: TodoViewModel = viewModel()) {
    var text by rememberSaveable { mutableStateOf("") }
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    Column(modifier = modifier.padding(16.dp)) {
        TaskInput(
            text = text,
            onTextChange = { newText -> text = newText },
            onAdd = {
                viewModel.addTask(text)
                text = ""
            }
        )

        LazyColumn {
            items(tasks, key = { it.id }) { task ->
                TaskRow(
                    task = task,
                    onToggle = {
                       viewModel.toggleTask(task.id)
                    },
                    onDelete = {
                        viewModel.deleteTask(task.id)
                    }
                )
            }
        }
    }
}

@Composable
fun TaskInput(
    text: String,
    onTextChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { newText -> onTextChange(newText) },
            label = { Text("New task") },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { onAdd() }) {
            Text("Add")
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { onToggle() }
        )
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            textDecoration = if (task.isDone) TextDecoration.LineThrough else null
        )
        TextButton(onClick = { onDelete() }) {
            Text("Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoneTaskPreview() {
    ToDoAppTheme {
        TaskRow(task = Task(1, "Buy milk", isDone = true), onToggle = {}, onDelete = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TaskInputPreview() {
    ToDoAppTheme {
        TaskInput(text = "Gym", onTextChange = {}, onAdd = {})
    }
}
