package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Sword extends ItemStack {

    public Sword(){
        super();
        this.setType(Material.DIAMOND_SWORD);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.GOLD + "One Hit Sword");

        this.setItemMeta(itemMeta);
    }

}
