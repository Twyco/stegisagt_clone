package de.twyco.stegisagt.Inventorys.Settings;

import de.twyco.stegisagt.Items.InventoryItems.Settings.CommandBlock;
import de.twyco.stegisagt.Items.InventoryItems.Settings.DiamondPickaxe;
import de.twyco.stegisagt.Items.InventoryItems.Settings.Visibility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class SettingsMenuInventory {

    private final Inventory inventory;
    private final String title;

    public SettingsMenuInventory() {
        title = ChatColor.GRAY + ChatColor.BOLD.toString() + "Setttings und Tools";
        this.inventory = Bukkit.createInventory(null, 3 * 9, title);
        inventory.setItem(4, new Visibility());
        inventory.setItem(12, new DiamondPickaxe());
        inventory.setItem(14, new CommandBlock());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return this.title;
    }

}
