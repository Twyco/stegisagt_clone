package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SchatzsucheItem extends ItemStack {

    public SchatzsucheItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(7);
        itemMeta.setDisplayName(ChatColor.GOLD + "Schatzsuche");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Spieler müssen im Boden zufällig");
        lore.add(ChatColor.GRAY + "verstecke Kisten finden");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
