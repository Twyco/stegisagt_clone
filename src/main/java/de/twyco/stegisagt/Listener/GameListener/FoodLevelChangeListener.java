package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (Stegisagt.getGameStatus().equals(GameStatus.CLOSED) ||
                Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            e.setCancelled(true);
        } else {
            if (!(e.getEntity() instanceof Player)) {
                return;
            }
            if (!Stegisagt.getInstance().isHunger()) {
                e.setCancelled(true);
            }
        }
    }

}
