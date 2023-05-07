package com.ait.onenightwerewolf.ui.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.ait.onenightwerewolf.model.Role

@Composable
fun CreatePlayerDialog(
    showDialog: Boolean,
    onCreatePlayer: (name: String) -> Unit,
    onCancel: () -> Unit
) {

    var playerName by remember { mutableStateOf("") }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            title = { Text("Create Player") },
            text = {
                TextField(
                    value = playerName,
                    onValueChange = { playerName = it },
                    label = { Text("Player name") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = { onCreatePlayer(playerName) }) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancel() }) {
                    Text("Cancel")
                }
            }
        )
    }
}


