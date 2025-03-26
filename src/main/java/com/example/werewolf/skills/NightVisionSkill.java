package com.example.werewolf.skills;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

// NightVisionSkill class that allows werewolves to see the distance to other players
public class NightVisionSkill extends Skill {

    // Duration of the night vision effect in seconds
    private static final int DURATION = 60; // 60 seconds

    // Constructor for the NightVisionSkill
    public NightVisionSkill() {
        super("Night Vision");
    }

    // Method to activate the night vision skill for a player
    public void activate(Player player) {
        // Apply the night vision effect to the player
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, DURATION * 20, 0, true, false));

        // Schedule a task to remove the effect after the duration
        new BukkitRunnable() {
            @Override
            public void run() {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        }.runTaskLater(MinecraftWerewolf.getInstance(), DURATION * 20);
    }

    // Method to get the distance to another player
    public double getDistanceToPlayer(Player player, Player target) {
        return player.getLocation().distance(target.getLocation());
    }
}