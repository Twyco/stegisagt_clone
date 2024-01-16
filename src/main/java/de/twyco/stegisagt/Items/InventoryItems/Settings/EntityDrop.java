package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDrop extends ItemStack {

    public EntityDrop(){
        super();
        this.setType(Material.ROTTEN_FLESH);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Mobdrop");
        this.setItemMeta(itemMeta);
    }

}
