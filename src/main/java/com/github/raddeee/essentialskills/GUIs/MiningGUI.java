package com.github.raddeee.essentialskills.GUIs;

import com.github.raddeee.essentialskills.Enchants.RegisterGlitter;
import com.github.raddeee.essentialskills.methods.checkCanBuy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
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

public class MiningGUI {
    public static Inventory gui(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GREEN+ "" + ChatColor.BOLD + "Mining Skills");

        ItemStack mineallE = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemStack[] mineall = {new ItemStack(Material.WOODEN_PICKAXE), new ItemStack(Material.STONE_PICKAXE), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.NETHERITE_PICKAXE)};
        ItemStack cutallE = new ItemStack(Material.GOLDEN_AXE);
        ItemStack[] cutall = {new ItemStack(Material.WOODEN_AXE), new ItemStack(Material.STONE_AXE), new ItemStack(Material.IRON_AXE), new ItemStack(Material.DIAMOND_AXE), new ItemStack(Material.NETHERITE_AXE)};
        ItemStack digallE = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemStack[] digall = {new ItemStack(Material.WOODEN_SHOVEL), new ItemStack(Material.STONE_SHOVEL), new ItemStack(Material.IRON_SHOVEL), new ItemStack(Material.DIAMOND_SHOVEL), new ItemStack(Material.NETHERITE_SHOVEL)};

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = null;

        for(int i = 0; i != 3; i++){
            String[] str = {"mineall", "cutall", "digall"};
            objective = scoreboard.getObjective(str[i]);
            if(objective.getScore(player.getName()).getScore() > 5){
                objective.getScore(player.getName()).setScore(objective.getScore(player.getName()).getScore() - 5);
            }
        }

        ItemMeta mineallE_meta = mineallE.getItemMeta();
        mineallE_meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Mine All");
        ArrayList<String> mineallE_lore = new ArrayList<>();
        mineallE_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Mine All" + ChatColor.GRAY + " enables to mine the whole vein", ChatColor.GRAY + "in a chain reaction.", ChatColor.GRAY + "In addition, mining a stone will destroy", ChatColor.GRAY + "the surrounding stones in a 3×3×3 area.", ChatColor.GRAY + "Right click a pickaxe to activate skill." , "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        mineallE_meta.setLore(mineallE_lore);
        //mineallE_meta.setAttributeModifiers(null);
        mineallE.setItemMeta(mineallE_meta);
        gui.setItem(10, mineallE);

        ItemMeta cutallEmeta = cutallE.getItemMeta();
        cutallEmeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Mine All");
        ArrayList<String> cutallE_lore = new ArrayList<>();
        cutallE_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Cut All" + ChatColor.GRAY + " enables to cut the whole tree", ChatColor.GRAY + "in a chain reaction.", ChatColor.GRAY + "Right click a axe to activate skill." , "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        cutallEmeta.setLore(cutallE_lore);
        //mineallE_meta.setAttributeModifiers(null);
        cutallE.setItemMeta(cutallEmeta);
        gui.setItem(19, cutallE);

        ItemMeta digallE_meta = digallE.getItemMeta();
        digallE_meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Dig All");
        ArrayList<String> digallE_lore = new ArrayList<>();
        digallE_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Dig All" + ChatColor.GRAY + " enables to dig a 5×5×1 area in one go.", ChatColor.GRAY + "Right click a shovel to activate skill." , "" , ChatColor.DARK_GRAY + "Click the following level of the skill", ChatColor.DARK_GRAY + "to obtain/levelup", ""));
        digallE_meta.setLore(digallE_lore);
        //mineallE_meta.setAttributeModifiers(null);
        digallE.setItemMeta(digallE_meta);
        gui.setItem(28, digallE);

        for(int i = 0; i != 5; i++){
            ItemMeta mineall_meta = mineall[i].getItemMeta();
            mineall_meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Mine All" + ChatColor.GRAY + ": Level " + (i + 1));
            ArrayList<String> mineall_lore = new ArrayList<>();
            String[] pickaxe = {"Wooden Pickaxe", "Stone Pickaxe", "Iron Pickaxe", "Diamond Pickaxe", "Netherite Pickaxe"};
            mineall_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Mine All" + ChatColor.GRAY + " will be able", ChatColor.GRAY + "to activate with a " + ChatColor.AQUA + pickaxe[i], "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + 10 + ChatColor.GRAY +  " Levels."));
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                mineall_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                mineall_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else mineall_meta.removeEnchant(RegisterGlitter.GLITTER);
            mineall_meta.setLore(mineall_lore);
            mineall[i].setItemMeta(mineall_meta);
            gui.setItem(i + 12, mineall[i]);

            ItemMeta cutall_meta = cutall[i].getItemMeta();
            cutall_meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Cut All" + ChatColor.GRAY + ": Level " + (i + 1));
            ArrayList<String> cutall_lore = new ArrayList<>();
            String[] axe = {"Wooden Axe", "Stone Axe", "Iron Axe", "Diamond Axe", "Netherite Axe"};
            cutall_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Cut All" + ChatColor.GRAY + " will be able", ChatColor.GRAY + "to activate with a " + ChatColor.AQUA + axe[i], "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + 10 + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("cutall");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                cutall_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                cutall_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else cutall_meta.removeEnchant(RegisterGlitter.GLITTER);
            cutall_meta.setLore(cutall_lore);
            cutall[i].setItemMeta(cutall_meta);
            gui.setItem(i + 21, cutall[i]);

            ItemMeta digall_meta = digall[i].getItemMeta();
            digall_meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Dig All" + ChatColor.GRAY + ": Level " + (i + 1));
            ArrayList<String> digall_lore = new ArrayList<>();
            String[] shovel = {"Wooden Shovel", "Stone Shovel", "Iron Shovel", "Diamond Shovel", "Netherite Shovel"};
            digall_lore.addAll(Arrays.asList(ChatColor.DARK_GREEN + "Dig All" + ChatColor.GRAY + " will be able", ChatColor.GRAY + "to activate with a " + ChatColor.AQUA + shovel[i], "", ChatColor.GRAY + "Requires " + ChatColor.GREEN + 10 + ChatColor.GRAY +  " Levels."));
            objective = scoreboard.getObjective("digall");
            if(objective.getScore(player.getName()).getScore() >= (i + 1)) {
                digall_meta.addEnchant(RegisterGlitter.GLITTER, 0, true);
                digall_lore.addAll(Arrays.asList("", ChatColor.GREEN + "" + ChatColor.BOLD + "YOU ALREADY HAVE THIS LEVEL OF THE SKILL"));
            }else digall_meta.removeEnchant(RegisterGlitter.GLITTER);
            digall_meta.setLore(digall_lore);
            digall[i].setItemMeta(digall_meta);
            gui.setItem(i + 30, digall[i]);
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
                if(SkillName.contains("Mine All")){
                    Score = scoreboard.getObjective("mineall").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Cut All")){
                    Score = scoreboard.getObjective("cutall").getScore(player.getName()).getScore();
                }else if(SkillName.contains("Dig All")){
                    Score = scoreboard.getObjective("digall").getScore(player.getName()).getScore();
                }

                checkCanBuy.checkCanBuy(Score, SkillLevel, itemStack, player);
            }
        }catch (Exception ex){}


        event.setCancelled(true);
    }
}
