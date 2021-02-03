package com.github.raddeee.essentialskills;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class onPlayersJoin implements Listener {

    @EventHandler
    public void onPlayersJoin(PlayerJoinEvent e){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard;
        scoreboard  = manager.getMainScoreboard();

        try{
            scoreboard.registerNewObjective("adrenaline", "dummy", "Adrenaline Level");
            scoreboard.registerNewObjective("counter", "dummy", "Counter Level");
            scoreboard.registerNewObjective("leech", "dummy", "Leech Level");
            scoreboard.registerNewObjective("leech_check", "dummy", "Leech Extra Damage");
            scoreboard.registerNewObjective("morehearts", "dummy", "More Hearts");
            scoreboard.registerNewObjective("nutrition", "dummy", "Nutritious");
            scoreboard.registerNewObjective("mineall", "dummy", "Mine All");
            scoreboard.registerNewObjective("digall", "dummy", "Dig All");
            scoreboard.registerNewObjective("cutall", "dummy", "Cut All");
            scoreboard.registerNewObjective("laststand", "dummy", "Last Stand");
            scoreboard.registerNewObjective("farmer", "dummy", "EXP Farming");
            scoreboard.registerNewObjective("grapple", "dummy", "Grappling Hook");

            System.out.println("<raddeee's Essential Skills> Scoreboards have been created. Initialization complete.");
        }catch (IllegalArgumentException exception){
            System.out.println("<raddeee's Essential Skills> Scoreboards already exists. Initialization complete.");
        }
    }

}
