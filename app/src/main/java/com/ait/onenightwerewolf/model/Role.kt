package com.ait.onenightwerewolf.model

public val allRoles = listOf(Role.Villager, Role.Werewolf, Role.Seer, Role.Robber, Role.Troublemaker)
sealed class Role {
    // Add role-specific actions and state management

    object Villager : Role() {
        // Villager-specific actions
    }
    object Werewolf : Role() {
        // Werewolf-specific actions
    }
    object Seer : Role() {
        // Seer-specific actions
    }
    object Robber : Role() {
        // Robber-specific actions
    }
    object Troublemaker : Role() {
        // Troublemaker-specific actions
    }
}