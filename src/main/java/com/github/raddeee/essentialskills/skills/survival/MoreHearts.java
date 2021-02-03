package com.github.raddeee.essentialskills.skills.survival;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class MoreHearts {

    public static void morehearts(Player player, int skillevel){

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + skillevel * 4);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "" + ChatColor.BOLD + "Last Stand" + ChatColor.WHITE + " has been activated"));


    }

}
