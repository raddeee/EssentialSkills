package com.github.raddeee.essentialskills.skills.utility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;

public class GrapplingHook {

    private ArrayList<Player> cooldown = new ArrayList<Player>();
    private ArrayList<Player> nofall = new ArrayList<Player>();

    public static void grappleFunc(ProjectileLaunchEvent event){

        FishHook hook = (FishHook) event.getEntity();

        Player player = (Player) event.getEntity().getShooter();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("grapple");
        int score = objective.getScore(player.getName()).getScore();

        if(score == 1){
        }


    }

}
