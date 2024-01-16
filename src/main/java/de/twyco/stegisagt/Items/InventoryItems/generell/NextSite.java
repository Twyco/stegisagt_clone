package de.twyco.stegisagt.Items.InventoryItems.generell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NextSite extends ItemStack {

    public NextSite(int site){
        super();
        this.setType(Material.LIME_STAINED_GLASS_PANE);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Nächste Seite");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(site + "");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
