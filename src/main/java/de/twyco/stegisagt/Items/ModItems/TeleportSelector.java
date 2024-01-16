package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeleportSelector extends ItemStack {

    public TeleportSelector(){
        super();
        this.setType(Material.END_PORTAL_FRAME);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Teleporter");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Du kannst auch ein Minigame nur mit ausgewählten Spielern spielen!");
        lore.add(ChatColor.YELLOW + "Linksklick zum Auswählen");
        lore.add(ChatColor.YELLOW + "Rechtsklick zum Entfernen");
        lore.add(ChatColor.YELLOW + "Rechtsklick auf Block zum Teleportieren");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
