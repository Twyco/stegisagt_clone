package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Items.SelectionItems.CoinflipSelection;
import de.twyco.stegisagt.Miigames.CoinFlip;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinflipCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player) {
            Player player = (Player) s;
            if (!(player.isOp() || Stegisagt.isMod(player.getUniqueId()))) {
                return false;
            }
            if (args.length == 0) {
                CoinFlip.flipCoin(player);
                return true;
            }else if (args.length == 1) {
                if(args[0].equalsIgnoreCase("item")){
                    player.getInventory().addItem(new CoinflipSelection());
                    return true;
                }
                Player flipPlayer;
                flipPlayer = Bukkit.getPlayer(args[0]);
                if (flipPlayer == null) {
                    return false;
                }
                CoinFlip.flipCoin(player, flipPlayer);
                return true;
            }
        }
        return false;
    }


}
