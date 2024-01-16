package de.twyco.stegisagt.Inventorys.Settings;

import de.twyco.stegisagt.Items.InventoryItems.Tools.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class ToolsInventory {

    private final Inventory inventory;
    private final String title;

    public ToolsInventory() {
        title = ChatColor.GRAY + ChatColor.BOLD.toString() + "Tools";
        this.inventory = Bukkit.createInventory(null, 9, title);
        inventory.setItem(0, new IronTools());
        inventory.setItem(1, new BowTool());
        inventory.setItem(2, new ArrowTool());
        inventory.setItem(6, new AliveChest());
        inventory.setItem(7, new DeadChest());
        inventory.setItem(8, new InvClear());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return this.title;
    }

}
