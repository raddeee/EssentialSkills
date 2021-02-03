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

public class UtilityGUI {

    public static Inventory gui(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.LIGHT_PURPLE+ "" + ChatColor.BOLD + "Utility Skills");

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        ItemStack farmerE = new ItemStack(Material.GOLDEN_HOE);
        ItemStack[] farmer = {farmerE, farmerE, farmerE, farmerE, farmerE};
        ItemStack grappleE = new ItemStack(Material.FISHING_ROD);
        ItemStack[] grapple = {grappleE, new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_NUGGET)};

        ItemMeta farmerE_meta = farmerE.getItemMeta();
        farmerE_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EXP Farming");
        ArrayList<String> farmerE_lore = new ArrayList<>();
        farmerE_lore.addAll(Arrays.asList(ChatColor.LIGHT_PURPLE + "EXP Farming" + ChatColor.GRAY + " gives you EXP when farming crops",  "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        farmerE_meta.setLore(farmerE_lore);
        farmerE.setItemMeta(farmerE_meta);
        gui.setItem(10, farmerE);

        ItemMeta grappleE_meta = grappleE.getItemMeta();
        grappleE_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Grappling Hook");
        ArrayList<String> grappleE_lore = new ArrayList<>();
        grappleE_lore.addAll(Arrays.asList(ChatColor.LIGHT_PURPLE + "Grappling Hook" + ChatColor.GRAY + " enables to use a fishing rod", ChatColor.GRAY + "as a grappling hook.", ChatColor.GRAY + "Left click the rod to change mode." , "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        grappleE_meta.setLore(grappleE_lore);
        grappleE.setItemMeta(grappleE_meta);
        gui.setItem(19, grappleE);

        for(int i = 0; i != 5; i++){
            ItemMeta farmer_meta = farmer[i].getItemMeta();
            farmer_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EXP Farming" + ChatColor.GRAY + ": Level " + (i + 1));
            ArrayList<String> farmer_lore = new ArrayList<>();
            farmer_lore.addAll(Arrays.asList(ChatColor.GRAY + "Everytime breaking a fully grown crop,", ChatColor.GRAY + "there is a " + ChatColor.AQUA + ((i + 1) * 5 + 25) + "%" + ChatColor.GRAY + " chance getting 1 EXP.", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + 10 + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("farmer");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                farmer_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                farmer_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else farmer_meta.removeEnchant(RegisterGlitter.GLITTER);
            farmer_meta.setLore(farmer_lore);
            farmer[i].setItemMeta(farmer_meta);
            gui.setItem(i + 12, farmer[i]);

            objective = scoreboard.getObjective("grapple");
            ItemMeta grapple_meta = grapple[i].getItemMeta();
            if(i == 0){
                grapple_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Grappling Hook" + ChatColor.GRAY + ": Level " + (i + 1));
            }else grapple_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Grappling Hook");
            ArrayList<String> grapple_lore = new ArrayList<>();
            grapple_lore.addAll(Arrays.asList(ChatColor.GRAY + "Level 1 is only available.", "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + 15 + ChatColor.GRAY +  " Levels."));
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                grapple_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                grapple_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else grapple_meta.removeEnchant(RegisterGlitter.GLITTER);
            grapple_meta.setLore(grapple_lore);
            grapple[i].setItemMeta(grapple_meta);
            gui.setItem(i + 12, grapple[i]);
        }



        return gui;
    }

    public static void onClick(InventoryClickEvent event, Player player){

        ItemStack itemStack = event.getCurrentItem();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;
        try {
            String SkillName = event.getCurrentItem().getItemMeta().getDisplayName();
            int SkillLevel = Integer.parseInt(SkillName.substring(SkillName.length() - 1));
            if(SkillName.contains("Level")){
                int Score = 0;
                if(SkillName.contains("EXP Farming")){
                    Score = scoreboard.getObjective("farmer").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Grappling Hook")){
                    Score = scoreboard.getObjective("grapple").getScore(player.getName()).getScore();
                }

                checkCanBuy.checkCanBuy(Score, SkillLevel, itemStack, player);
            }
        }catch (Exception ex){}


        event.setCancelled(true);

    }

}
