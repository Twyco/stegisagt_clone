package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Coin extends ItemStack {

    public Coin(boolean head) {
        super(Material.LIGHT, 1);
        ItemMeta itemMeta = getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setDisplayName(ChatColor.GOLD + (head ? "Kopf" : "Zahl"));
        BlockData data = Material.LIGHT.createBlockData();
        ((Light) data).setLevel(head ? 1 : 0);
        ((BlockDataMeta) itemMeta).setBlockData(data);
        setItemMeta(itemMeta);
    }

}
