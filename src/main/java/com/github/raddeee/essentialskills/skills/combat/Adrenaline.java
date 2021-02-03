package com.github.raddeee.essentialskills.skills.combat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import javax.swing.text.JTextComponent;
import java.util.UUID;

public class Adrenaline {

    public static void activateAdrenaline(Player player, double PlayerHP, double Heal){

        double PlayerHP_A = PlayerHP + Heal;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("adrenaline");

        int Score = objective.getScore(player.getName()).getScore();

        int[] adrenaline_Heart = {4, 4, 5, 6, 6};

        for(int i = 0; i != 5 ; i++ ){

            if(PlayerHP_A <= adrenaline_Heart[Score - 1] && Score == i + 1){
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "" + ChatColor.BOLD + "Adrenaline" + ChatColor.WHITE + " has been activated"));
                try{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25000, 0));
                    if(Score == 5){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 25000, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 25000, 1));
                    }
                    if(Score == 4){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 25000, 1));
                    }
                    if(Score > 1){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 25000, 0));
                    }
                }catch (Exception e){}
            }



            if(PlayerHP_A > adrenaline_Heart[Score - 1] && Score == i + 1){
                try{
                    if(player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 0) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "" + ChatColor.BOLD + "Adrenaline" + ChatColor.WHITE + " has been deactivated"));
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                    if(Score == 5 && player.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE).getAmplifier() == 0){
                        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    }
                    if(Score > 2 && player.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() == 1){
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    }else if(Score > 0 && player.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() == 0){
                        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    }
                }catch (Exception e){};
            }
        }
    }
    

}
