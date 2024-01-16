package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Enderpearl extends ItemStack {

    public Enderpearl(String displayName) {
        super();
        this.setType(Material.ENDER_PEARL);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Teleport you to");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(displayName);
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
