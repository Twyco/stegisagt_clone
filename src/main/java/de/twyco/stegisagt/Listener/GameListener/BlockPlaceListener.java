package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED) &&
                !Stegisagt.getGameStatus().equals(GameStatus.PLAYING_MLG)) {
            Player p = e.getPlayer();
            if (p.isOp() || Stegisagt.isMod(p.getUniqueId())) {
                return;
            }
            if (!Stegisagt.getInstance().isBuildPlace()) {
                e.setCancelled(true);
            }
        }
    }

}
