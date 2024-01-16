package de.twyco.stegisagt.Inventorys.Minigames;

import de.twyco.stegisagt.Items.InventoryItems.generell.Back;
import de.twyco.stegisagt.Items.Minigames.*;
import de.twyco.stegisagt.MinigamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class TrueOrFalseInventory {

    private final Inventory inventory;
    private final String title;

    public TrueOrFalseInventory() {
        title = ChatColor.GOLD + ChatColor.BOLD.toString() + "True or False";
        this.inventory = Bukkit.createInventory(null, 3 * 9, title);
        inventory.setItem(9, new Back());
        inventory.setItem(13, new AlivePlayer());
        inventory.setItem(17, new Start());
    }

    public String getTitle() {
        return this.title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory getInventory(MinigamePlayers players) {
        switch (players) {
            case DEAD -> {
                inventory.setItem(13, new DeadPlayer());
            }
            case ALIVE -> {
                inventory.setItem(13, new AlivePlayer());
            }
            case SELECTED -> {
                inventory.setItem(13, new SelectedPlayer());
            }
        }
        return inventory;
    }

}
