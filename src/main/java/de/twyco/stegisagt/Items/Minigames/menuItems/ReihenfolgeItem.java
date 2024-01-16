package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReihenfolgeItem extends ItemStack {

    public ReihenfolgeItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(6);
        itemMeta.setDisplayName(ChatColor.GOLD + "Reihenfolge" + ChatColor.RED + ChatColor.BOLD + " NEW");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Spieler müssen");
        lore.add("sich die Reihenfolge merken");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
