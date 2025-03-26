package com.example.werewolf.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

// This utility class provides methods for managing locations and coordinates within the game world.
public class LocationUtil {

    // Converts a string representation of coordinates into a Location object.
    public static Location stringToLocation(String locationString, World world) {
        String[] parts = locationString.split(",");
        if (parts.length != 3) {
            return null; // Invalid format
        }
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        return new Location(world, x, y, z);
    }

    // Converts a Location object into a string representation.
    public static String locationToString(Location location) {
        return location.getX() + "," + location.getY() + "," + location.getZ();
    }

    // Teleports a player to a specified location.
    public static void teleportPlayer(Player player, Location location) {
        if (player != null && location != null) {
            player.teleport(location);
        }
    }

    // Checks if two locations are the same.
    public static boolean isSameLocation(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            return false;
        }
        return loc1.getWorld().equals(loc2.getWorld()) &&
               loc1.getBlockX() == loc2.getBlockX() &&
               loc1.getBlockY() == loc2.getBlockY() &&
               loc1.getBlockZ() == loc2.getBlockZ();
    }
}