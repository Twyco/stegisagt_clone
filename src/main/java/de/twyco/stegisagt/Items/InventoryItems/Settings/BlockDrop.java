package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockDrop extends ItemStack {

    public BlockDrop(){
        super();
        this.setType(Material.HOPPER);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Blockdrop");
        this.setItemMeta(itemMeta);
    }

}
