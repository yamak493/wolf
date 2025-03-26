package com.example.werewolf.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

// ConfigManager class to handle loading and saving configuration files
public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    private File configFile;

    // Constructor to initialize the ConfigManager with the main plugin instance
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        createConfigFile();
    }

    // Method to create the configuration file if it doesn't exist
    private void createConfigFile() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false); // Save the default config file from resources
        }
        config = YamlConfiguration.loadConfiguration(configFile); // Load the configuration
    }

    // Method to get the configuration
    public FileConfiguration getConfig() {
        return config;
    }

    // Method to save the configuration to the file
    public void saveConfig() {
        try {
            config.save(configFile); // Save the current configuration to the file
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
        }
    }

    // Method to reload the configuration from the file
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile); // Reload the configuration
    }
}