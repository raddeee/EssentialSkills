package com.github.raddeee.essentialskills.skills;

import com.github.raddeee.essentialskills.EssentialSkills;
import com.github.raddeee.essentialskills.skills.combat.Adrenaline;
import com.github.raddeee.essentialskills.skills.combat.Counter;
import com.github.raddeee.essentialskills.skills.combat.Leech;
import com.github.raddeee.essentialskills.skills.mining.CutAll;
import com.github.raddeee.essentialskills.skills.mining.DigAll;
import com.github.raddeee.essentialskills.skills.mining.MineAll;
import com.github.raddeee.essentialskills.skills.survival.LastStand;
import com.github.raddeee.essentialskills.skills.survival.Nutritious;
import com.github.raddeee.essentialskills.skills.utility.EXPFarming;
import com.github.raddeee.essentialskills.skills.utility.GrapplingHook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SkillListerner implements Listener {



    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            double Heal = event.getAmount();
            Player player = (Player) event.getEntity();
            Adrenaline.activateAdrenaline(player, player.getHealth(), Heal);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            double PlayerHP = player.getHealth() - event.getFinalDamage();

            Adrenaline.activateAdrenaline(player, PlayerHP, 0);

            if(PlayerHP <= 0){
                LastStand.activateLastStand(player, event);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();

        Objective objective = scoreboard.getObjective("mineall");
        if(objective.getScore(player.getName()).getScore() != 0 && event.getAction() == Action.RIGHT_CLICK_AIR){
            if(event.getMaterial() == Material.WOODEN_PICKAXE || event.getMaterial() == Material.IRON_PICKAXE || event.getMaterial() == Material.GOLDEN_PICKAXE || event.getMaterial() == Material.STONE_PICKAXE || event.getMaterial() == Material.DIAMOND_PICKAXE){
                MineAll.ActivateMineAll(player);
            }else if(event.getMaterial() == Material.WOODEN_AXE || event.getMaterial() == Material.IRON_AXE || event.getMaterial() == Material.GOLDEN_AXE || event.getMaterial() == Material.STONE_AXE || event.getMaterial() == Material.DIAMOND_AXE){
                CutAll.ActivateCutAll(player);
            }else if(event.getMaterial() == Material.WOODEN_SHOVEL || event.getMaterial() == Material.IRON_SHOVEL || event.getMaterial() == Material.GOLDEN_SHOVEL || event.getMaterial() == Material.STONE_SHOVEL || event.getMaterial() == Material.DIAMOND_SHOVEL){
                DigAll.ActivateDigAll(player);
            }
        }


        objective = scoreboard.getObjective("counter");
        if(objective.getScore(player.getName()).getScore() != 0){
            player.setShieldBlockingDelay(0);
            new BukkitRunnable() {
                @Override
                public void run () {
                    if(player.isBlocking()){
                        Counter.checkCounter(player);
                    }
                }
            }.runTaskLater(EssentialSkills.getPlugin(EssentialSkills.class), 1);
        }
    }

    @EventHandler
    public void getDamaged(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if(entity instanceof Player) {
            Player player = (Player) event.getEntity();
            if(player.isBlocking()){
                Counter.checkBlocked(player, damager);
            }
        }

        if(damager instanceof Player){
            double Damage = event.getFinalDamage();
            Player player = (Player)damager;
            Leech.LeechCalc(player, Damage);
        }
    }

    @EventHandler
    public void onEat(FoodLevelChangeEvent event){
        Player player = (Player)event.getEntity();
        Nutritious.nutritious(player, event);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = (Player)event.getPlayer();
        MineAll.MineAllFunc(player, event);
        CutAll.CutAllFunc(player, event);
        DigAll.DigAllFunc(player,event);
        EXPFarming.expFarm(player,event);
    }

    @EventHandler
    public void onShootProjectile(ProjectileLaunchEvent event){
        if(event.getEntity().getType() == EntityType.FISHING_HOOK){
            GrapplingHook.grappleFunc(event);
        }
    }

}


