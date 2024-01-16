package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Settigns extends ItemStack {

    public Settigns() {
        super();
        this.setType(Material.ANVIL);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Settings/Tools");
        this.setItemMeta(itemMeta);
    }

}
