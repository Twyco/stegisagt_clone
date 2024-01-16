package de.twyco.stegisagt.Inventorys.Minigames;

import de.twyco.stegisagt.Items.InventoryItems.generell.Back;
import de.twyco.stegisagt.Items.Minigames.*;
import de.twyco.stegisagt.MinigamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class MoleraceInventory {

    private final Inventory inventory;
    private final String title;

    public MoleraceInventory() {
        title = ChatColor.GOLD + ChatColor.BOLD.toString() + "Molerace";
        this.inventory = Bukkit.createInventory(null, 3 * 9, title);
        inventory.setItem(3, new SettingButtonUp(0));
        inventory.setItem(12, new SpielerAnzahl());
        inventory.setItem(9, new Back());
        inventory.setItem(14, new AlivePlayer());
        inventory.setItem(17, new Start());
        inventory.setItem(21, new SettingButtonDown(0));
    }


    public String getTitle() {
        return this.title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory getInventory(int playerCount, MinigamePlayers players) {
        inventory.setItem(3, new SettingButtonUp(playerCount));
        switch (players){
            case DEAD -> {
                inventory.setItem(14, new DeadPlayer());
            }
            case ALIVE -> {
                inventory.setItem(14, new AlivePlayer());
            }
            case SELECTED -> {
                inventory.setItem(14, new SelectedPlayer());
            }
        }
        inventory.setItem(21, new SettingButtonDown(playerCount));
        return inventory;
    }

}
