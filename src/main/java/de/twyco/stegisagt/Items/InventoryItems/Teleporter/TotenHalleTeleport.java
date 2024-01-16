package de.twyco.stegisagt.Items.InventoryItems.Teleporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TotenHalleTeleport extends ItemStack {

    public TotenHalleTeleport() {
        super(Material.SKELETON_SKULL, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "zur Totenhalle");
        setItemMeta(itemMeta);
    }

}
