package com.ait.onenightwerewolf.model.server

import com.ait.onenightwerewolf.model.GameState
import com.ait.onenightwerewolf.model.Player

data class GameRoom(
    val id: String = "",
    val code: String = "",
    var players: List<Player> = listOf(),
    val gameState: GameState = GameState.SETUP,
    val messages: List<Message> = listOf()
)

data class Message(
    val id: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val content: String = "",
    val timestamp: Long = 0L
)