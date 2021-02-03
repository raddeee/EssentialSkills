package com.github.raddeee.essentialskills;

import com.github.raddeee.essentialskills.Enchants.RegisterGlitter;
import com.github.raddeee.essentialskills.GUIs.MainGUI;
import com.github.raddeee.essentialskills.events.ClickEvent;
import com.github.raddeee.essentialskills.skills.SkillListerner;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialSkills extends JavaPlugin {

    boolean PlayersOnline = false;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new onPlayersJoin(), this);
        getServer().getPluginManager().registerEvents(new SkillListerner(), this);
        RegisterGlitter.register();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                if(getServer().getOnlinePlayers().size() > 0 ){

                }
            }

        }, 20L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //resetScoreboards();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if((cmd.getName().equalsIgnoreCase("skills")) && (sender instanceof Player)){
            player.openInventory(MainGUI.gui(player));
            player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1 ,1);
            return true;
        }else {
            if(args[0].equalsIgnoreCase("reset")){
                resetScoreboards();
                return true;
            }else return false;
        }


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

    }



    public void resetScoreboards(){
        this.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players reset @a");
        this.getServer().broadcastMessage("All skills has been reset.");
    }

    public EssentialSkills getPlugin(){
        return this;
    }


}
