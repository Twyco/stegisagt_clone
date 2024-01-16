package de.twyco.stegisagt.Items.InventoryItems.Settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandBlock extends ItemStack {

    public CommandBlock(){
        super();
        this.setType(Material.COMMAND_BLOCK);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Settings");
        this.setItemMeta(itemMeta);
    }

}

