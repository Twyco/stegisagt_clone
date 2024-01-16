package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MSGCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lable, String[] args) {
        if (args.length < 2) {
            return false;
        }
        Player receivePlayer = Bukkit.getPlayer(args[0]);
        if (receivePlayer == null) {
            return false;
        }
        StringBuilder message = new StringBuilder();
        args[0] = "";
        for (String msg : args) {
            message.append(" ");
            message.append(msg);
        }

        String senderName;
        String receiveName;
        if (Stegisagt.isMod(receivePlayer.getUniqueId())) {
            receiveName = ChatColor.DARK_PURPLE + "Mod" + ChatColor.GRAY + " | " + receivePlayer.getName();
        } else if (Stegisagt.isDead(receivePlayer.getUniqueId())) {
            receiveName = ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + ChatColor.GRAY + receivePlayer.getName();
        } else {
            receiveName = (receivePlayer.isOp() ? ChatColor.RED : ChatColor.GRAY) + receivePlayer.getName();
        }
        final String msg = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "You" + ChatColor.DARK_GRAY + " -> " + receiveName + ChatColor.DARK_GRAY + "]"
                + ChatColor.GOLD + ChatColor.BOLD + " >>" + ChatColor.WHITE + message;
        if (s instanceof Player) {
            Player player = (Player) s;
            if (Stegisagt.isMod(player.getUniqueId())) {
                senderName = ChatColor.DARK_PURPLE + "Mod" + ChatColor.GRAY + " | " + player.getName();
            } else if (Stegisagt.isDead(player.getUniqueId())) {
                senderName = ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + ChatColor.GRAY + player.getName();
            } else {
                senderName = (player.isOp() ? ChatColor.RED : ChatColor.GRAY) + player.getName();
            }
            player.sendMessage(msg);
        } else {
            senderName = ChatColor.DARK_RED + "CONSOLE";
                    Bukkit.getConsoleSender().sendMessage(msg);
        }

        receivePlayer.sendMessage(ChatColor.DARK_GRAY + "[" + senderName + ChatColor.DARK_GRAY + " -> " + ChatColor.WHITE + "You" + ChatColor.DARK_GRAY + "]"
                + ChatColor.GOLD + ChatColor.BOLD + " >>" + ChatColor.WHITE + message);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentArg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentArg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }
}
