package com.example.werewolf.skills;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * ShadowWalkSkill class implements the shadow walk skill for werewolves.
 * This skill allows werewolves to become invisible for a short duration.
 */
public class ShadowWalkSkill extends Skill implements Listener {

    private final JavaPlugin plugin;
    private final int duration; // Duration of the invisibility effect in seconds
    private final int cooldown; // Cooldown time in seconds

    public ShadowWalkSkill(JavaPlugin plugin) {
        this.plugin = plugin;
        this.duration = 10; // Set duration to 10 seconds
        this.cooldown = 180; // Set cooldown to 3 minutes
    }

    /**
     * Method to activate the shadow walk skill.
     * @param player The player using the skill.
     */
    public void activate(Player player) {
        // Check if the skill is on cooldown
        if (isOnCooldown(player)) {
            player.sendMessage("このスキルはクールタイム中です。");
            return;
        }

        // Apply invisibility effect to the player
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration * 20, 1));
        player.sendMessage("影歩きが発動しました！");

        // Start cooldown
        startCooldown(player);

        // Schedule a task to notify the player when the effect is about to end
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("影歩きの効果が終了します。");
            }
        }.runTaskLater(plugin, duration * 20 - 5); // Notify 5 seconds before the effect ends
    }

    /**
     * Event handler for player interactions to trigger the skill.
     * @param event The PlayerInteractEvent.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Check if the player is a werewolf and has the skill item
        if (isWerewolf(player) && isSkillItem(event.getItem())) {
            activate(player);
        }
    }

    /**
     * Method to check if the player is on cooldown for the skill.
     * @param player The player to check.
     * @return true if on cooldown, false otherwise.
     */
    private boolean isOnCooldown(Player player) {
        // Implement cooldown logic here
        return false; // Placeholder
    }

    /**
     * Method to start the cooldown for the skill.
     * @param player The player to apply cooldown to.
     */
    private void startCooldown(Player player) {
        // Implement cooldown logic here
    }

    /**
     * Method to check if the player is a werewolf.
     * @param player The player to check.
     * @return true if the player is a werewolf, false otherwise.
     */
    private boolean isWerewolf(Player player) {
        // Implement logic to check if the player is a werewolf
        return true; // Placeholder
    }

    /**
     * Method to check if the item used is the skill item.
     * @param item The item to check.
     * @return true if it is the skill item, false otherwise.
     */
    private boolean isSkillItem(ItemStack item) {
        // Implement logic to check if the item is the skill item
        return true; // Placeholder
    }
}