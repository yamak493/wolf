package com.example.werewolf.skills;

// Abstract class representing a skill that can be used by werewolves
public abstract class Skill {
    
    // The name of the skill
    private String name;
    
    // The cooldown time for the skill in seconds
    private int cooldownTime;

    // Constructor to initialize the skill with a name and cooldown time
    public Skill(String name, int cooldownTime) {
        this.name = name;
        this.cooldownTime = cooldownTime;
    }

    // Method to get the name of the skill
    public String getName() {
        return name;
    }

    // Method to get the cooldown time of the skill
    public int getCooldownTime() {
        return cooldownTime;
    }

    // Abstract method to be implemented by subclasses for skill activation
    public abstract void activateSkill();

    // Method to check if the skill is on cooldown
    public boolean isOnCooldown(long lastUsedTime) {
        return (System.currentTimeMillis() - lastUsedTime) < (cooldownTime * 1000);
    }
}