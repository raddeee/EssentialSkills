package com.github.raddeee.essentialskills.skills.mining;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class DigAll {

    public static void ActivateDigAll(Player player){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("digall");
        int score = objective.getScore(player.getName()).getScore();

        if(score < 6){
            objective.getScore(player.getName()).setScore(score + 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Dig All" + ChatColor.WHITE + " is active"));
        }else {
            objective.getScore(player.getName()).setScore(score - 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Dig All" + ChatColor.WHITE + " is off"));
        }
    }

    public static void DigAllFunc(Player player, BlockBreakEvent event){
        boolean canMineAll = false;
        Block block = event.getBlock();
        Material[] shovel = {Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL, Material.GOLDEN_SHOVEL};
        Material inhandMaterial = player.getInventory().getItemInMainHand().getType();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("digall");
        int score = objective.getScore(player.getName()).getScore();

        if(score == 6 && inhandMaterial == shovel[0]){
            canMineAll = true;
        }else if(score == 7){
            if(inhandMaterial == shovel[0] || inhandMaterial == shovel[1]){
                canMineAll = true;
            }
        }else if(score == 8){
            if(inhandMaterial == shovel[0] || inhandMaterial == shovel[1] || inhandMaterial == shovel[2]){
                canMineAll = true;
            }
        }else if(score == 9){
            if(inhandMaterial == shovel[0] || inhandMaterial == shovel[1] || inhandMaterial == shovel[2] || inhandMaterial == shovel[3]){
                canMineAll = true;
            }
        }else if(score == 10){
            if(inhandMaterial == shovel[0] || inhandMaterial == shovel[1] || inhandMaterial == shovel[2] || inhandMaterial == shovel[3] || inhandMaterial == shovel[4]){
                canMineAll = true;
            }
        }


        if(canMineAll){
            if(block.getType() == Material.DIRT || block.getType() == Material.GRASS_BLOCK || block.getType() == Material.COARSE_DIRT || block.getType() == Material.SAND
                    || block.getType() == Material.GRAVEL || block.getType() == Material.PODZOL || block.getType() == Material.MYCELIUM || block.getType() == Material.GRASS_PATH){
                for(int i = 0; i != 25; i++){

                    int x = i / 5;
                    int z = i % 5;

                    for (int i2 = 0; i2 != 5; i2++){
                        Block dirt = player.getWorld().getBlockAt(block.getLocation().clone().add(x - 2, i2, z - 2));
                        if(dirt.getType() == Material.DIRT || dirt.getType() == Material.GRASS_BLOCK || dirt.getType() == Material.COARSE_DIRT || dirt.getType() == Material.SAND
                                || dirt.getType() == Material.GRAVEL || dirt.getType() == Material.PODZOL || dirt.getType() == Material.MYCELIUM || dirt.getType() == Material.GRASS_PATH){
                            dirt.breakNaturally();
                        }
                    }
                }
            }
        }

    }

}
