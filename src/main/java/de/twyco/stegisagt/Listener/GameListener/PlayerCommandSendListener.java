package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class PlayerCommandSendListener implements Listener {

    @EventHandler
    public void onPlayerCommandSend(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId()) || player.isOp()) {
            return;
        }
        event.getCommands().clear();
        event.getCommands().add("dc");
        event.getCommands().add("discord");
        event.getCommands().add("msg");
    }

    //TODO CONFIG BAUEN
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId()) || player.isOp()) {
            return;
        }
        List<String> allowedCommand = new ArrayList<>();
        allowedCommand.add("/dc");
        allowedCommand.add("/discord");
        allowedCommand.add("/msg");
        allowedCommand.add("/hub");
        allowedCommand.add("/l");
        allowedCommand.add("/lobby");
        if (allowedCommand.contains(event.getMessage())) {
            return;
        }
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "Unknown or incomplete command, see below for error");
        player.sendMessage(ChatColor.RED + ChatColor.UNDERLINE.toString() + event.getMessage().substring(1)
                + ChatColor.RED + ChatColor.ITALIC + "<--[HERE]");
    }

}
