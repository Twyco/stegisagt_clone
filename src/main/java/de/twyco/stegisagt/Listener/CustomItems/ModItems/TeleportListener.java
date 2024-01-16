package de.twyco.stegisagt.Listener.CustomItems.ModItems;

import de.twyco.stegisagt.Items.ModItems.MinigamesSelector;
import de.twyco.stegisagt.Items.ModItems.TeleportSelector;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class TeleportListener implements Listener {

    private Stegisagt instance;

    public TeleportListener() {
        instance = Stegisagt.getInstance();
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId())) {
            if (!player.getItemInHand().getType().equals(new TeleportSelector().getType())) {
                return;
            }
            if (!player.getItemInHand().getItemMeta().equals(new TeleportSelector().getItemMeta())) {
                return;
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        Player clicker = e.getPlayer();
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || !Stegisagt.isMod(clicker.getUniqueId()) ||
                clicker.getItemInHand().getType().equals(Material.AIR)) {
            return;
        }
        if (!clicker.getItemInHand().getType().equals(new TeleportSelector().getType()) ||
                !clicker.getItemInHand().getItemMeta().equals(new TeleportSelector().getItemMeta())) {
            return;
        }
        e.setCancelled(true);
        for (Player p : instance.getSelectedPlayer()) {
            p.teleport(e.getClickedBlock().getLocation().add(0.5, 1, 0.5));
            p.removePotionEffect(PotionEffectType.GLOWING);
        }
        instance.clearSelectedPlayer();
    }

    @EventHandler
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            Player clicker = e.getPlayer();
            if (Stegisagt.isMod(clicker.getUniqueId()) && e.getRightClicked() instanceof Player && clicker.getItemInHand() != null) {
                if (!clicker.getInventory().getItemInMainHand().getType().equals(new TeleportSelector().getType())) {
                    return;
                }
                if (!clicker.getInventory().getItemInMainHand().getItemMeta().equals(new TeleportSelector().getItemMeta())) {
                    return;
                }
                Player p = (Player) e.getRightClicked();
                if (!instance.isSelected(p)) {
                    e.setCancelled(true);
                    return;
                }
                instance.removeSelectedPlayer(p);
                p.removePotionEffect(PotionEffectType.GLOWING);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            if (Stegisagt.isMod(damager.getUniqueId()) && e.getEntity() instanceof Player) {
                damager.getItemInHand();
                if (!damager.getInventory().getItemInMainHand().getType().equals(new TeleportSelector().getType())) {
                    return;
                }
                if (!damager.getInventory().getItemInMainHand().getItemMeta().equals(new TeleportSelector().getItemMeta())) {
                    return;
                }
                Player p = (Player) e.getEntity();
                if (instance.isSelected(p)) {
                    e.setCancelled(true);
                    return;
                }
                instance.addSelectedPlayer(p);
                PotionEffect glow = new PotionEffect(PotionEffectType.GLOWING, 1000000, 1, false, false);
                p.addPotionEffect(glow);
                e.setCancelled(true);
            }
        }
    }

}
