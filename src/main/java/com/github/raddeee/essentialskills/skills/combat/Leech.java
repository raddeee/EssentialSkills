package com.github.raddeee.essentialskills.skills.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Leech {

    public static void LeechCalc(Player player, double Damage){


        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("leech");
        int Score = objective.getScore(player.getName()).getScore();
        Objective objective_d = scoreboard.getObjective("leech_damage");
        int plus_damage = objective_d.getScore(player.getName()).getScore();


        double persent;
        if(Score == 5){
            persent = 25;
        }else persent = Score * 5 + 5;

        double healamount = (Damage + plus_damage) * (double)(persent / 100);

        if(healamount >= 1){
            if(player.getHealth() + healamount > 20){
                player.setHealth(20);
            }else player.setHealth(player.getHealth() + healamount);

            if(Score == 5){
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
            }

            objective_d.getScore(player.getName()).setScore(0);
        }else objective_d.getScore(player.getName()).setScore((int)(Damage + plus_damage));

    }

}
