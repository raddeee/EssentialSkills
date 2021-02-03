package com.github.raddeee.essentialskills.skills.survival;

import com.github.raddeee.essentialskills.EssentialSkills;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Random;

public class Nutritious {

    public static void nutritious(Player player, FoodLevelChangeEvent event){

        int initialhunger = event.getFoodLevel();

        int  hunger = player.getFoodLevel();
        int foodlevel = initialhunger - hunger;

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("nutrition");
        int score = objective.getScore(player.getName()).getScore();
        PotionEffectType[] potionEffectTypes = {PotionEffectType.ABSORPTION, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FAST_DIGGING, PotionEffectType.FIRE_RESISTANCE, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.JUMP, PotionEffectType.LUCK, PotionEffectType.REGENERATION, PotionEffectType.WATER_BREATHING, PotionEffectType.SPEED};
        Random random = new Random();

        if(foodlevel > 1 && ((double)foodlevel * (score * 2 + 20) / 200) > (double)random.nextInt(100) / 100){
            player.addPotionEffect(new PotionEffect(potionEffectTypes[random.nextInt(10)], (score * 10 + 10) * 20, 0));
        }



    }

}
