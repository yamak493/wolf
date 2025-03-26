package com.example.werewolf.config;

// Import necessary classes
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class GameConfig {
    // Default values for the game configuration
    private int werewolfCount = 1; // Default number of werewolves
    private int coalRequired = 30; // Default number of coal required for villagers to win
    private int skillCooldown = 180; // Default cooldown time for werewolf skills in seconds

    // Reference to the plugin's configuration
    private final JavaPlugin plugin;

    // Constructor to initialize the GameConfig with the plugin instance
    public GameConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig(); // Load configuration values from the config file
    }

    // Load configuration values from the config.yml file
    private void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        // Load values from the configuration file, using defaults if not set
        werewolfCount = config.getInt("werewolfCount", werewolfCount);
        coalRequired = config.getInt("coalRequired", coalRequired);
        skillCooldown = config.getInt("skillCooldown", skillCooldown);
    }

    // Getters for the configuration values
    public int getWerewolfCount() {
        return werewolfCount;
    }

    public int getCoalRequired() {
        return coalRequired;
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

    // Method to save the configuration values back to the config.yml file
    public void saveConfig() {
        FileConfiguration config = plugin.getConfig();
        config.set("werewolfCount", werewolfCount);
        config.set("coalRequired", coalRequired);
        config.set("skillCooldown", skillCooldown);
        plugin.saveConfig(); // Save the configuration to the file
    }
}