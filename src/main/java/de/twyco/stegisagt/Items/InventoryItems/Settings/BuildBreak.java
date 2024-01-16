package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildBreak extends ItemStack {

    public BuildBreak(){
        super();
        this.setType(Material.DIAMOND_PICKAXE);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Abbauen");
        this.setItemMeta(itemMeta);
    }

}
