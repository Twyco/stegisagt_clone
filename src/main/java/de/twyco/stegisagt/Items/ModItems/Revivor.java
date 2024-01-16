package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Revivor extends ItemStack {

    public Revivor(){
        super();
        this.setType(Material.BLAZE_ROD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Wiederbeleber");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Rechtsklick auf eine tote Person zum Wiederbeleben");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
