package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AmpelrennenItem extends ItemStack {

    public AmpelrennenItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setCustomModelData(1);
        itemMeta.setDisplayName(ChatColor.GOLD + "Ampelrennen");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Spieler müssen ins Ziel laufen,");
        lore.add(ChatColor.GRAY + "dürfen aber nur bei grün laufen.");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}