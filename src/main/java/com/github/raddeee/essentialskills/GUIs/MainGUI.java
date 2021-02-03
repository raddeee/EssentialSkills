package com.github.raddeee.essentialskills.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MainGUI {
    public static Inventory gui(Player player){
        Inventory gui = Bukkit.createInventory(player, 9, ChatColor.DARK_AQUA+ "" + ChatColor.BOLD + "raddeee's Essential Skills");
        ItemStack combat = new ItemStack(Material.RED_WOOL);
        ItemStack survival = new ItemStack(Material.YELLOW_WOOL);
        ItemStack mining = new ItemStack(Material.GREEN_WOOL);
        ItemStack utility = new ItemStack(Material.MAGENTA_WOOL);

        ItemMeta combat_meta = combat.getItemMeta();
        combat_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Combat Skills");
        ArrayList<String> combat_lore = new ArrayList<>();
        combat_lore.add(ChatColor.GRAY + "Skills related to Combat");
        combat_meta.setLore(combat_lore);
        combat.setItemMeta(combat_meta);

        ItemMeta survival_meta = survival.getItemMeta();
        survival_meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Survivability Skills");
        ArrayList<String> survival_lore = new ArrayList<>();
        survival_lore.add(ChatColor.GRAY + "Skills related to Survivability");
        survival_meta.setLore(survival_lore);
        survival.setItemMeta(survival_meta);

        ItemMeta mining_meta = mining.getItemMeta();
        mining_meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Mining Skills");
        ArrayList<String> mining_lore = new ArrayList<>();
        mining_lore.add(ChatColor.GRAY + "Skills related to Mining");
        mining_meta.setLore(mining_lore);
        mining.setItemMeta(mining_meta);

        ItemMeta utility_meta = utility.getItemMeta();
        utility_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Utility Skills");
        ArrayList<String> utility_lore = new ArrayList<>();
        utility_lore.add(ChatColor.GRAY + "Skills related to other stuff");
        utility_meta.setLore(utility_lore);
        utility.setItemMeta(utility_meta);

        gui.setItem(1, combat);
        gui.setItem(3, survival);
        gui.setItem(5, mining);
        gui.setItem(7, utility);

        return gui;
    }

    public static void onClick(InventoryClickEvent e, Player player){
        switch(e.getCurrentItem().getType()){
            case YELLOW_WOOL:
                player.openInventory(SurvivalGUI.gui(player));
                e.setCancelled(true);
                break;
            case RED_WOOL:
                player.openInventory(CombatGUI.gui(player));
                e.setCancelled(true);
                break;
            case GREEN_WOOL:
                player.openInventory(MiningGUI.gui(player));
                e.setCancelled(true);
                break;
            case MAGENTA_WOOL:
                player.openInventory(UtilityGUI.gui(player));
                e.setCancelled(true);
                break;
        }
    }




}
