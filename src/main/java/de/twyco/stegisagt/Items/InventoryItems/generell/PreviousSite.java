package de.twyco.stegisagt.Items.InventoryItems.generell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PreviousSite extends ItemStack {

    private static final String lebendeLore = "zur Lebenden Spieler Übersicht";
    private static final String toteLore = "zur Toten Spieler Übersicht";

    public PreviousSite(int site) {
        super();
        this.setType(Material.RED_STAINED_GLASS_PANE);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Vorherige Seite");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(site + "");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

    public PreviousSite(int site, boolean dead) {
        super();
        this.setType(Material.RED_STAINED_GLASS_PANE);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Zurück");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(dead ? toteLore : lebendeLore);
        lore.add(site + "");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

    public static String getToteLore() {
        return toteLore;
    }

    public static String getLebendeLore() {
        return lebendeLore;
    }
}
