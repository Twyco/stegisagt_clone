package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SelectedPlayer extends ItemStack {

    public SelectedPlayer() {
        super(Material.END_PORTAL_FRAME, 1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Ausgewählte Spieler");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Wähle aus, ob die Lebenden, Toten");
        lore.add("oder Ausgewählten Spieler das Spiel spielen");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
