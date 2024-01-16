package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MoleRaceItem extends ItemStack {

    public MoleRaceItem() {
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(5);
        itemMeta.setDisplayName(ChatColor.GOLD + "Molerace");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Spieler müssen sich schnell durch");
        lore.add("verschiedene Materialien graben");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
