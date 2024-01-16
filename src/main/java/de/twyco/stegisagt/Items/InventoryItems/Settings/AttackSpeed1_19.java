package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AttackSpeed1_19 extends ItemStack {

    public AttackSpeed1_19(){
        super();
        this.setType(Material.NETHERITE_AXE);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "1.19");
        this.setItemMeta(itemMeta);
    }

}
