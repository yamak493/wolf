// This file defines the Werewolf role in the Minecraft Werewolf game plugin.
// It extends the Role class and implements specific behaviors and skills for werewolf players.

package com.example.werewolf.roles;

import com.example.werewolf.skills.ShadowWalkSkill;
import com.example.werewolf.skills.WolfSummonSkill;
import com.example.werewolf.skills.FangSkill;
import com.example.werewolf.skills.NightVisionSkill;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Werewolf extends Role {
    // List to hold the skills available to the werewolf
    private List<Skill> skills;

    // Constructor for the Werewolf class
    public Werewolf(Player player) {
        super(player); // Call the constructor of the Role class
        this.skills = new ArrayList<>(); // Initialize the skills list
        initializeSkills(); // Set up the werewolf's skills
    }

    // Method to initialize the werewolf's skills
    private void initializeSkills() {
        // Add the specific skills to the werewolf's skill list
        skills.add(new ShadowWalkSkill(this)); // Shadow walk skill
        skills.add(new WolfSummonSkill(this)); // Wolf summon skill
        skills.add(new FangSkill(this)); // Fang skill
        skills.add(new NightVisionSkill(this)); // Night vision skill
    }

    // Method to get the list of skills for the werewolf
    public List<Skill> getSkills() {
        return skills;
    }

    // Method to use a specific skill
    public void useSkill(Skill skill) {
        if (skills.contains(skill)) {
            skill.activate(); // Activate the skill
        } else {
            // Handle the case where the skill is not available
            player.sendMessage("You do not have this skill!"); // Notify the player
        }
    }

    // Method to handle the werewolf's special actions
    public void performSpecialAction() {
        // Implement any special actions for the werewolf here
    }

    // Override the method to define the werewolf's victory conditions
    @Override
    public boolean checkVictoryCondition() {
        // Define the victory condition for the werewolf role
        return false; // Placeholder, implement actual logic
    }

    // Method to get the werewolf's inventory items
    public ItemStack[] getInventoryItems() {
        // Return the items that the werewolf starts with
        return new ItemStack[] {
            // Define the starting items for the werewolf
        };
    }
}