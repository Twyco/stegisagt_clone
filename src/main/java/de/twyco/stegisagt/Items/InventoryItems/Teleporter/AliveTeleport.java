package de.twyco.stegisagt.Items.InventoryItems.Teleporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class AliveTeleport extends ItemStack {

    public AliveTeleport(){
        super(Material.COMPASS, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "Lebenden Teleport");
        setItemMeta(itemMeta);
    }
}
