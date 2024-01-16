package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {

    private final Stegisagt instance;

    public ResetCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            instance.getResetConfig().getFileConfiguration().set("World.isReset", true);
            instance.getResetConfig().save();
            Bukkit.shutdown();
            return true;
        }
        return false;
    }
}
