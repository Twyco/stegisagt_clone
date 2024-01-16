package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemsCommand implements CommandExecutor {

    private final Stegisagt instance;

    public ItemsCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (Stegisagt.isMod(p.getUniqueId())) {
                instance.giveModItems(p);
                return true;
            }
            return false;
        }
        return false;
    }
}
