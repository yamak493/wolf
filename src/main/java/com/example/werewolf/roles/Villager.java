package com.example.werewolf.roles;

import com.example.werewolf.game.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Villager extends Role {

    // Constructor for the Villager role
    public Villager(Player player) {
        super(player);
    }

    // Method to handle the villager's task completion
    public void completeTask(ItemStack item) {
        // Check if the item is coal
        if (item.getType() == Material.COAL) {
            // Logic to add coal to the task progress
            PlayerManager.addCoalToTask(player, item.getAmount());
            // Notify the player of task progress
            player.sendMessage("You have added " + item.getAmount() + " coal to the task!");
        } else {
            player.sendMessage("This is not the correct item for the task.");
        }
    }

    // Method to check if the villager has completed their task
    public boolean hasCompletedTask() {
        return PlayerManager.hasVillagerCompletedTask(player);
    }

    // Method to handle the villager's victory condition
    public void checkVictory() {
        if (hasCompletedTask()) {
            // Logic for villager victory
            player.sendMessage("Congratulations! You have completed the task and won the game!");
            // Additional victory logic can be implemented here
        }
    }
}