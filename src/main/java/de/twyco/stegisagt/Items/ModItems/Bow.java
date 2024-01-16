package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Bow extends ItemStack {

    public Bow(){
        super();
        this.setType(Material.BOW);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        if(itemMeta == null){
            return;
        }
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.GOLD + "One Hit Bow");
        this.setItemMeta(itemMeta);
    }

}
