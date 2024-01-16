package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class BlockDropItemListener implements Listener {

    @EventHandler
    public void onBlockDrop(final BlockDropItemEvent e){
        if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED) &&
                !Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS) ) {
            if (!Stegisagt.getInstance().isBlockDrop()) {
                e.setCancelled(true);
            }
        }
    }

}
