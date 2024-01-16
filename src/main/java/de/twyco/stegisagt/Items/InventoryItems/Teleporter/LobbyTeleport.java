package de.twyco.stegisagt.Items.InventoryItems.Teleporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyTeleport extends ItemStack {

    public LobbyTeleport() {
        super(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta itemMeta = getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.setDisplayName(ChatColor.GRAY + "zur Lobby");
        setItemMeta(itemMeta);
    }

}
