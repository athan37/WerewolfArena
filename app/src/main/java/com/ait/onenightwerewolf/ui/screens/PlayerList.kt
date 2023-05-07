package com.ait.onenightwerewolf.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.ait.onenightwerewolf.model.Player

@Composable
fun PlayerList(players: List<Player>, onPlayerClick: (Player) -> Unit) {
    LazyColumn {
        items(players) { player ->
            PlayerItem(player = player, onClick = { onPlayerClick(player) })
        }
    }
}