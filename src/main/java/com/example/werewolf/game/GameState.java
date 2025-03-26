package com.example.werewolf.game;

// Enum to represent the various states of the game
public enum GameState {
    LOBBY,          // The game is in the lobby, waiting for players to join
    IN_PROGRESS,    // The game is currently being played
    ENDED;          // The game has ended

    // Method to get a friendly name for the game state
    public String getFriendlyName() {
        switch (this) {
            case LOBBY:
                return "Lobby";
            case IN_PROGRESS:
                return "In Progress";
            case ENDED:
                return "Ended";
            default:
                return "Unknown";
        }
    }
}