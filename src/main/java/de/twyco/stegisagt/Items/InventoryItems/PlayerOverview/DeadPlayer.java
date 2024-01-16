package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeadPlayer extends ItemStack {

    public DeadPlayer() {
        super();
        this.setType(Material.SKELETON_SKULL);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Tote Spieler");
        this.setItemMeta(itemMeta);
    }

}
