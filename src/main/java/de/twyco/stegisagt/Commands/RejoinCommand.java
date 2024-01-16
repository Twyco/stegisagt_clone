package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RejoinCommand implements CommandExecutor {

    private final Stegisagt instance;

    public RejoinCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.isOp()) {
            if (args.length != 1) {
                s.sendMessage(ChatColor.RED + "/rejoin <Name>");
                return false;
            }
            if(args[0].equalsIgnoreCase("true")){
                instance.setRejoin(true);
                s.sendMessage(ChatColor.GREEN + "Rejoinen ist nun aktiviert.");
                return true;
            }else if(args[0].equalsIgnoreCase("false")){
                instance.setRejoin(false);
                s.sendMessage(ChatColor.RED + "Rejoinen ist nun deaktiviert.");
                return true;
            }
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            if (offlinePlayer == null) {
                s.sendMessage(ChatColor.RED + "Der Spieler existiert nicht!");
                return false;
            }
            s.sendMessage(ChatColor.GREEN + args[0] + " kann nun nach joinen.");
            instance.addLeavedPlayer(offlinePlayer.getUniqueId());
            return true;
        }
        return false;
    }
}
