package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Attackspeed1_8 extends ItemStack {

    public Attackspeed1_8(){
        super();
        this.setType(Material.DIAMOND_SWORD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "1.8");
        this.setItemMeta(itemMeta);
    }

}
