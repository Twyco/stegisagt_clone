package de.twyco.stegisagt.Inventorys.Teleport;

import de.twyco.stegisagt.Items.InventoryItems.Teleporter.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class TeleportInventory {

    private final Inventory inventory;
    private final String title;

    public TeleportInventory() {
        title = ChatColor.GRAY + ChatColor.BOLD.toString() + "Teleport Übersicht";
        this.inventory = Bukkit.createInventory(null, 3 * 9, title);
        inventory.setItem(4, new LobbyTeleport());
        inventory.setItem(12, new AliveTeleport());
        inventory.setItem(14, new DeadTeleport());
        inventory.setItem(22, new TotenHalleTeleport());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return this.title;
    }

}
