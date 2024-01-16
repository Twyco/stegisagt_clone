package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import de.twyco.stegisagt.Inventorys.PlayerOverview.DeadPlayerInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SortByItem extends ItemStack {

    public SortByItem(){
        super(Material.OAK_SIGN, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setDisplayName(ChatColor.GOLD + "Sortiert nach: " + (DeadPlayerInventory.isAlphabetSort() ? "Alphabet" : "Todeszeit"));
        setItemMeta(itemMeta);
    }

}
