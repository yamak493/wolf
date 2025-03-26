// FangSkill.java
package com.example.werewolf.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

// FangSkill class that extends the Skill class
public class FangSkill extends Skill implements Listener {

    // Constructor for FangSkill
    public FangSkill(JavaPlugin plugin) {
        super(plugin);
    }

    // Method to activate the fang skill
    public void activateFang(Player player) {
        // Create a new iron axe with sharpness enchantment
        ItemStack fang = new ItemStack(Material.IRON_AXE);
        fang.addEnchantment(Enchantment.DAMAGE_ALL, 2); // Sharpness level 2
        player.getInventory().addItem(fang); // Give the fang to the player
        player.sendMessage("You have received the Fang skill!"); // Notify the player
    }

    // Event handler for player interactions
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Check if the player has the fang skill activated
        if (isFangSkillActive(player)) {
            activateFang(player); // Activate the fang skill
        }
    }

    // Method to check if the fang skill is active for the player
    private boolean isFangSkillActive(Player player) {
        // Logic to determine if the fang skill is active
        // This can be based on player state, role, or other conditions
        return true; // Placeholder for actual logic
    }
}