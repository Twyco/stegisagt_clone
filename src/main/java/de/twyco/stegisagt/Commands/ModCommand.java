package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCommand implements CommandExecutor {

    private final Stegisagt instance;

    public ModCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (args.length == 1) {
            if(!s.isOp()){
                return false;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                return false;
            }
            instance.addMod(player);
        } else {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (!p.isOp()) {
                    return false;
                }
                instance.addMod(p);
                return true;
            }
        }
        return false;
    }
}
