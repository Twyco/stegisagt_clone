package de.twyco.stegisagt.Items.InventoryItems.generell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Bucket extends ItemStack {

    public Bucket(String displayName) {
        super();
        this.setType(Material.BUCKET);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Clear Inventory");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(displayName);
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
