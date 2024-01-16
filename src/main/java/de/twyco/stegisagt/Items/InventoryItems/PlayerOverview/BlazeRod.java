package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlazeRod extends ItemStack {

    public BlazeRod(String displayName) {
        super();
        this.setType(Material.BLAZE_ROD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Revive");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(displayName);
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
