package com.example.werewolf;

import org.bukkit.plugin.java.JavaPlugin;
import com.example.werewolf.commands.CommandManager;
import com.example.werewolf.config.ConfigManager;
import com.example.werewolf.game.GameManager;
import com.example.werewolf.listeners.GameListener;
import com.example.werewolf.listeners.PlayerListener;
import com.example.werewolf.listeners.ItemListener;

/**
 * Main class of the Minecraft Werewolf plugin.
 * This class extends JavaPlugin and contains methods for enabling and disabling the plugin,
 * as well as initializing the game components.
 */
public class MinecraftWerewolf extends JavaPlugin {

    private CommandManager commandManager;
    private ConfigManager configManager;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Initialize the configuration manager to load settings
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Initialize the command manager to register commands
        commandManager = new CommandManager(this);
        commandManager.registerCommands();

        // Initialize the game manager to handle game logic
        gameManager = new GameManager(this);

        // Register event listeners for game events
        getServer().getPluginManager().registerEvents(new GameListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemListener(this), this);

        // Log a message indicating that the plugin has been enabled
        getLogger().info("Minecraft Werewolf plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Perform any necessary cleanup when the plugin is disabled
        getLogger().info("Minecraft Werewolf plugin has been disabled.");
    }

    // Getter for the GameManager instance
    public GameManager getGameManager() {
        return gameManager;
    }

    // Getter for the ConfigManager instance
    public ConfigManager getConfigManager() {
        return configManager;
    }
}