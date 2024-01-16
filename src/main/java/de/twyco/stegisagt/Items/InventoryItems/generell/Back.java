package de.twyco.stegisagt.Items.InventoryItems.generell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Back extends ItemStack {

    public Back(){
        super(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.RED + "Zurück zur Minigame Auswahl");
        setItemMeta(itemMeta);
    }

}
