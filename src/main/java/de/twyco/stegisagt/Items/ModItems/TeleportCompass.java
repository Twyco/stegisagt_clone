package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeleportCompass extends ItemStack {

    public TeleportCompass() {
        super(Material.COMPASS, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.RED + "Teleport Compass");
        setItemMeta(itemMeta);
    }

}
