package com.ait.onenightwerewolf.model

import Team
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

enum class GameState {
    SETUP, NIGHT, DAY, VOTE, GAME_OVER
}

data class NightAction(val playerId: Int, val targetPlayerId: Int? = null, val targetPlayerId2: Int? = null)

class WerewolfViewModel : ViewModel() {
    val winningTeam = mutableStateOf<Team?>(null)

    // ...
    private val nightActions = mutableListOf<NightAction>()

    private val votes = mutableMapOf<Int, Int>()
    val gameState = mutableStateOf(GameState.SETUP)
    val showDialog: MutableState<Boolean> = mutableStateOf(false)
    val _players = mutableStateListOf<Player>()
    val players: List<Player> = _players

    val chatEnabled = mutableStateOf(false)
    val roleInformation = mutableStateOf<String?>(null)

    // Add more game logic and state management methods
    fun onRoleAction(player: Player) {
        // Handle role actions
        // Update the game state based on role actions
    }

    fun onTransitionToNextPhase() {
        when (gameState.value) {
            GameState.SETUP -> {
                setupActions()
                gameState.value = GameState.NIGHT
            }
            GameState.NIGHT -> {
                nightPhaseActions()
                gameState.value = GameState.DAY
            }
            GameState.DAY -> {
                dayPhaseActions()
                gameState.value = GameState.VOTE
            }
            GameState.VOTE -> {
                votePhaseActions()
                gameState.value = GameState.GAME_OVER
            }
            GameState.GAME_OVER -> {
                gameOverActions()
                gameState.value = GameState.SETUP
            }
        }
    }


    private fun gameOverActions() {
        // Show results, reveal player roles, etc.
        // For example, find the player(s) with the most votes, determine the winning team, etc.

        // Reveal player roles
        _players.forEach { player ->
            // Reveal role to other players, for example, by updating a shared state or sending a message
        }

        // Determine the winning team based on game rules and votes
        val winningTeam = determineWinningTeam()
        // Show the winning team and any additional game over information
    }

    private fun determineWinningTeam(): Team {
        // Get the player with the most votes
        val mostVotedPlayerId = votes.maxByOrNull { it.value }?.key
        val mostVotedPlayer = _players.find { it.id == mostVotedPlayerId }

        // Check if the villagers team won
        if (mostVotedPlayer != null && mostVotedPlayer.role is Role.Werewolf) {
            // The villagers team wins if a werewolf is eliminated
            return Team.Villagers
        } else if (_players.none { it.role is Role.Werewolf }) {
            // The villagers team also wins if there are no werewolves in the game
            return Team.Villagers
        }

        // In all other cases, the werewolves team wins
        return Team.Werewolves
    }

    private fun votePhaseActions() {
        // Initialize voting state
        // For example, create an empty voting list
        val votes = mutableMapOf<Int, Int>()
        _players.forEach { player ->
            votes[player.id] = 0
        }

        // Later, you will need to handle vote processing, such as counting votes, determining the result, etc.
    }

    private fun dayPhaseActions() {
        chatEnabled.value = true

        // Show role-specific UI elements or instructions
        _players.forEach { player ->
            when (player.role) {
                is Role.Werewolf -> {
                    // Show werewolf-specific UI elements or instructions
                    roleInformation.value = "You are a Werewolf. The other Werewolves are: ${getOtherWerewolves(player)}"
                }
                is Role.Seer -> {
                    // Show seer-specific UI elements or instructions
                    roleInformation.value = "You are the Seer. The role you peeked at during the night phase is: ${getPeekedRole(player)}"
                }
                is Role.Robber -> {
                    // Show robber-specific UI elements or instructions
                    roleInformation.value = "You are the Robber. The role you swapped with during the night phase is: ${getSwappedRole(player)}"
                }
                is Role.Troublemaker -> {
                    // Show troublemaker-specific UI elements or instructions
                    roleInformation.value = "You are the Troublemaker. The roles you swapped during the night phase are: ${getSwappedRoles(player)}"
                }
                else -> {
                    // No action needed for other roles
                    roleInformation.value = "You are a Villager. You have no special actions."
                }
            }
        }
    }

    private fun getSwappedRoles(player: Player): String {
        val action = nightActions.find { it.playerId == player.id }
        return if (action != null && action.targetPlayerId != null && action.targetPlayerId2 != null) {
            "Player ${action.targetPlayerId} and Player ${action.targetPlayerId2}"
        } else {
            "No roles swapped"
        }
    }

    private fun getSwappedRole(player: Player): String {
        val action = nightActions.find { it.playerId == player.id }
        return if (action != null && action.targetPlayerId != null) {
            val targetPlayer = _players.find { it.id == action.targetPlayerId }
            "Role: ${targetPlayer?.role?.toString() ?: "Unknown"}"
        } else {
            "No role swapped"
        }
    }

    private fun getPeekedRole(player: Player): String {
        val action = nightActions.find { it.playerId == player.id }
        return if (action != null && action.targetPlayerId != null) {
            val targetPlayer = _players.find { it.id == action.targetPlayerId }
            "Role: ${targetPlayer?.role?.toString() ?: "Unknown"}"
        } else {
            "No role peeked"
        }
    }

    private fun getOtherWerewolves(player: Player): String {
        val otherWerewolves = _players.filter { it.role is Role.Werewolf && it.id != player.id }
        return if (otherWerewolves.isNotEmpty()) {
            otherWerewolves.joinToString(separator = ", ") { "Player ${it.id}" }
        } else {
            "No other werewolves"
        }
    }


    private fun nightPhaseActions() {
        // Handle role-specific actions during the night phase
        // For example, activate/deactivate UI elements based on roles
        _players.forEach { player ->
            when (player.role) {
                is Role.Werewolf -> {
                    // Werewolf-specific actions
                    // For example, reveal other werewolves
                }
                is Role.Seer -> {
                    // Seer-specific actions
                    // For example, allow the seer to peek at another player's role
                }
                is Role.Robber -> {
                    // Robber-specific actions
                    // For example, allow the robber to swap roles with another player
                }
                is Role.Troublemaker -> {
                    // Troublemaker-specific actions
                    // For example, allow the troublemaker to swap roles between two other players
                }
                else -> {
                    // No action needed for other roles
                }
            }
        }
    }

    private fun setupActions() {
        // Shuffle roles
        val shuffledRoles = allRoles.shuffled()

        // Assign roles to players
        _players.forEachIndexed { index, player ->
            player.role = shuffledRoles[index]
        }
    }
    
    
}