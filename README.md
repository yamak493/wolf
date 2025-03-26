# Minecraft Werewolf Plugin

## Overview
This project is a Minecraft plugin for the Paper API, implementing a Werewolf game mode. Players can take on the roles of villagers or werewolves, with unique abilities and tasks. The goal for villagers is to complete tasks and eliminate werewolves, while werewolves aim to eliminate all villagers.

## Features
- **Role Assignment**: Randomly assigns roles to players at the start of the game.
- **Villager Tasks**: Villagers must collect coal to win the game.
- **Werewolf Skills**: Werewolves have unique skills such as Shadow Walk, Wolf Summon, Fang, and Night Vision.
- **Game Management**: Handles game states, player management, and task tracking.
- **Commands**: Includes commands for players to join/leave the game and for admins to manage game settings.

## Installation
1. Ensure you have a Minecraft server running Paper API version 1.21.4.
2. Download the plugin JAR file from the releases section.
3. Place the JAR file in the `plugins` folder of your Minecraft server.
4. Restart the server to load the plugin.

## Configuration
- The default configuration can be found in `src/main/resources/config.yml`. Modify this file to adjust game settings such as the number of werewolves and task requirements.

## Commands
- `/werewolf join`: Join the Werewolf game.
- `/werewolf leave`: Leave the Werewolf game.
- `/werewolf start`: Start the game (admin only).
- `/werewolf stop`: Stop the game (admin only).
- `/werewolf setwerewolves <number>`: Set the number of werewolves (admin only).
- `/werewolf settasks <number>`: Set the number of coal required for villagers to win (admin only).

## Gameplay
- Players are assigned roles at the start of the game.
- Villagers must complete their tasks while avoiding werewolves.
- Werewolves can use their skills to hunt villagers and disrupt their tasks.
- The game ends when either all villagers are eliminated or the villagers complete their tasks.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.