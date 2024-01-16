package de.twyco.stegisagt.Items.SelectionItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoinflipSelection extends ItemStack {

    public CoinflipSelection() {
        super(Material.WOODEN_SHOVEL, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setDisplayName(ChatColor.GOLD + "Coinflip Selection");
        setItemMeta(itemMeta);
    }

}
