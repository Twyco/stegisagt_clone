package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MinigameCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lable, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0 || Stegisagt.getGameStatus().equals(GameStatus.CLOSED)
        || Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            return list;
        } else if (args.length == 1) {
            switch (Stegisagt.getGameStatus()){
                case PLAYING -> {
                    list.add("Ampelrennen");
                    list.add("Blockparty");
                    list.add("Hide-n-Seek");
                    list.add("MLG");
                    list.add("Molerace");
                    list.add("Schatzsuche");
                    list.add("Snowfall");
                    list.add("TrueOrFalse");
                }
                default -> {
                    list.add("stop");
                    list.add("items");
                }
            }
        } else if (args.length == 2) {
            switch (Stegisagt.getGameStatus()){
                case PLAYING -> {
                    list.add("lebende");
                    list.add("tote");
                    list.add("ausgewählte");
                }
                default -> {
                    list.add("stop");
                    list.add("items");
                }
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
