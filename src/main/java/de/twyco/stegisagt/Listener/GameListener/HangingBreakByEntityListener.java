package de.twyco.stegisagt.Listener.GameListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class HangingBreakByEntityListener implements Listener {

    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event){
        event.setCancelled(true);
    }
}
