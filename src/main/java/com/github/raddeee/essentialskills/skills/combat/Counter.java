package com.github.raddeee.essentialskills.skills.combat;

import com.github.raddeee.essentialskills.EssentialSkills;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;
import sun.awt.SunHints;

public class Counter {

    private static Player ShieldedPlayer;
    private static boolean isShielded = false;

    public static void checkCounter(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Objective objective = scoreboard.getObjective("counter");
        int Score = objective.getScore(player.getName()).getScore();

        if(Score != 0 && !isShielded){
            ShieldedPlayer = player;
            isShielded = true;
            new BukkitRunnable() {
                @Override
                public void run () {
                    isShielded = false;
                }
            }.runTaskLater(EssentialSkills.getPlugin(EssentialSkills.class), (2 + Score * 1));
        }
    }

    public static void checkBlocked(Player player, Entity damager){
        Player BlockedPlayer = player;
        runCounter(BlockedPlayer, damager);

    }

    private static void runCounter(Player BlockedPlayer, Entity damager){
        if(isShielded && ShieldedPlayer == BlockedPlayer){
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = manager.getMainScoreboard();
            Objective objective = scoreboard.getObjective("counter");
            int Score = objective.getScore(ShieldedPlayer.getName()).getScore();


            ShieldedPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "" + ChatColor.BOLD + "Counter" + ChatColor.WHITE + " has been activated"));


            ShieldedPlayer.playSound(ShieldedPlayer.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1 ,1);


            if(damager.getType() == EntityType.ARROW){
                Arrow arrow = (Arrow)damager;
                Entity shooter = (Entity)arrow.getShooter();
                if(shooter.getType().isAlive()){
                    ShieldedPlayer.spawnParticle(Particle.SWEEP_ATTACK, ShieldedPlayer.getLocation().clone().add(0,1,0), 10, 1 ,0 ,1);
                    new BukkitRunnable() {
                        @Override
                        public void run () {
                            Location from = arrow.getLocation(); //Gets the arrows location
                            Location to = shooter.getLocation().clone().add(0,1.7,0); //Gets the entities Location
                            Vector vFrom = from.toVector(); //Converts the from location to a vector
                            Vector vTo = to.toVector(); //Converts the to location to a vector
                            Vector direction = vTo.subtract(vFrom).normalize(); //Subtracts the to variable to the from variable and normalizes it.
                            arrow.setVelocity(direction.multiply(1.5)); //Sets the arrows newfound direction
                        }
                    }.runTaskLater(EssentialSkills.getPlugin(EssentialSkills.class), 1);
                }
            }else {
                new BukkitRunnable() {
                    @Override
                    public void run () {
                        ShieldedPlayer.spawnParticle(Particle.SWEEP_ATTACK, ShieldedPlayer.getLocation().clone().add(0,1,0), 10, 1 ,0 ,1);
                        ShieldedPlayer.playSound(ShieldedPlayer.getLocation().clone().add(0,1,0), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1 ,1);
                        ((LivingEntity) damager).damage(1 + Score * 1);
                    }
                }.runTaskLater(EssentialSkills.getPlugin(EssentialSkills.class), 10);
            }
        }
    }

}
