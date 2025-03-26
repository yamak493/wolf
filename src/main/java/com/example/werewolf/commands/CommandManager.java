// CommandManager.java
package com.example.werewolf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

// CommandManager class to handle command registration and execution
public class CommandManager implements CommandExecutor {

    private final JavaPlugin plugin;

    // Constructor to initialize the CommandManager with the main plugin instance
    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
        registerCommands();
    }

    // Method to register all commands
    private void registerCommands() {
        // Register game commands
        plugin.getCommand("startgame").setExecutor(new GameCommands(plugin));
        plugin.getCommand("stopgame").setExecutor(new GameCommands(plugin));
        plugin.getCommand("join").setExecutor(new GameCommands(plugin));
        plugin.getCommand("leave").setExecutor(new GameCommands(plugin));

        // Register admin commands
        plugin.getCommand("setwerewolves").setExecutor(new AdminCommands(plugin));
        plugin.getCommand("settasks").setExecutor(new AdminCommands(plugin));
        plugin.getCommand("setcooldown").setExecutor(new AdminCommands(plugin));
        plugin.getCommand("forceexit").setExecutor(new AdminCommands(plugin));
    }

    // Method to handle command execution
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the command is valid and delegate to the appropriate command class
        if (command.getName().equalsIgnoreCase("startgame")) {
            return new GameCommands(plugin).onCommand(sender, command, label, args);
        } else if (command.getName().equalsIgnoreCase("stopgame")) {
            return new GameCommands(plugin).onCommand(sender, command, label, args);
        } else if (command.getName().equalsIgnoreCase("setwerewolves")) {
            return new AdminCommands(plugin).onCommand(sender, command, label, args);
        }
        // Add more command handling as needed
        return false;
    }
}