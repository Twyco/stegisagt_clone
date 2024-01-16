package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HidenSeekItem extends ItemStack {

    public HidenSeekItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(3);
        itemMeta.setDisplayName(ChatColor.GOLD + "Hide n Seek");
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
