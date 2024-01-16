package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MLGItem extends ItemStack {

    public MLGItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(4);
        itemMeta.setDisplayName(ChatColor.GOLD + "MLG");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Spieler müssen");
        lore.add("verschiedene MLG schaffen");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
