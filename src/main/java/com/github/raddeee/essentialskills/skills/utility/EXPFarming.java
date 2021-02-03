package com.github.raddeee.essentialskills.skills.utility;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Random;

public class EXPFarming {

    public static void expFarm(Player player, BlockBreakEvent event){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("farmer");
        int score = objective.getScore(player.getName()).getScore();
        Block block = event.getBlock();

        if(score != 0 && block.getBlockData() instanceof Ageable){
            Ageable crop = (Ageable)block.getBlockData();
            if(crop.getAge() == crop.getMaximumAge()){
                Random random = new Random();
                if((score * 5 + 25) >= random.nextInt(100)){
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    player.giveExp(1);
                }
            }
        }

    }

}
