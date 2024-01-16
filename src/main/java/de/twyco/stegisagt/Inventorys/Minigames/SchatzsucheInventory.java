package de.twyco.stegisagt.Inventorys.Minigames;

import de.twyco.stegisagt.Items.InventoryItems.generell.Back;
import de.twyco.stegisagt.Items.Minigames.*;
import de.twyco.stegisagt.Items.Minigames.ExtraSettings.SchatzsucheExtraSetting;
import de.twyco.stegisagt.MinigamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class SchatzsucheInventory {

    private final Inventory inventory;
    private final String title;

    public SchatzsucheInventory() {
        title = ChatColor.GOLD + ChatColor.BOLD.toString() + "Schatzsuche";
        this.inventory = Bukkit.createInventory(null, 3 * 9, title);
        inventory.setItem(3, new SettingButtonUp(0));
        inventory.setItem(5, new SettingButtonUp(0));
        inventory.setItem(12, new SpielerAnzahl());
        inventory.setItem(14, new SchatzsucheExtraSetting());
        inventory.setItem(9, new Back());
        inventory.setItem(13, new AlivePlayer());
        inventory.setItem(17, new Start());
        inventory.setItem(21, new SettingButtonDown(0));
        inventory.setItem(23, new SettingButtonDown(0));
    }


    public String getTitle() {
        return this.title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory getInventory(int playerCount, int chestCount, MinigamePlayers players) {
        inventory.setItem(3, new SettingButtonUp(playerCount));
        inventory.setItem(5, new SettingButtonUp(chestCount));
        switch (players){
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
        inventory.setItem(21, new SettingButtonDown(playerCount));
        inventory.setItem(23, new SettingButtonDown(chestCount));
        return inventory;
    }

}
