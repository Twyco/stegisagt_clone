package de.twyco.stegisagt.Items.InventoryItems.Teleporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeadTeleport extends ItemStack {

    public DeadTeleport() {
        super(Material.RECOVERY_COMPASS, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "Toten Teleport");
        setItemMeta(itemMeta);
    }

}
