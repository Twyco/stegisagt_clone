package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (!player.isOp()) {
            return false;
        }
        String usage = ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Dieser command ist experimentell und sollte nur genutzt werden, " +
                "wenn es wirklich ein Problem gibt.\n"
                + ChatColor.YELLOW + "Benutzung bei folgenden Problemen:\n"
                + ChatColor.GRAY + " - " + ChatColor.GOLD + "/fix player " + ChatColor.GRAY + "sollte die Spieler-übersicht fixen!\n"
                + ChatColor.GRAY + " - " + ChatColor.GOLD + "/fix minigame " + ChatColor.GRAY + " sollte fixen,dass kein Minigame startet!\n"
                + ChatColor.GRAY + " - " + ChatColor.GOLD + "/fix all " + ChatColor.GRAY + " sollten beide oberen nicht funktionieren!!\n";
        if (args.length != 1) {
            player.sendMessage(usage);
            return false;
        }
        if (args[0].equals("player")) {
            Stegisagt.fixModPlayer();
            Stegisagt.fixAlivePlayer();
            Stegisagt.fixDeadPlayer();
            Stegisagt.updateScoreboard();
            Bukkit.getOnlinePlayers().forEach(player1 -> {
                Bukkit.getOnlinePlayers().forEach(player2 -> {
                    if (!player1.getName().equals(player2.getName())) {
                        player1.showPlayer(Stegisagt.getInstance(), player2);
                    }
                });
                Stegisagt.teleportToAliveOrDead(player1);
            });
            player.sendMessage(ChatColor.RED + "Try fixing player...");
            return false;
        } else if (args[0].equals("minigame")) {
            Stegisagt.setGameStatus(GameStatus.PLAYING);
            player.sendMessage(ChatColor.RED + "Try fixing minigame...");
        } else if (args[0].equals("all")) {
            Stegisagt.setGameStatus(GameStatus.PLAYING);
            Stegisagt.fixModPlayer();
            Stegisagt.fixAlivePlayer();
            Stegisagt.fixDeadPlayer();
            Stegisagt.updateScoreboard();
            Stegisagt.getInstance().setPvp(false);
            Stegisagt.getInstance().setFallDamage(false);
            Stegisagt.getInstance().setHunger(false);
            Stegisagt.getInstance().setBuildPlace(false);
            Stegisagt.getInstance().setBuildBreak(false);
            Stegisagt.getInstance().setBlockDrop(false);
            Stegisagt.getInstance().setPlayerCollision(false);
            Stegisagt.getInstance().setEntityDrop(false);
            Stegisagt.getInstance().setPlayerVisibility(true);
            Bukkit.getOnlinePlayers().forEach(player1 -> {
                Bukkit.getOnlinePlayers().forEach(player2 -> {
                    if (!player1.getName().equals(player2.getName())) {
                        player1.showPlayer(Stegisagt.getInstance(), player2);
                    }
                });
                Stegisagt.teleportToAliveOrDead(player1);
            });
            player.sendMessage(ChatColor.RED + "Try fixing all...");
        } else {
            player.sendMessage(usage);
        }
        return false;
    }
}
