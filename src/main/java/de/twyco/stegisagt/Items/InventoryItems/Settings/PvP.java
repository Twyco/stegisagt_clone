package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PvP extends ItemStack {

    public PvP(){
        super();
        this.setType(Material.IRON_SWORD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "PvP");
        this.setItemMeta(itemMeta);
    }

}
