package com.example.werewolf.game;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * PlayerManager class manages player roles and actions during the game.
 */
public class PlayerManager {
    
    // Map to store player roles
    private Map<UUID, Role> playerRoles = new HashMap<>();
    
    // Method to assign a role to a player
    public void assignRole(Player player, Role role) {
        playerRoles.put(player.getUniqueId(), role);
        // Notify player of their assigned role
        player.sendMessage("You have been assigned the role of: " + role.getName());
    }

    // Method to get a player's role
    public Role getRole(Player player) {
        return playerRoles.get(player.getUniqueId());
    }

    // Method to check if a player is a werewolf
    public boolean isWerewolf(Player player) {
        Role role = getRole(player);
        return role instanceof Werewolf; // Assuming Werewolf extends Role
    }

    // Method to check if a player is a villager
    public boolean isVillager(Player player) {
        Role role = getRole(player);
        return role instanceof Villager; // Assuming Villager extends Role
    }

    // Method to remove a player from the game
    public void removePlayer(Player player) {
        playerRoles.remove(player.getUniqueId());
        // Notify player of their removal
        player.sendMessage("You have been removed from the game.");
    }

    // Method to clear all players' roles (e.g., when restarting the game)
    public void clearRoles() {
        playerRoles.clear();
    }
}