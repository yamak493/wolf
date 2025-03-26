// PlayerListener.java
package com.example.werewolf.listeners;

import com.example.werewolf.game.GameManager;
import com.example.werewolf.game.PlayerManager;
import com.example.werewolf.roles.Role;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class PlayerListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    // Constructor to initialize GameManager and PlayerManager
    public PlayerListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    // Event handler for when a player joins the game
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Notify all players that a new player has joined
        player.sendMessage(ChatColor.GREEN + "Welcome to the Werewolf game!");
        
        // Add player to the game
        gameManager.addPlayer(player);
        
        // Assign a role to the player
        Role role = playerManager.assignRole(player);
        player.sendMessage(ChatColor.YELLOW + "You are now a " + role.getName() + "!");
    }

    // Event handler for when a player leaves the game
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        // Remove player from the game
        gameManager.removePlayer(player);
        
        // Notify all players that a player has left
        gameManager.broadcastMessage(ChatColor.RED + player.getName() + " has left the game.");
    }
}