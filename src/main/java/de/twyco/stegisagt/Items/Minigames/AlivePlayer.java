package de.twyco.stegisagt.Items.Minigames;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AlivePlayer extends ItemStack {

    public AlivePlayer() {
        super(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Lebende Spieler");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Wähle aus, ob die Lebenden, Toten");
        lore.add("oder Ausgewählten Spieler das Spiel spielen");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
