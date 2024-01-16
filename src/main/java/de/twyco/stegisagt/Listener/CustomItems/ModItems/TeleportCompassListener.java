package de.twyco.stegisagt.Listener.CustomItems.ModItems;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Inventorys.Minigames.MinigamesMenuInventory;
import de.twyco.stegisagt.Inventorys.Teleport.TeleportInventory;
import de.twyco.stegisagt.Items.InventoryItems.Teleporter.AliveTeleport;
import de.twyco.stegisagt.Items.InventoryItems.Teleporter.DeadTeleport;
import de.twyco.stegisagt.Items.InventoryItems.Teleporter.LobbyTeleport;
import de.twyco.stegisagt.Items.InventoryItems.Teleporter.TotenHalleTeleport;
import de.twyco.stegisagt.Items.ModItems.MinigamesSelector;
import de.twyco.stegisagt.Items.ModItems.TeleportCompass;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class TeleportCompassListener implements Listener {

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (hasTeleportCompassInHand(player)) {
            player.openInventory(new TeleportInventory().getInventory());
            player.playSound(player, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        if (event.getView().getTitle().equals(new TeleportInventory().getTitle())) {
            event.setCancelled(true);
            if (itemStack.getType().equals(new AliveTeleport().getType()) && itemMeta.equals(new AliveTeleport().getItemMeta())) {
                for (UUID uuid : Stegisagt.getAlivePlayer()) {
                    Player teleportPlayer = Bukkit.getPlayer(uuid);
                    teleportPlayer.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                }
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
            }else if (itemStack.getType().equals(new DeadTeleport().getType()) && itemMeta.equals(new DeadTeleport().getItemMeta())) {
                for (UUID uuid : Stegisagt.getDeadPlayer()) {
                    Player teleportPlayer = Bukkit.getPlayer(uuid);
                    teleportPlayer.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                }
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
            }else if (itemStack.getType().equals(new TotenHalleTeleport().getType()) && itemMeta.equals(new TotenHalleTeleport().getItemMeta())) {
                Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
                if (location == null) {
                    return;
                }
                player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            }else if (itemStack.getType().equals(new LobbyTeleport().getType()) && itemMeta.equals(new LobbyTeleport().getItemMeta())) {
                Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
                if (location == null) {
                    return;
                }
                player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
        }
    }

    private boolean hasTeleportCompassInHand(Player player) {
        if (!Stegisagt.isMod(player.getUniqueId())) {
            return false;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!itemStack.getType().equals(new TeleportCompass().getType())) {
            return false;
        }
        if (itemStack.getItemMeta() == null) {
            return false;
        }
        return itemStack.getItemMeta().equals(new TeleportCompass().getItemMeta());
    }


}
