package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TrueOrFalseItem extends ItemStack {

    public TrueOrFalseItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(10);
        itemMeta.setDisplayName(ChatColor.GOLD + "Wahr oder Falsch");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Hier kannst du Fragen mit zwei Antworten stellen.");
        lore.add(ChatColor.GRAY + "Spieler mit der Falschen antwort sterben.");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
