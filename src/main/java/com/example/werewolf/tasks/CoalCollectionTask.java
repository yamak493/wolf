package com.example.werewolf.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * CoalCollectionTask class implements the task for villagers to collect coal.
 * The task is completed when a specified amount of coal is placed in a designated chest.
 */
public class CoalCollectionTask {

    // The required amount of coal to complete the task
    private static final int REQUIRED_COAL_AMOUNT = 30;

    // Map to track the coal collected by each player
    private Map<Player, Integer> playerCoalCount = new HashMap<>();

    // The location of the chest where coal should be deposited
    private Location chestLocation;

    /**
     * Constructor to initialize the CoalCollectionTask with the chest location.
     *
     * @param chestLocation The location of the chest for coal collection.
     */
    public CoalCollectionTask(Location chestLocation) {
        this.chestLocation = chestLocation;
    }

    /**
     * Starts the coal collection task for a player.
     *
     * @param player The player participating in the task.
     */
    public void startTask(Player player) {
        playerCoalCount.put(player, 0);
        player.sendMessage("You have started the coal collection task! Collect " + REQUIRED_COAL_AMOUNT + " coal.");
        
        // Schedule a task to check for completion every few seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                checkTaskCompletion(player);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("MinecraftWerewolf"), 0, 20); // Check every second
    }

    /**
     * Checks if the player has completed the coal collection task.
     *
     * @param player The player to check.
     */
    private void checkTaskCompletion(Player player) {
        int collected = playerCoalCount.get(player);
        
        // Check if the player has collected enough coal
        if (collected >= REQUIRED_COAL_AMOUNT) {
            player.sendMessage("Congratulations! You have completed the coal collection task.");
            // Here you can add logic to reward the player or update the game state
            playerCoalCount.remove(player); // Remove player from tracking
            this.cancel(); // Stop the task check
        }
    }

    /**
     * Method to add coal to the player's count when they deposit it in the chest.
     *
     * @param player The player depositing coal.
     * @param amount The amount of coal deposited.
     */
    public void addCoal(Player player, int amount) {
        if (playerCoalCount.containsKey(player)) {
            int currentCount = playerCoalCount.get(player);
            playerCoalCount.put(player, currentCount + amount);
            player.sendMessage("You have deposited " + amount + " coal. Total: " + (currentCount + amount));
        }
    }

    /**
     * Gets the location of the chest for coal collection.
     *
     * @return The location of the chest.
     */
    public Location getChestLocation() {
        return chestLocation;
    }
}