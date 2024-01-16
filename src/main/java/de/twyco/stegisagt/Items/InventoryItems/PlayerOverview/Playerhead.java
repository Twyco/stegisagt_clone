package de.twyco.stegisagt.Items.InventoryItems.PlayerOverview;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Playerhead extends ItemStack {

    public Playerhead(Player owner, ChatColor nameColor) {
        super();
        this.setType(Material.PLAYER_HEAD);
        this.setAmount(1);
        SkullMeta itemMeta = (SkullMeta) this.getItemMeta();
        itemMeta.setOwningPlayer(owner);
        itemMeta.setDisplayName(nameColor + owner.getDisplayName());
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Revivecount: " + Stegisagt.getPlayerReviveCount(owner.getUniqueId()));
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
