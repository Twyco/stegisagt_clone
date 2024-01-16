package de.twyco.stegisagt.Items.InventoryItems.Tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DeadChest extends ItemStack {

    public DeadChest(){
        super();
        this.setType(Material.CHEST);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Items für tote Spieler");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Items werden an alle toten Spieler verteilt");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}

