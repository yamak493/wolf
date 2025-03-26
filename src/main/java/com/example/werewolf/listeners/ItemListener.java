// This file is responsible for handling item interactions in the Werewolf game plugin.
// It listens for events related to item usage and manages the activation of skills and tasks.

package com.example.werewolf.listeners;

import com.example.werewolf.game.GameManager;
import com.example.werewolf.roles.Werewolf;
import com.example.werewolf.skills.Skill;
import com.example.werewolf.utils.ItemFactory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    private final GameManager gameManager;

    // Constructor to initialize the GameManager
    public ItemListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // Event handler for player interactions with items
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Get the item the player is interacting with
        ItemStack item = event.getItem();

        // Check if the item is not null and the player is in a game
        if (item != null && gameManager.isPlayerInGame(event.getPlayer())) {
            // Check if the item is a skill item
            if (ItemFactory.isSkillItem(item)) {
                // Get the player who interacted with the item
                Werewolf werewolf = gameManager.getWerewolfByPlayer(event.getPlayer());

                // If the player is a werewolf, activate the corresponding skill
                if (werewolf != null) {
                    Skill skill = ItemFactory.getSkillFromItem(item);
                    if (skill != null) {
                        skill.activate(werewolf);
                        // Remove the item from the player's inventory after use
                        event.getPlayer().getInventory().remove(item);
                    }
                }
            }
        }
    }
}