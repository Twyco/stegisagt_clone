package de.twyco.stegisagt.Items.InventoryItems.Tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ArrowTool extends ItemStack {

    public ArrowTool(){
        super();
        this.setType(Material.ARROW);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "16 Pfeile");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Linksklick: Alle Lebenden");
        lore.add("Rechtsklick: Alle Toten");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
