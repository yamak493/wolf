package com.example.werewolf.listeners;

import com.example.werewolf.game.GameManager;
import com.example.werewolf.game.PlayerManager;
import com.example.werewolf.roles.Role;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class GameListener implements Listener {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    // Constructor to initialize GameManager and PlayerManager
    public GameListener(GameManager gameManager, PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    // Event handler for player joining the game
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Notify player of game rules and status
        player.sendMessage(ChatColor.GREEN + "Welcome to the Werewolf game! Please wait for the game to start.");
        // Add player to the game
        gameManager.addPlayer(player);
    }

    // Event handler for player quitting the game
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // Remove player from the game
        gameManager.removePlayer(player);
    }

    // Event handler for player death
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        // Handle player death in the game
        gameManager.handlePlayerDeath(player);
    }

    // Event handler for player interaction with items
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Check if the player is using a skill item
        if (playerManager.isWerewolf(player)) {
            // Handle skill activation
            playerManager.activateSkill(player, event.getItem());
        }
    }

    // Event handler for entity damage (to prevent villagers from damaging each other)
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Prevent damage if the game is in progress and the player is a villager
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (playerManager.isVillager(player) && gameManager.isGameInProgress()) {
                event.setCancelled(true);
            }
        }
    }

    // Event handler for block breaking (villagers can break blocks to complete tasks)
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isVillager(player) && gameManager.isGameInProgress()) {
            // Allow block breaking for task completion
            playerManager.handleBlockBreak(player, event.getBlock());
        } else {
            event.setCancelled(true); // Prevent breaking if not allowed
        }
    }

    // Event handler for block placing (villagers can place blocks for tasks)
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (playerManager.isVillager(player) && gameManager.isGameInProgress()) {
            // Allow block placing for task completion
            playerManager.handleBlockPlace(player, event.getBlock());
        } else {
            event.setCancelled(true); // Prevent placing if not allowed
        }
    }
}