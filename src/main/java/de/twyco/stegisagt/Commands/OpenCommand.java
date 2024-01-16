package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.stegisagt.Util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OpenCommand implements CommandExecutor, TabCompleter {

    private final Stegisagt instance;

    public OpenCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            s.sendMessage(ChatColor.RED + "/open <Max Spieleranzahl>");
            return false;
        }
        if (s.isOp()) {
            switch (Stegisagt.getGameStatus()) {
                case CLOSED -> {
                    if (!allLocationsSet(s)) {
                        return false;
                    }

                    int maxPlayer;
                    try {
                        maxPlayer = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        s.sendMessage(ChatColor.RED + "/open <Max Spieleranzahl>");
                        return false;
                    }
                    if (maxPlayer < 2) {
                        s.sendMessage(ChatColor.RED + "Die mindestanzahl von Spielern beträgt 2!");
                        return false;
                    }
                    instance.setMaxPlayer(maxPlayer);
                    Stegisagt.setGameStatus(GameStatus.WAITING_FOR_PLAYERS);
                    if (s instanceof Player) {
                        instance.addMod((Player) s);
                    }
                    s.sendMessage(ChatColor.GREEN + "Es wurde eine Runde mit " + ChatColor.YELLOW + maxPlayer + " Spielern" + ChatColor.GREEN + " gestartet.");
                    s.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "ES GIBT NUN EINEN /FIX COMMAND");
                    Bukkit.setWhitelist(false);
                    return true;
                }
                case PLAYING, WAITING_FOR_PLAYERS -> {
                    s.sendMessage(ChatColor.RED + "Stegi sagt läuft bereits!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean allLocationsSet(CommandSender sender) {
        boolean allAreSet = true;
        Config config = Stegisagt.getInstance().getLocationsConfig();
        Location start = config.getFileConfiguration().getLocation("Locations.spawn.start");
        Location login = config.getFileConfiguration().getLocation("Locations.spawn.login");
        Location dead = config.getFileConfiguration().getLocation("Locations.spawn.dead");
        StringBuilder stringBuilder = new StringBuilder().append(ChatColor.RED + "Es fehlen noch folgende Positionen:" + ChatColor.YELLOW);
        if (start == null) {
            allAreSet = false;
            stringBuilder.append(" Locations.spawn.start,");
        }
        if (login == null) {
            allAreSet = false;
            stringBuilder.append(" Locations.spawn.login,");
        }
        if (dead == null) {
            allAreSet = false;
            stringBuilder.append(" Locations.spawn.dead,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(ChatColor.RED + ".\nBenutze /set <location/loc> <start/login/dead>");
        if (!allAreSet) {
            sender.sendMessage(stringBuilder.toString());
        }
        return allAreSet;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
                list.add("Spieleranzahl");
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
