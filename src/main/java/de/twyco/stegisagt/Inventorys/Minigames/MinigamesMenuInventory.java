package de.twyco.stegisagt.Inventorys.Minigames;

import de.twyco.stegisagt.Items.Minigames.menuItems.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class MinigamesMenuInventory {

    private final Inventory inventory;
    private final String title;

    public MinigamesMenuInventory() {
        title = ChatColor.RED + ChatColor.BOLD.toString() + "Minigames";
        this.inventory = Bukkit.createInventory(null, 5 * 9, title);
        inventory.addItem(new ReihenfolgeItem());
        inventory.addItem(new SpleefItem());
        inventory.addItem(new BlockPartyItem());
        inventory.addItem(new AmpelrennenItem());
        inventory.addItem(new SnowFallItem());
        inventory.addItem(new SchatzsucheItem());
        inventory.addItem(new MoleRaceItem());
        inventory.addItem(new HidenSeekItem());
        inventory.addItem(new MLGItem());
        inventory.addItem(new TrueOrFalseItem());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return this.title;
    }

}
