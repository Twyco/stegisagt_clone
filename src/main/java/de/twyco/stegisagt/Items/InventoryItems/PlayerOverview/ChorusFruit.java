package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ChorusFruit extends ItemStack {

    public ChorusFruit(String displayName) {
        super();
        this.setType(Material.CHORUS_FRUIT);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Teleport Player to you");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(displayName);
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
