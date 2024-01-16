package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Items.ModItems.Revivor;
import de.twyco.stegisagt.Items.ModItems.Sword;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (!Stegisagt.isMod(damager.getUniqueId())) {
                    event.setCancelled(true);
                }
            }
        } else if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED)) {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (Stegisagt.isMod(damager.getUniqueId())) {
                    if(event.getEntity() instanceof Player){
                        if (damager.getInventory().getItemInMainHand().getType().equals(new Sword().getType())) {
                            if (!damager.getInventory().getItemInMainHand().getItemMeta().equals(new Sword().getItemMeta())) {
                                return;
                            }
                            event.setCancelled(true);
                            Player p = (Player) event.getEntity();
                            Stegisagt.killPlayer(p);
                            return;
                        }else if (damager.getInventory().getItemInMainHand().getType().equals(new Revivor().getType())){
                            if (!damager.getInventory().getItemInMainHand().getItemMeta().equals(new Revivor().getItemMeta())) {
                                return;
                            }
                            event.setCancelled(true);
                            Player p = (Player) event.getEntity();
                            if(Stegisagt.isAlive(p.getUniqueId())){
                                return;
                            }
                            Stegisagt.revivePlayer(p, damager.getLocation());
                        }
                    }
                    event.setCancelled(false);
                    return;
                }
                if (!Stegisagt.getInstance().isPvp()) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
