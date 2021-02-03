package com.github.raddeee.essentialskills.GUIs;

import com.github.raddeee.essentialskills.Enchants.RegisterGlitter;
import com.github.raddeee.essentialskills.methods.checkCanBuy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Arrays;

public class SurvivalGUI {
    public static Inventory gui(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.GOLD+ "" + ChatColor.BOLD + "Survival Skills");

        ItemStack heartsE = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack hearts1 = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack nutritionE = new ItemStack(Material.CAKE);
        ItemStack nutrition1 = new ItemStack(Material.CAKE);
        ItemStack laststandE = new ItemStack(Material.TOTEM_OF_UNDYING);

        ItemStack[] hearts = {hearts1, hearts1, hearts1, hearts1, hearts1};
        ItemStack[] nutrition = {nutrition1, nutrition1, nutrition1, nutrition1, nutrition1};
        ItemStack[] laststand = {laststandE, laststandE, laststandE, laststandE, laststandE};

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        ItemMeta heartsE_meta = heartsE.getItemMeta();
        heartsE_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "More Hearts");
        ArrayList<String> heartsE_lore = new ArrayList<>();
        heartsE_lore.addAll(Arrays.asList(ChatColor.GOLD + "More Hearts" + ChatColor.GRAY + " increases you maximum HP.", "", ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        heartsE_meta.setLore(heartsE_lore);
        heartsE.setItemMeta(heartsE_meta);
        gui.setItem(10, heartsE);

        ItemMeta nutritionE_meta = heartsE.getItemMeta();
        nutritionE_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Nutritious");
        ArrayList<String> nutritionE_lore = new ArrayList<>();
        nutritionE_lore.addAll(Arrays.asList(ChatColor.GOLD + "Nutritious" + ChatColor.GRAY + " gives you a random potion effect", ChatColor.GRAY + "when eating food.", "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        nutritionE_meta.setLore(nutritionE_lore);
        nutritionE.setItemMeta(nutritionE_meta);
        gui.setItem(19, nutritionE);

        ItemMeta laststandE_meta = heartsE.getItemMeta();
        laststandE_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Last Stand");
        ArrayList<String> laststandE_lore = new ArrayList<>();
        laststandE_lore.addAll(Arrays.asList(ChatColor.GOLD + "Last Stand" + ChatColor.GRAY + " makes you endure some critical damages.", "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        laststandE_meta.setLore(laststandE_lore);
        laststandE.setItemMeta(laststandE_meta);
        gui.setItem(28, laststandE);

        for(int i =0;i != 5; i++){

            ItemMeta hearts_meta = hearts[i].getItemMeta();
            hearts_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "More Hearts" + ChatColor.GRAY + " : Level " + (i + 1));
            ArrayList<String> hearts_lore = new ArrayList<>();
            hearts_lore.addAll(Arrays.asList(ChatColor.GRAY + "Increases you maximum HP by " + ChatColor.AQUA + ((i + 1) * 2) + ChatColor.RED + "♡", "" ,ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 2) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("morehearts");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                hearts_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                hearts_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else hearts_meta.removeEnchant(RegisterGlitter.GLITTER);
            hearts_meta.setLore(hearts_lore);
            hearts[i].setItemMeta(hearts_meta);
            gui.setItem(i + 12, hearts[i]);

            ItemMeta nutrition_meta = nutrition[i].getItemMeta();
            nutrition_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Nutritious" + ChatColor.GRAY + " : Level " + (i + 1));
            ArrayList<String> nutrition_lore = new ArrayList<>();
            nutrition_lore.addAll(Arrays.asList(ChatColor.GRAY + "A random potion effect of " + ChatColor.AQUA + ((i + 1) * 8 + 20) + " secs", ChatColor.GRAY + "will be applied upon eating.", ChatColor.GRAY + "Has a " + ChatColor.AQUA + ((i + 1) * 2 + 20) + "%" + ChatColor.GRAY + " chance per 1 hunger bar.", "" ,ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("nutrition");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                nutrition_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                nutrition_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else nutrition_meta.removeEnchant(RegisterGlitter.GLITTER);
            nutrition_meta.setLore(nutrition_lore);
            nutrition[i].setItemMeta(nutrition_meta);
            gui.setItem(i + 21, nutrition[i]);

            ItemMeta laststand_meta = laststand[i].getItemMeta();
            laststand_meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Last Stand" + ChatColor.GRAY + " : Level " + (i + 1));
            ArrayList<String> laststand_lore = new ArrayList<>();
            laststand_lore.addAll(Arrays.asList(ChatColor.GRAY + "Upon getting hit by a critical damage", ChatColor.GRAY + "that would lead to death, there is a ", ChatColor.AQUA + "" + ((i + 1) * 5 + 10) + "%" + ChatColor.GRAY + " chance that you will survive the", ChatColor.GRAY + "attack at half a " + ChatColor.RED + "♡" + ChatColor.GRAY + ".", "" ,ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("laststand");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                laststand_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                laststand_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else laststand_meta.removeEnchant(RegisterGlitter.GLITTER);
            laststand_meta.setLore(laststand_lore);
            laststand[i].setItemMeta(laststand_meta);
            gui.setItem(i + 30, laststand[i]);


        }



        return gui;
    }

    public static void onClick(InventoryClickEvent e, Player player){

        ItemStack itemStack = e.getCurrentItem();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        try{
            String SkillName = e.getCurrentItem().getItemMeta().getDisplayName();
            int SkillLevel = Integer.parseInt(SkillName.substring(SkillName.length() - 1));

            if(SkillName.contains("Level")){
                int Score = 0;
                if(SkillName.contains("More Hearts")){
                    Score = scoreboard.getObjective("morehearts").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Nutritious")){
                    Score = scoreboard.getObjective("nutrition").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Last Stand")){
                    Score = scoreboard.getObjective("laststand").getScore(player.getName()).getScore();
                }

                checkCanBuy.checkCanBuy(Score, SkillLevel, itemStack, player);
            }
        }catch (Exception yo){}



        e.setCancelled(true);
    }
}
