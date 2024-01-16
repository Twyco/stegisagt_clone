package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingButtonDown extends ItemStack {

    public SettingButtonDown(int n) {
        super(Material.STONE_BUTTON, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY.toString() + n);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Klick - 1");
        lore.add("Shift & Klick - 5");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
