package com.example.werewolf.commands;

import com.example.werewolf.MinecraftWerewolf;
import com.example.werewolf.config.GameConfig;
import com.example.werewolf.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    private final MinecraftWerewolf plugin;
    private final GameManager gameManager;
    private final GameConfig gameConfig;

    public AdminCommands(MinecraftWerewolf plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        this.gameConfig = plugin.getGameConfig();
    }

    // Command to start the game
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the player has permission to execute admin commands
        if (!player.hasPermission("werewolf.admin")) {
            player.sendMessage("You do not have permission to execute this command.");
            return true;
        }

        // Handle different admin commands
        if (args.length == 0) {
            player.sendMessage("Usage: /admin <start|stop|setwerewolves|settasks>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                gameManager.startGame();
                player.sendMessage("The game has been started.");
                break;

            case "stop":
                gameManager.stopGame();
                player.sendMessage("The game has been stopped.");
                break;

            case "setwerewolves":
                if (args.length < 2) {
                    player.sendMessage("Usage: /admin setwerewolves <number>");
                    return true;
                }
                try {
                    int number = Integer.parseInt(args[1]);
                    gameConfig.setWerewolfCount(number);
                    player.sendMessage("The number of werewolves has been set to " + number);
                } catch (NumberFormatException e) {
                    player.sendMessage("Please enter a valid number.");
                }
                break;

            case "settasks":
                if (args.length < 2) {
                    player.sendMessage("Usage: /admin settasks <number>");
                    return true;
                }
                try {
                    int number = Integer.parseInt(args[1]);
                    gameConfig.setCoalRequired(number);
                    player.sendMessage("The number of coal required has been set to " + number);
                } catch (NumberFormatException e) {
                    player.sendMessage("Please enter a valid number.");
                }
                break;

            default:
                player.sendMessage("Unknown command. Usage: /admin <start|stop|setwerewolves|settasks>");
                break;
        }

        return true;
    }
}