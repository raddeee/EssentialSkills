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
import java.util.List;

public class CombatGUI {
    public static Inventory gui(Player player){
        String[] roman = {"Ⅰ", "Ⅱ", "Ⅲ", "Ⅳ", "Ⅴ"};
        String[] adrenaline_Heart = {"2", "2", "2.5", "3", "3"};
        String[] adrenaline_Strength = {null,"Ⅰ","Ⅰ","Ⅱ","Ⅱ"};
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.RED+ "" + ChatColor.BOLD + "Combat Skills");


        ItemStack adrenalineE = new ItemStack(Material.BLAZE_POWDER);
        ItemStack adrenaline1 = new ItemStack(Material.BLAZE_POWDER);
        ItemStack counterE = new ItemStack(Material.SHIELD);
        ItemStack counter1 = new ItemStack(Material.SHIELD);
        ItemStack leechE = new ItemStack(Material.BEETROOT_SOUP);
        ItemStack leech1 = new ItemStack(Material.BEETROOT_SOUP);

        ItemStack[] adrenaline = {adrenaline1,adrenaline1,adrenaline1, adrenaline1, adrenaline1};
        ItemStack[] counter = {counter1,counter1,counter1,counter1,counter1};
        ItemStack[] leech = {leech1,leech1,leech1,leech1,leech1};



        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        ItemMeta adrenalineE_meta = adrenalineE.getItemMeta();
        adrenalineE_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Adrenaline");
        ArrayList<String> adrenalineE_lore = new ArrayList<>();
        adrenalineE_lore.addAll(Arrays.asList(ChatColor.RED + "Adrenaline" + ChatColor.GRAY + " makes you stronger on low HP levels.", "", ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        adrenalineE_meta.setLore(adrenalineE_lore);
        adrenalineE.setItemMeta(adrenalineE_meta);
        gui.setItem(10, adrenalineE);

        ItemMeta counterE_meta = counterE.getItemMeta();
        counterE_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Counter");
        ArrayList<String> counterE_lore = new ArrayList<>();
        counterE_lore.addAll(Arrays.asList(ChatColor.RED + "Counter" + ChatColor.GRAY + " enables to perform a just guard", ChatColor.GRAY + "counter attack using a shield.", ChatColor.GRAY + "A just guard can also reflect", ChatColor.GRAY + "arrows to it's sender.", "" ,ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        counterE_meta.setLore(counterE_lore);
        counterE.setItemMeta(counterE_meta);
        gui.setItem(19, counterE);

        ItemMeta leechE_meta = leechE.getItemMeta();
        leechE_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Leech");
        ArrayList<String> leechE_lore = new ArrayList<>();
        leechE_lore.addAll(Arrays.asList(ChatColor.RED + "Leech" + ChatColor.GRAY + " makes you heal a portion of the damage you dealt.", ChatColor.GRAY + "Higher levels gives an short effect after a kill.", "", ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        leechE_meta.setLore(leechE_lore);
        leechE.setItemMeta(leechE_meta);
        gui.setItem(28, leechE);

        for(int i =0;i != 5; i++){
            ItemMeta adrenaline_meta = adrenaline[i].getItemMeta();
            adrenaline_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Adrenaline" + ChatColor.GRAY + ": Level " + (i + 1));
            ArrayList<String> adrenaline_lore = new ArrayList<>();
            if(i < 1
            ){
                adrenaline_lore.addAll(Arrays.asList(ChatColor.GRAY + "When your HP is bellow " + ChatColor.AQUA + "" + adrenaline_Heart[i] + ChatColor.RED + "♡" + ChatColor.GRAY + ",", ChatColor.GRAY + "you will get the following effects.", ChatColor.AQUA + "Speed Ⅰ", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            }else if(i < 4){
                adrenaline_lore.addAll(Arrays.asList(ChatColor.GRAY + "When your HP is bellow " + ChatColor.AQUA + "" + adrenaline_Heart[i] + ChatColor.RED + "♡" + ChatColor.GRAY + ",", ChatColor.GRAY + "you will get the following effects.", ChatColor.AQUA + "Speed Ⅰ" + ChatColor.GRAY + ", " + ChatColor.AQUA + "Strength " + adrenaline_Strength[i], "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            }else adrenaline_lore.addAll(Arrays.asList(ChatColor.GRAY + "When your HP is bellow " + ChatColor.AQUA + "" + adrenaline_Heart[i] + ChatColor.RED + "♡" + ChatColor.GRAY + ",", ChatColor.GRAY + "you will get the following effects.", ChatColor.AQUA + "Speed Ⅰ" + ChatColor.GRAY + ", " + ChatColor.AQUA + "Strength " + adrenaline_Strength[i] + ChatColor.GRAY + ", " + ChatColor.BOLD + "" + ChatColor.AQUA + "Resistance Ⅰ", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("adrenaline");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                adrenaline_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                adrenaline_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else adrenaline_meta.removeEnchant(RegisterGlitter.GLITTER);
            adrenaline_meta.setLore(adrenaline_lore);
            adrenaline[i].setItemMeta(adrenaline_meta);
            gui.setItem(i + 12, adrenaline[i]);

            ItemMeta counter_meta = counter[i].getItemMeta();
            counter_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Counter" + ChatColor.GRAY + " : Level " + (i + 1));
            ArrayList<String> counter_lore = new ArrayList<>();
            counter_lore.addAll(Arrays.asList(ChatColor.GRAY + "Performs a " + ChatColor.AQUA + (i * 0.5 + 1) + ChatColor.RED + "♡" + ChatColor.GRAY + " direct damage when", ChatColor.GRAY + "successfully just guarded an attack.", ChatColor.GRAY + "A just guard can be performed only", ChatColor.GRAY + "while " + ChatColor.AQUA + (2 + (i + 1) * 1) + " ticks " + ChatColor.GRAY + "from the initial tick", ChatColor.GRAY + "that a shield guard has performed.", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("counter");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                counter_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                counter_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else counter_meta.removeEnchant(RegisterGlitter.GLITTER);
            counter_meta.setLore(counter_lore);
            counter[i].setItemMeta(counter_meta);
            gui.setItem(i + 21, counter[i]);

            ItemMeta leech_meta = leech[i].getItemMeta();
            leech_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Leech" + ChatColor.GRAY + " : Level " + (i + 1));
            ArrayList<String> leech_lore = new ArrayList<>();
            if(i < 4){
                leech_lore.addAll(Arrays.asList(ChatColor.AQUA + "" + (10 + i * 5) + "%" + ChatColor.GRAY + " of the damage you dealt will be healed.", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            } else leech_lore.addAll(Arrays.asList(ChatColor.AQUA + "25" + "%" + ChatColor.GRAY + " of the damage you dealt will be healed.", ChatColor.GRAY + "In addition, " + ChatColor.AQUA + "5 seconds" + ChatColor.GRAY + " of " + ChatColor.AQUA + "Strength Ⅰ" + ChatColor.GRAY + " will be applied.", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + ((i + 1) * 5) + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("leech");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                leech_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                leech_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else leech_meta.removeEnchant(RegisterGlitter.GLITTER);
            leech_meta.setLore(leech_lore);
            leech[i].setItemMeta(leech_meta);
            gui.setItem(i + 30, leech[i]);

        }
        return gui;
    }

    public static void onClick(InventoryClickEvent e, Player player){

        ItemStack itemStack = e.getCurrentItem();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        try {
            String SkillName = e.getCurrentItem().getItemMeta().getDisplayName();
            int SkillLevel = Integer.parseInt(SkillName.substring(SkillName.length() - 1));
            if(SkillName.contains("Level")){
                int Score = 0;
                if(SkillName.contains("Adrenaline")){
                    Score = scoreboard.getObjective("adrenaline").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Counter")){
                    Score = scoreboard.getObjective("counter").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Leech")){
                    Score = scoreboard.getObjective("leech").getScore(player.getName()).getScore();
                }

                checkCanBuy.checkCanBuy(Score, SkillLevel, itemStack, player);
            }
        }catch (Exception ex){}





        e.setCancelled(true);
    }


}
