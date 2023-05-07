package com.ait.onenightwerewolf.ui.screens

import ChatUI
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ait.onenightwerewolf.model.*

@Composable
fun OneNightWerewolf(viewModel: WerewolfViewModel) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "One Night Werewolf", style = MaterialTheme.typography.displaySmall)
            PlayerList(players = viewModel.players, {})

            Spacer(modifier = Modifier.weight(1f))


            when (viewModel.gameState.value) {
                GameState.SETUP -> {
                    // Display game setup UI
                    CreatePlayerDialog(
                        showDialog = viewModel.showDialog.value,
                        onCreatePlayer = { name ->
                            val newPlayer = Player(id = viewModel.players.size, name = name, role = assignRandomRole(allRoles.toMutableList()), level = 1)
                            viewModel._players.add(newPlayer)
                            viewModel.showDialog.value = false
                        },
                        onCancel = { viewModel.showDialog.value = false }
                    )

                    FloatingActionButton(
                        onClick = { viewModel.showDialog.value = true },
                        content = { Icon(Icons.Default.Add, contentDescription = "Add Player") }
                    )
                }
                GameState.NIGHT -> {
                    // Display night phase UI
                    NightPhaseUI(viewModel)
                }
                GameState.DAY -> {
                    // Display day phase UI
                    DayPhaseUI(viewModel)
                }
                GameState.VOTE -> {
                    // Display voting UI
                    VotePhaseUI(viewModel)
                }
                GameState.GAME_OVER -> {
                    // Display game over UI
                    GameOverUI(viewModel)
                }
                else -> {}
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { viewModel.onTransitionToNextPhase() },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Next Phase")
                }
            }
        }

    }

}

fun assignRandomRole(availableRoles: MutableList<Role>): Role {
    val index = (0 until availableRoles.size).random()
    val role = availableRoles[index]
    availableRoles.removeAt(index)
    return role
}

@Composable
fun PlayerList(players: List<Player>) {
    LazyColumn {
        items(players) { player ->
            Text("Player ${player.id}: ${player.name} - Role: ${player.role}")
        }
    }
}

@Composable
fun NightPhaseUI(viewModel: WerewolfViewModel) {
    // Show role actions for each player based on their role
    // This is just an example; you may need to create separate Composables for each role's action
    Text(text = viewModel.roleInformation.value ?: "No information available.")
}

@Composable
fun DayPhaseUI(viewModel: WerewolfViewModel) {
    // Allow players to discuss and share information
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Day Phase", modifier = Modifier.padding(16.dp))

        if (viewModel.chatEnabled.value) {
            ChatUI(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { viewModel.onTransitionToNextPhase() },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text("Next Phase")
            }
        }
    }
}

@Composable
fun VotePhaseUI(viewModel: WerewolfViewModel) {
    // Allow players to vote on who they believe is a werewolf
    Column {
        Text("Vote Phase")
        PlayerList(viewModel.players)
        // Implement voting functionality here
    }
}

@Composable
fun GameOverUI(viewModel: WerewolfViewModel) {
    // Show the results and reveal player roles
    Column {
        Text("Game Over")
        Text("Winning Team: ${viewModel.winningTeam.value}")
        PlayerList(viewModel.players)
    }
}

