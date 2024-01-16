package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MinigamesSelector extends ItemStack {

    public MinigamesSelector() {
        super();
        this.setType(Material.REPEATER);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(1);
        itemMeta.setDisplayName(ChatColor.RED + "Minispiele übersicht");
        this.setItemMeta(itemMeta);
    }

}
