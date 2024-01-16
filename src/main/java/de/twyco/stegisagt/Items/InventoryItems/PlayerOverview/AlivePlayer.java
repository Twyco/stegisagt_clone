package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlivePlayer extends ItemStack {

    public AlivePlayer() {
        super();
        this.setType(Material.PLAYER_HEAD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Lebende Spieler");
        this.setItemMeta(itemMeta);
    }

}
