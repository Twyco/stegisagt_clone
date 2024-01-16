package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    private boolean cooldown = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!cooldown){
            Bukkit.broadcastMessage(ChatColor.BLUE + "Joine dem Discord von Stegi hier: " + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "https://discord.gg/stegi");
            cooldown = true;
            Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), new Runnable() {
                @Override
                public void run() {
                    cooldown = false;
                }
            }, 20 * 20L);
        }
        return true;
    }
}
