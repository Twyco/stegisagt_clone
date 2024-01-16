package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Start extends ItemStack {

    public Start(){
        super(Material.LIME_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GREEN + "Start");
        setItemMeta(itemMeta);
    }

}
