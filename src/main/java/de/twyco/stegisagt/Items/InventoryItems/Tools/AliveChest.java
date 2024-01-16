package de.twyco.stegisagt.Items.InventoryItems.Tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AliveChest extends ItemStack {

    public AliveChest(){
        super();
        this.setType(Material.CHEST);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Items für lebende Spieler");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Items werden an alle lebenden Spieler verteilt");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}

