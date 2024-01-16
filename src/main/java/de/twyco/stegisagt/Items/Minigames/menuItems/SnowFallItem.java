package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SnowFallItem extends ItemStack {

    public SnowFallItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(8);
        itemMeta.setDisplayName(ChatColor.GOLD + "Snowfall");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Spieler müssen mehreren Wellen von");
        lore.add("herunterfallenden Schneebällen ausweichen");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}