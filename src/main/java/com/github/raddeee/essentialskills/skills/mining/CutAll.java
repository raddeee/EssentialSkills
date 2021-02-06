package com.github.raddeee.essentialskills.skills.mining;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CutAll {

    public static void ActivateCutAll(Player player){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("cutall");
        int score = objective.getScore(player.getName()).getScore();

        if(score < 6){
            objective.getScore(player.getName()).setScore(score + 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Cut All" + ChatColor.WHITE + " is active"));
        }else {
            objective.getScore(player.getName()).setScore(score - 5);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + ChatColor.BOLD + "Cut All" + ChatColor.WHITE + " is off"));
        }
    }

    public static void CutAllFunc(Player player, BlockBreakEvent event) {

        ItemStack player_axe = player.getInventory().getItemInMainHand();
        ItemMeta axe_meta = player_axe.getItemMeta();
        int damage;
        int maxDurability = player_axe.getType().getMaxDurability();

        boolean canCutAll = false;
        Block block = event.getBlock();
        Material[] axes = {Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.GOLDEN_AXE};
        Material inhandMaterial = player.getInventory().getItemInMainHand().getType();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("cutall");
        int score = objective.getScore(player.getName()).getScore();

        if (score == 6 && inhandMaterial == axes[0]) {
            canCutAll = true;
        } else if (score == 7) {
            if (inhandMaterial == axes[0] || inhandMaterial == axes[1]) {
                canCutAll = true;
            }
        } else if (score == 8) {
            if (inhandMaterial == axes[0] || inhandMaterial == axes[1] || inhandMaterial == axes[2]) {
                canCutAll = true;
            }
        } else if (score == 9) {
            if (inhandMaterial == axes[0] || inhandMaterial == axes[1] || inhandMaterial == axes[2] || inhandMaterial == axes[3]) {
                canCutAll = true;
            }
        } else if (score == 10) {
            if (inhandMaterial == axes[0] || inhandMaterial == axes[1] || inhandMaterial == axes[2] || inhandMaterial == axes[3] || inhandMaterial == axes[4]) {
                canCutAll = true;
            }
        }


        if (canCutAll) {
            if (block.getType() == Material.OAK_LOG || block.getType() == Material.BIRCH_LOG || block.getType() == Material.ACACIA_LOG
                    || block.getType() == Material.DARK_OAK_LOG || block.getType() == Material.JUNGLE_LOG) {
                List<Block> blocks = new ArrayList<Block>();
                blocks.add(block);

                boolean canloop = true;

                while (blocks.size() != 0 && canloop) {
                    for (int i = 0; i != blocks.size()&& canloop; i++) {
                        Location location = blocks.get(i).getLocation();
                        for (int i2 = 0; i2 != 6; i2++) {

                            damage = ((Damageable) axe_meta).getDamage();

                            BlockFace[] face = {BlockFace.DOWN, BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.UP, BlockFace.WEST};
                            if (blocks.get(i).getRelative(face[i2]).getType() == Material.OAK_LOG || blocks.get(i).getRelative(face[i2]).getType() == Material.BIRCH_LOG || blocks.get(i).getRelative(face[i2]).getType() == Material.ACACIA_LOG ||
                                    blocks.get(i).getRelative(face[i2]).getType() == Material.DARK_OAK_LOG || blocks.get(i).getRelative(face[i2]).getType() == Material.JUNGLE_LOG || blocks.get(i).getRelative(face[i2]).getType() == Material.OAK_LEAVES ||
                                    blocks.get(i).getRelative(face[i2]).getType() == Material.BIRCH_LEAVES || blocks.get(i).getRelative(face[i2]).getType() == Material.ACACIA_LEAVES ||
                                    blocks.get(i).getRelative(face[i2]).getType() == Material.DARK_OAK_LEAVES || blocks.get(i).getRelative(face[i2]).getType() == Material.JUNGLE_LEAVES) {
                                blocks.add(blocks.get(i).getRelative(face[i2]));
                                blocks = blocks.stream().distinct().collect(Collectors.toList());
                            }
                            if (i2 == 5) {
                                blocks.remove(i);
                                if(damage + 2 >= maxDurability){
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "The item is too damaged to use the skill!"));
                                    canloop = false;
                                    break;
                                }else{
                                    Random random = new Random();
                                    if(axe_meta.getEnchantLevel(Enchantment.DURABILITY) * 20 <= random.nextInt(100)){
                                        ((Damageable) axe_meta).setDamage(damage + 1);
                                        player_axe.setItemMeta(axe_meta);
                                    }
                                    player.getWorld().getBlockAt(location).breakNaturally(new ItemStack(Material.DIAMOND_AXE));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
