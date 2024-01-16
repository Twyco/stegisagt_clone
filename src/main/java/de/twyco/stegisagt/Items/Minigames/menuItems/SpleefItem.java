package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpleefItem extends ItemStack {

    public SpleefItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(9);
        itemMeta.setDisplayName(ChatColor.GOLD + "Spleef" + ChatColor.RED + ChatColor.BOLD + " NEW");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Spleef halt");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}