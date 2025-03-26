package com.example.werewolf.roles;

// Import necessary classes
import org.bukkit.entity.Player;

// Abstract class representing a game role
public abstract class Role {
    // Player associated with this role
    protected Player player;

    // Constructor to initialize the role with a player
    public Role(Player player) {
        this.player = player;
    }

    // Abstract method to get the role name
    public abstract String getRoleName();

    // Abstract method to perform actions specific to the role
    public abstract void performRoleAction();

    // Method to get the player associated with this role
    public Player getPlayer() {
        return player;
    }

    // Method to set the player associated with this role
    public void setPlayer(Player player) {
        this.player = player;
    }
}