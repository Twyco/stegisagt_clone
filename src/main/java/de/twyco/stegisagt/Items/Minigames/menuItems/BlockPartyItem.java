package de.twyco.stegisagt.Items.Minigames.menuItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlockPartyItem extends ItemStack {

    public BlockPartyItem(){
        super(Material.PAPER, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setCustomModelData(2);
        itemMeta.setDisplayName(ChatColor.GOLD + "BLockparty");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Die Spieler müssen auf der richtigen Farbe stehen");
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
