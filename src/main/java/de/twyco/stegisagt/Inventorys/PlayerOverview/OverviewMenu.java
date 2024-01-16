package de.twyco.stegisagt.Inventorys.PlayerOverview;

import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.AlivePlayer;
import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.DeadPlayer;
import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.SortByItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class OverviewMenu {

    private final Inventory inventory;
    private final String title;

    public OverviewMenu(){
        title = ChatColor.BLUE + "Spieler übersicht";
        this.inventory = Bukkit.createInventory(null, 3*9, title);
        inventory.setItem(12, new AlivePlayer());
        inventory.setItem(14, new DeadPlayer());
        inventory.setItem(15, new SortByItem());
    }

    public Inventory getInventory(){
        return inventory;
    }

    public String getTitle(){
        return this.title;
    }

}
