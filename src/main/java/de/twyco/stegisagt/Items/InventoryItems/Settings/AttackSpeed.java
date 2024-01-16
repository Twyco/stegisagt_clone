package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AttackSpeed extends ItemStack {

    public AttackSpeed(){
        super();
        this.setType(Material.SHIELD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "AttackSpeed");
        this.setItemMeta(itemMeta);
    }

}
