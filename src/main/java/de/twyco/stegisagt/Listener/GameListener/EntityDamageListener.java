package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent e) {
        if (Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
            }
        } else if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED)) {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                if (Stegisagt.isMod(player.getUniqueId())) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    if (!Stegisagt.getInstance().isFallDamage()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
