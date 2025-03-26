package com.example.werewolf.skills;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

// WolfSummonSkill class allows werewolves to summon wolves to attack villagers
public class WolfSummonSkill extends Skill {

    private final JavaPlugin plugin; // Reference to the main plugin class
    private final Random random = new Random(); // Random number generator for wolf summoning

    // Constructor to initialize the skill with the main plugin instance
    public WolfSummonSkill(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Method to summon wolves near a specified player
    public void summonWolves(Player werewolf, List<Player> villagers) {
        // Check if the werewolf has permission to use this skill
        if (!canUseSkill(werewolf)) {
            werewolf.sendMessage("You cannot use this skill yet!"); // Notify the player if they cannot use the skill
            return;
        }

        // Select a random villager to summon wolves near
        Player targetVillager = villagers.get(random.nextInt(villagers.size()));
        Location summonLocation = targetVillager.getLocation(); // Get the location of the selected villager

        // Summon 3 wolves at the target villager's location
        for (int i = 0; i < 3; i++) {
            Wolf wolf = (Wolf) Bukkit.getWorld(summonLocation.getWorld().getName())
                    .spawnEntity(summonLocation, EntityType.WOLF); // Spawn a wolf at the location
            wolf.setTarget(targetVillager); // Set the villager as the target for the wolf
        }

        // Notify the werewolf that the wolves have been summoned
        werewolf.sendMessage("You have summoned wolves to attack " + targetVillager.getName() + "!");

        // Start cooldown for the skill
        startCooldown(werewolf);
    }

    // Method to check if the skill can be used (e.g., based on cooldown)
    private boolean canUseSkill(Player player) {
        // Implement cooldown logic here
        return true; // Placeholder for actual cooldown check
    }

    // Method to start the cooldown for the skill
    private void startCooldown(Player player) {
        // Placeholder for cooldown duration (e.g., 3 minutes)
        int cooldownDuration = 180; // 180 seconds

        new BukkitRunnable() {
            @Override
            public void run() {
                // Logic to reset the cooldown for the player
                player.sendMessage("Your Wolf Summon skill is ready to use again!"); // Notify player when skill is ready
            }
        }.runTaskLater(plugin, cooldownDuration * 20); // Convert seconds to ticks (20 ticks = 1 second)
    }
}