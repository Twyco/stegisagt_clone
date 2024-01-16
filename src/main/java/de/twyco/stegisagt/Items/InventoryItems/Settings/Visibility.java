package de.twyco.stegisagt.Items.InventoryItems.Settings;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Visibility extends ItemStack {

    public Visibility() {
        if(Stegisagt.getInstance().isPlayerVisibility()){
            setType(Material.LIME_DYE);
            setAmount(1);
            ItemMeta itemMeta = getItemMeta();
            if(itemMeta == null){
                return;
            }
            itemMeta.setDisplayName(ChatColor.GREEN + "Sichtbar");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Die Spieler sehen sich aktuell gegenseitig");
            itemMeta.setLore(lore);
            setItemMeta(itemMeta);
        }else {
            setType(Material.GRAY_DYE);
            setAmount(1);
            ItemMeta itemMeta = getItemMeta();
            if(itemMeta == null){
                return;
            }
            itemMeta.setDisplayName(ChatColor.GRAY + "Unsichtbar");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Die Spieler sehen sich aktuell nicht gegenseitig");
            itemMeta.setLore(lore);
            setItemMeta(itemMeta);
        }
    }

}
