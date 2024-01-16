package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CoinItem extends ItemStack {

    public CoinItem(String displayName) {
        super(Material.LIGHT, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setDisplayName(ChatColor.GOLD + "Coinflip");
        BlockData data = Material.LIGHT.createBlockData();
        ((Light) data).setLevel(1);
        ((BlockDataMeta) itemMeta).setBlockData(data);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(displayName);
        itemMeta.setLore(lore);
        setItemMeta(itemMeta);
    }

}
