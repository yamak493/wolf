package com.example.werewolf.game;

import com.example.werewolf.MinecraftWerewolf;
import com.example.werewolf.config.GameConfig;
import com.example.werewolf.listeners.GameListener;
import com.example.werewolf.listeners.PlayerListener;
import com.example.werewolf.listeners.ItemListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final MinecraftWerewolf plugin; // Reference to the main plugin class
    private GameState gameState; // Current state of the game
    private final List<Player> players; // List of players participating in the game
    private final GameConfig gameConfig; // Game configuration settings

    public GameManager(MinecraftWerewolf plugin) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.gameConfig = plugin.getGameConfig(); // Load game configuration
        this.gameState = GameState.LOBBY; // Initial game state
    }

    // Method to start the game
    public void startGame() {
        if (gameState != GameState.LOBBY) {
            plugin.getLogger().warning("Game cannot be started from the current state: " + gameState);
            return;
        }
        gameState = GameState.IN_PROGRESS; // Change game state to in progress
        Bukkit.broadcastMessage("The game has started!"); // Notify players
        // Additional game start logic (e.g., assigning roles, initializing tasks)
    }

    // Method to stop the game
    public void stopGame() {
        if (gameState == GameState.ENDED) {
            plugin.getLogger().warning("Game is already ended.");
            return;
        }
        gameState = GameState.ENDED; // Change game state to ended
        Bukkit.broadcastMessage("The game has ended!"); // Notify players
        // Additional game end logic (e.g., resetting player states)
    }

    // Method to add a player to the game
    public void addPlayer(Player player) {
        if (players.contains(player)) {
            player.sendMessage("You are already in the game!");
            return;
        }
        players.add(player); // Add player to the list
        player.sendMessage("You have joined the game!"); // Notify player
    }

    // Method to remove a player from the game
    public void removePlayer(Player player) {
        if (!players.contains(player)) {
            player.sendMessage("You are not in the game!");
            return;
        }
        players.remove(player); // Remove player from the list
        player.sendMessage("You have left the game!"); // Notify player
    }

    // Method to check win conditions
    public void checkWinConditions() {
        // Logic to determine if villagers or werewolves have won
        // This could involve checking player roles and task completion
    }

    // Getters for game state and players
    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }
}