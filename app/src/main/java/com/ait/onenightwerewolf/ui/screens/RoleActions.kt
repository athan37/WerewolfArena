package com.ait.onenightwerewolf.ui.screens

import androidx.compose.runtime.Composable
import com.ait.onenightwerewolf.model.Player
import com.ait.onenightwerewolf.model.Role

//role-specific actions, UI components, and instructions for each role:
@Composable
fun RoleActions(player: Player, onRoleAction: (Player) -> Unit) {
    when (player.role) {
        is Role.Villager -> VillagerActions(player, onRoleAction)
        is Role.Werewolf -> WerewolfActions(player, onRoleAction)
        is Role.Seer -> SeerActions(player, onRoleAction)
        is Role.Robber -> RobberActions(player, onRoleAction)
        is Role.Troublemaker -> TroublemakerActions(player, onRoleAction)
    }
}

@Composable
fun TroublemakerActions(player: Player, onRoleAction: (Player) -> Unit) {

}

@Composable
fun RobberActions(player: Player, onRoleAction: (Player) -> Unit) {

}

@Composable
fun SeerActions(player: Player, onRoleAction: (Player) -> Unit) {

}

@Composable
fun WerewolfActions(player: Player, onRoleAction: (Player) -> Unit) {

}

@Composable
fun VillagerActions(player: Player, onRoleAction: (Player) -> Unit) {

}
