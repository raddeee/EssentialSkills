package com.github.raddeee.essentialskills.GUIs;

import com.github.raddeee.essentialskills.skills.survival.MoreHearts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;

public class ConfirmGUI {
    public static Inventory gui(Player player, ItemStack skill_item){
        Inventory gui = Bukkit.createInventory(player, 27, "Do you really want to get this skill?");

        ItemStack yes_item = new ItemStack(Material.LIME_WOOL);
        ItemStack no_item = new ItemStack(Material.RED_WOOL);

        ItemStack experience_item = new ItemStack(Material.EXPERIENCE_BOTTLE);


        ItemMeta yes_meta = yes_item.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "YES");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.GRAY + "Click to confirm");
        yes_meta.setLore(yes_lore);
        yes_item.setItemMeta(yes_meta);
        gui.setItem(11, yes_item);

        ItemMeta no_meta = no_item.getItemMeta();
        no_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "NO");
        ArrayList<String> no_lore = new ArrayList<>();
        no_lore.add(ChatColor.GRAY + "Click to cancel");
        no_meta.setLore(no_lore);
        no_item.setItemMeta(no_meta);
        gui.setItem(15, no_item);

        ItemMeta experience_meta = experience_item.getItemMeta();
        experience_meta.setDisplayName("Your current LVL : " + ChatColor.GREEN + player.getLevel());
        experience_item.setItemMeta(experience_meta);
        gui.setItem(22, experience_item);

        gui.setItem(4, skill_item);

        return gui;
    }

    public static void onClick(InventoryClickEvent e, Player player){

        String SkillName = e.getView().getItem(4).getItemMeta().getDisplayName();
        int SkillLevel = Integer.parseInt(SkillName.substring(SkillName.length() - 1));
        int ReqLevel = 0;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        if(SkillName.contains("Adrenaline")){
            ReqLevel = 5 * SkillLevel;
            objective = scoreboard.getObjective("adrenaline");
        }else if (SkillName.contains("Counter")){
            ReqLevel = 5 * SkillLevel;
            objective = scoreboard.getObjective("counter");
        }else if (SkillName.contains("Leech")){
            ReqLevel = 5 * SkillLevel;
            objective = scoreboard.getObjective("leech");
        }else if (SkillName.contains("More Hearts")){
            ReqLevel = 5 * (SkillLevel + 2);
            objective = scoreboard.getObjective("morehearts");
            MoreHearts.morehearts(player, SkillLevel);
        }else if (SkillName.contains("Nutritious")){
            ReqLevel = 5 * (SkillLevel + 1);
            objective = scoreboard.getObjective("nutrition");
        }else if (SkillName.contains("Last Stand")){
            ReqLevel = 5 * (SkillLevel + 1);
            objective = scoreboard.getObjective("laststand");
        }else if (SkillName.contains("Mine All")){
            ReqLevel = 10;
            objective = scoreboard.getObjective("mineall");
        }else if (SkillName.contains("Cut All")){
            ReqLevel = 10;
            objective = scoreboard.getObjective("cutall");
        }else if (SkillName.contains("Dig All")){
            ReqLevel = 10;
            objective = scoreboard.getObjective("digall");
        }else if (SkillName.contains("EXP Farming")){
            ReqLevel = 10;
            objective = scoreboard.getObjective("farmer");
        }else if (SkillName.contains("Grappling Hook")){
            ReqLevel = 10;
            objective = scoreboard.getObjective("grapple");
        }

        objective.getScore(player.getName()).setScore(SkillLevel);
        player.setLevel(player.getLevel() - ReqLevel);
        player.sendMessage(ChatColor.BOLD +  "You have obtained \" " + SkillName + ChatColor.RESET + "" + ChatColor.BOLD + " \" !");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 ,1);

    }

    public static void onClickRED(InventoryClickEvent e, Player player){
        String SkillName = e.getView().getItem(4).getItemMeta().getDisplayName();
        if(SkillName.contains("Adrenaline") || SkillName.contains("Counter") || SkillName.contains("Leech")){
            player.openInventory(CombatGUI.gui(player));
        }else if(SkillName.contains("Hearts") || SkillName.contains("Nutritious") || SkillName.contains("Last Stand")){
            player.openInventory(SurvivalGUI.gui(player));
        }else if(SkillName.contains("All")){
            player.openInventory(MiningGUI.gui(player));
        }else if(SkillName.contains("EXP") || SkillName.contains("Hook")){
            player.openInventory(UtilityGUI.gui(player));
        }
    }
}
