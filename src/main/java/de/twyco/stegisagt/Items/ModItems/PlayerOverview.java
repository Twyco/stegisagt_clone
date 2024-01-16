package de.twyco.stegisagt.Items.ModItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerOverview extends ItemStack {

    public PlayerOverview(Player p){
        super();
        this.setType(Material.PLAYER_HEAD);
        this.setAmount(1);
        SkullMeta itemMeta = (SkullMeta) this.getItemMeta();
        itemMeta.setOwningPlayer(p);
        itemMeta.setDisplayName(ChatColor.BLUE + "Spielerübersicht");
        this.setItemMeta(itemMeta);
    }

}
