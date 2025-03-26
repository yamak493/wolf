package com.example.werewolf.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * MessageUtil is a utility class that provides methods for sending messages to players.
 * It includes methods for sending game notifications, instructions, and other messages.
 */
public class MessageUtil {

    /**
     * Sends a message to a specific player.
     *
     * @param player The player to whom the message will be sent.
     * @param message The message to be sent.
     */
    public static void sendMessage(Player player, String message) {
        player.sendMessage(formatMessage(message));
    }

    /**
     * Broadcasts a message to all players on the server.
     *
     * @param message The message to be broadcasted.
     */
    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(formatMessage(message));
    }

    /**
     * Formats a message by adding a prefix and color.
     *
     * @param message The original message.
     * @return The formatted message.
     */
    private static String formatMessage(String message) {
        return ChatColor.GOLD + "[Werewolf] " + ChatColor.RESET + message;
    }

    /**
     * Sends a game start message to all players.
     */
    public static void sendGameStartMessage() {
        broadcastMessage("The game has started! Good luck to all players!");
    }

    /**
     * Sends a game end message to all players.
     *
     * @param winner The role of the winning team (e.g., "Villagers" or "Werewolves").
     */
    public static void sendGameEndMessage(String winner) {
        broadcastMessage("The game has ended! " + winner + " have won!");
    }

    /**
     * Sends a task completion message to all players.
     *
     * @param player The player who completed the task.
     */
    public static void sendTaskCompletionMessage(Player player) {
        sendMessage(player, "You have completed your task!");
        broadcastMessage(player.getName() + " has completed their task!");
    }
}