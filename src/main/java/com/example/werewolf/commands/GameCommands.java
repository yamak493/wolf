package com.example.werewolf.commands;

import com.example.werewolf.MinecraftWerewolf;
import com.example.werewolf.game.GameManager;
import com.example.werewolf.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// GameCommands class handles game-related commands
public class GameCommands implements CommandExecutor {

    private final MinecraftWerewolf plugin;
    private final GameManager gameManager;

    // Constructor to initialize the plugin and game manager
    public GameCommands(MinecraftWerewolf plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
    }

    // Method to handle command execution
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        // Command to start the game
        if (command.getName().equalsIgnoreCase("startgame")) {
            if (gameManager.getGameState() == GameState.LOBBY) {
                gameManager.startGame();
                Bukkit.broadcastMessage("The game has started!");
            } else {
                player.sendMessage("The game is already in progress or has ended.");
            }
            return true;
        }

        // Command to stop the game
        if (command.getName().equalsIgnoreCase("stopgame")) {
            if (gameManager.getGameState() == GameState.IN_PROGRESS) {
                gameManager.stopGame();
                Bukkit.broadcastMessage("The game has been stopped.");
            } else {
                player.sendMessage("The game is not currently in progress.");
            }
            return true;
        }

        // Command to join the game
        if (command.getName().equalsIgnoreCase("joingame")) {
            if (gameManager.getGameState() == GameState.LOBBY) {
                gameManager.addPlayer(player);
                player.sendMessage("You have joined the game.");
            } else {
                player.sendMessage("You cannot join the game at this time.");
            }
            return true;
        }

        // Command to leave the game
        if (command.getName().equalsIgnoreCase("leavegame")) {
            gameManager.removePlayer(player);
            player.sendMessage("You have left the game.");
            return true;
        }

        // If command is not recognized
        player.sendMessage("Unknown command. Please use /startgame, /stopgame, /joingame, or /leavegame.");
        return false;
    }
}