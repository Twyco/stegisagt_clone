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

public class ReviveCommand implements CommandExecutor, TabCompleter {

    public ReviveCommand() {
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            s.sendMessage(ChatColor.RED + "/revive <Spielername>");
            return false;
        }
        Player player = (Player) s;
        if (s.isOp() || Stegisagt.isMod(player.getUniqueId())) {
            Player revivePlayer;
            revivePlayer = Bukkit.getPlayer(args[0]);
            if (revivePlayer == null) {
                return false;
            }
            Stegisagt.revivePlayer(revivePlayer, ((Player) s).getLocation());
            return true;

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 0) return list;
        if(args.length == 1){
            for (UUID uuid : Stegisagt.getDeadPlayer()){
                Player player = Bukkit.getPlayer(uuid);
                assert player != null;
                list.add(player.getName());
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentArg = args[args.length-1].toLowerCase();
        for(String s : list){
            String s1 = s.toLowerCase();
            if(s1.startsWith(currentArg)){
                completerList.add(s);
            }
        }
        return completerList;
    }
}
