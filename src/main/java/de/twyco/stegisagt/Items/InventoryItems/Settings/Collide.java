package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Collide extends ItemStack {

    public Collide(){
        super();
        this.setType(Material.PLAYER_HEAD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Spielkollision");
        this.setItemMeta(itemMeta);
    }

}

