package de.twyco.stegisagt.Items.Minigames.ExtraSettings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SchatzsucheExtraSetting extends ItemStack {

    public SchatzsucheExtraSetting(){
        super(Material.CHEST, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "Anzahl der Versteckten Kisten");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("(Kann höher sein als max Spieler)");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
