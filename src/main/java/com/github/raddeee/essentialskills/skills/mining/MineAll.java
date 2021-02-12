package com.github.raddeee.essentialskills.skills.mining;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MineAll {

    public static void ActivateMineAll(Player player){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("mineall");
        int score = objective.getScore(player.getName()).getScore();

        if(score < 6){
            objective.getScore(player.getName()).setScore(score + 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Mine All" + ChatColor.WHITE + " is active"));
        }else {
            objective.getScore(player.getName()).setScore(score - 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Mine All" + ChatColor.WHITE + " is off"));
        }
    }

    public static void MineAllFunc(Player player, BlockBreakEvent event){
        boolean canMineAll = false;
        Block block = event.getBlock();
        Material[] pickaxes = {Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE, Material.GOLDEN_PICKAXE};
        Material inhandMaterial = player.getInventory().getItemInMainHand().getType();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("mineall");
        int score = objective.getScore(player.getName()).getScore();

        ItemStack player_pick = player.getInventory().getItemInMainHand();
        ItemMeta pick_meta = player_pick.getItemMeta();
        int damage;
        int maxDurability = player_pick.getType().getMaxDurability();

        if(score == 6 && inhandMaterial == pickaxes[0]){
            canMineAll = true;
        }else if(score == 7){
            if(inhandMaterial == pickaxes[0] || inhandMaterial == pickaxes[1]){
                canMineAll = true;
            }
        }else if(score == 8){
            if(inhandMaterial == pickaxes[0] || inhandMaterial == pickaxes[1] || inhandMaterial == pickaxes[2]){
                canMineAll = true;
            }
        }else if(score == 9){
            if(inhandMaterial == pickaxes[0] || inhandMaterial == pickaxes[1] || inhandMaterial == pickaxes[2] || inhandMaterial == pickaxes[3]){
                canMineAll = true;
            }
        }else if(score == 10){
            if(inhandMaterial == pickaxes[0] || inhandMaterial == pickaxes[1] || inhandMaterial == pickaxes[2] || inhandMaterial == pickaxes[3] || inhandMaterial == pickaxes[4]){
                canMineAll = true;
            }
        }


        if(canMineAll){

            if(block.getType() == Material.STONE || block.getType() == Material.ANDESITE || block.getType() == Material.DIORITE || block.getType() == Material.GRANITE){
                for(int i = 0; i != 26; i++){

                    int[] xyzs = getLoc(i);
                    damage = ((Damageable) pick_meta).getDamage();

                    Block stone = player.getWorld().getBlockAt(block.getLocation().clone().add(xyzs[0], xyzs[1], xyzs[2]));
                    if(stone.getType() == Material.STONE || stone.getType() == Material.ANDESITE || stone.getType() == Material.DIORITE || stone.getType() == Material.GRANITE){
                        if(damage + 2 >= maxDurability){
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "The item is too damaged to use the skill!"));
                            break;
                        }else{
                            Random random = new Random();
                            if(pick_meta.getEnchantLevel(Enchantment.DURABILITY) * 20 <= random.nextInt(100)){
                                ((Damageable) pick_meta).setDamage(damage + 1);
                                player_pick.setItemMeta(pick_meta);
                            }
                            stone.breakNaturally();
                        }
                    }
                }
            }else if(block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.GOLD_ORE
                    || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.NETHER_QUARTZ_ORE || block.getType() == Material.NETHER_GOLD_ORE || block.getType() == Material.NETHERRACK){
                List<Block> blocks =  new ArrayList<Block>();
                blocks.add(block);

                boolean canloop = true;

                while (blocks.size() != 0 && canloop){
                    for(int i = 0; i != blocks.size() && canloop; i++){
                        Location location = blocks.get(i).getLocation();
                        for(int i2 = 0; i2 != 6; i2++) {
                            damage = ((Damageable) pick_meta).getDamage();
                            BlockFace[] face = {BlockFace.DOWN,BlockFace.EAST,BlockFace.NORTH,BlockFace.SOUTH,BlockFace.UP,BlockFace.WEST};
                            if(blocks.get(i).getRelative(face[i2]).getType() == Material.COAL_ORE || blocks.get(i).getRelative(face[i2]).getType() == Material.IRON_ORE || blocks.get(i).getRelative(face[i2]).getType() == Material.GOLD_ORE ||
                                    blocks.get(i).getRelative(face[i2]).getType() == Material.DIAMOND_ORE || blocks.get(i).getRelative(face[i2]).getType() == Material.NETHER_QUARTZ_ORE || blocks.get(i).getRelative(face[i2]).getType() == Material.NETHER_GOLD_ORE || block.getType() == Material.NETHERRACK) {
                                blocks.add(blocks.get(i).getRelative(face[i2]));
                                blocks = blocks.stream().distinct().collect(Collectors.toList());
                            }
                            if(i2 == 5){
                                blocks.remove(i);
                                if(damage + 2 >= maxDurability){
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "The item is too damaged to use the skill!"));
                                    canloop = false;
                                    break;
                                }else{
                                    Random random = new Random();
                                    if(pick_meta.getEnchantLevel(Enchantment.DURABILITY) * 20 <= random.nextInt(100)){
                                        ((Damageable) pick_meta).setDamage(damage + 1);
                                        player_pick.setItemMeta(pick_meta);
                                    }
                                    player.getWorld().getBlockAt(location).breakNaturally(new ItemStack(Material.DIAMOND_PICKAXE));
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private static int[] getLoc(int i){

        int[] x = {-1,-1,-1,-1,-1,-1,-1,-1,-1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
        int[] y = {-1,-1,-1,1,1,1,0,0,0,-1,-1,-1,1,1,1,0,0,0,-1,-1,-1,1,1,1,0,0};
        int[] z = {-1,1,0,-1,1,0,-1,1,0,-1,1,0,-1,1,0,-1,1,0,-1,1,0,-1,1,0,-1,1};

        int[] xyz = {x[i], y[i], z[i]};

        return xyz;

    }
}
