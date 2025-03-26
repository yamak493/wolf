// This file provides methods for creating and managing custom items used in the game.

package com.example.werewolf.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import java.util.List;

public class ItemFactory {

    // Creates a custom item with a specified name and lore
    public static ItemStack createCustomItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName(ChatColor.RESET + name); // Set the display name of the item
            meta.setLore(lore); // Set the lore of the item
            item.setItemMeta(meta); // Apply the meta to the item
        }
        
        return item; // Return the custom item
    }

    // Creates a custom item for the werewolf's fang skill
    public static ItemStack createFangItem() {
        ItemStack fang = new ItemStack(Material.IRON_AXE); // Create an iron axe for the fang skill
        ItemMeta meta = fang.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName(ChatColor.RED + "Werewolf Fang"); // Set the display name
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, 2, true); // Add sharpness enchantment
            fang.setItemMeta(meta); // Apply the meta to the item
        }
        
        return fang; // Return the fang item
    }

    // Creates a custom item for the shadow walk skill
    public static ItemStack createShadowWalkItem() {
        return createCustomItem(Material.ENDER_PEARL, "Shadow Walk", List.of("Use to become invisible for 10 seconds.")); // Create shadow walk item
    }

    // Creates a custom item for the wolf summon skill
    public static ItemStack createWolfSummonItem() {
        return createCustomItem(Material.BONE, "Wolf Summon", List.of("Use to summon wolves to attack villagers.")); // Create wolf summon item
    }

    // Creates a custom item for the night vision skill
    public static ItemStack createNightVisionItem() {
        return createCustomItem(Material.GLOWSTONE_DUST, "Night Vision", List.of("Use to see the distance to other players.")); // Create night vision item
    }
}