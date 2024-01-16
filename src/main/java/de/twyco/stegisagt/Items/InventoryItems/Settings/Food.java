package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Food extends ItemStack {

    public Food(){
        super();
        this.setType(Material.BREAD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Hunger");
        this.setItemMeta(itemMeta);
    }

}
