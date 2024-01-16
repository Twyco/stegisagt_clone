package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpielerAnzahl extends ItemStack {

    public SpielerAnzahl(){
        super(Material.PLAYER_HEAD, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "Spieleranzahl");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Anzahl gibt an, wie viele");
        lore.add("Spieler es max. schaffen können.");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
