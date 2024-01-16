package de.twyco.stegisagt.Inventorys.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class GiveItemInventory {

    private final Inventory aliveInventory;
    private final Inventory deadInventory;
    private final String aliveTitle;
    private final String deadTitle;

    public GiveItemInventory() {
        aliveTitle = ChatColor.GREEN + ChatColor.BOLD.toString() + "Itemverteiler";
        deadTitle = ChatColor.RED + ChatColor.BOLD.toString() + "Itemverteiler";
        this.aliveInventory = Bukkit.createInventory(null, 4 * 9, aliveTitle);
        this.deadInventory = Bukkit.createInventory(null, 4 * 9, deadTitle);
    }


    public Inventory getAliveInventory() {
        return aliveInventory;
    }

    public Inventory getDeadInventory() {
        return deadInventory;
    }

    public String getAliveTitle() {
        return aliveTitle;
    }

    public String getDeadTitle() {
        return deadTitle;
    }
}
