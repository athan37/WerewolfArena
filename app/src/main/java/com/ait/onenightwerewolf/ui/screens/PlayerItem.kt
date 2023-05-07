package com.ait.onenightwerewolf.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ait.onenightwerewolf.model.Player

@Composable
fun PlayerItem(player: Player, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Text(text = player.name, modifier = Modifier.weight(1f))
        Text(text = "Level: ${player.level}", textAlign = TextAlign.Center)
        Text(text = player.role::class.simpleName ?: "", textAlign = TextAlign.End)
    }
}