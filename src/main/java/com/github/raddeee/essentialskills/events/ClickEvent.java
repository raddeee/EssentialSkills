package com.github.raddeee.essentialskills.events;

import com.github.raddeee.essentialskills.GUIs.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener{

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        //player.sendMessage(e.getView().getTitle());

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "raddeee's Essential Skills")) {
            MainGUI.onClick(e, player);
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Combat Skills")) {
            CombatGUI.onClick(e, player);
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase("Do you really want to get this skill?")) {
            switch (e.getCurrentItem().getType()) {
                case LIME_WOOL:
                    ConfirmGUI.onClick(e, player);
                case RED_WOOL:
                    ConfirmGUI.onClickRED(e, player);
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Survival Skills")) {
            SurvivalGUI.onClick(e, player);
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Mining Skills")) {
            MiningGUI.onClick(e, player);
            e.setCancelled(true);
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Utility Skills")) {
            UtilityGUI.onClick(e, player);
            e.setCancelled(true);
        }

    }
}
