package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FallDamage extends ItemStack {

    public FallDamage(){
        super();
        this.setType(Material.IRON_BOOTS);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Fallschaden");
        this.setItemMeta(itemMeta);
    }

}
