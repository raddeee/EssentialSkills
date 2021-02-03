package com.github.raddeee.essentialskills.methods;

import com.github.raddeee.essentialskills.GUIs.ConfirmGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class checkCanBuy {
    public static void checkCanBuy(int Score, int SkillLevel, ItemStack itemStack, Player player){
        if (Score >= SkillLevel){
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You already have this level of the skill!");
        }else if (Score == SkillLevel - 1){
            player.openInventory(ConfirmGUI.gui(player, itemStack));
        }else player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need the previous level of the skill first!");
    }
}
