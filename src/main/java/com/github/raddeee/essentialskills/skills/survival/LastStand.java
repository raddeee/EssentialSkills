package com.github.raddeee.essentialskills.skills.survival;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Random;

public class LastStand {

    public static void activateLastStand(Player player, EntityDamageEvent event){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("laststand");
        int Score = objective.getScore(player.getName()).getScore();

        Random random = new Random();

        if(((Score * 5) + 10) >= random.nextInt(100)){
            event.setCancelled(true);

            player.setHealth(1);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ChatColor.BOLD + "Last Stand" + ChatColor.WHITE + " has been activated"));
            player.getWorld().playSound(player.getLocation().clone().add(0,1,0), Sound.BLOCK_ANVIL_LAND, 1 ,2);
            player.getWorld().playSound(player.getLocation().clone().add(0,1,0), Sound.ENTITY_PLAYER_HURT, 1 ,1);
            player.spawnParticle(Particle.TOTEM, player.getLocation().clone().add(0,1,0), 15, 0.8 ,0.8,0.8);
        }

    }


}
