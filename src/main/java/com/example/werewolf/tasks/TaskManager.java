package com.example.werewolf.tasks;

import com.example.werewolf.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * TaskManager class manages the tasks that villagers must complete to win the game.
 * It tracks the progress of tasks and checks for completion.
 */
public class TaskManager {

    // Map to hold the task progress for each player
    private Map<Player, Integer> taskProgress = new HashMap<>();
    private GameManager gameManager;

    // Constructor to initialize the TaskManager with a reference to GameManager
    public TaskManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Starts the coal collection task for villagers.
     * This method can be called when the game starts.
     */
    public void startCoalCollectionTask() {
        // Initialize task progress for all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            taskProgress.put(player, 0);
        }

        // Schedule a repeating task to check for task completion
        new BukkitRunnable() {
            @Override
            public void run() {
                checkTaskCompletion();
            }
        }.runTaskTimer(gameManager.getPlugin(), 0, 20); // Check every second
    }

    /**
     * Adds coal to the player's task progress.
     * @param player The player who collected coal.
     * @param amount The amount of coal collected.
     */
    public void addCoalToProgress(Player player, int amount) {
        if (taskProgress.containsKey(player)) {
            int currentProgress = taskProgress.get(player);
            currentProgress += amount;
            taskProgress.put(player, currentProgress);
            // Notify player of their progress
            player.sendMessage("You have collected " + currentProgress + " coal.");
        }
    }

    /**
     * Checks if any player has completed the task.
     * If a player has collected enough coal, they win the game.
     */
    private void checkTaskCompletion() {
        for (Map.Entry<Player, Integer> entry : taskProgress.entrySet()) {
            Player player = entry.getKey();
            int progress = entry.getValue();

            // Check if the player has collected enough coal (30 in this case)
            if (progress >= 30) {
                // Notify all players of the victory
                Bukkit.broadcastMessage(player.getName() + " has completed the coal collection task! Villagers win!");
                gameManager.endGame(); // End the game
                return; // Exit after a win is detected
            }
        }
    }

    /**
     * Resets the task progress for all players.
     * This method can be called when the game restarts.
     */
    public void resetTasks() {
        taskProgress.clear();
    }
}