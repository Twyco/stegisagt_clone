package de.twyco.stegisagt.Inventorys.PlayerOverview;

import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.*;
import de.twyco.stegisagt.Items.InventoryItems.generell.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerOverviewInventory {

    private final Inventory inventory;
    private final String title;

    public PlayerOverviewInventory(Player p, boolean dead, int site){
        title = ChatColor.YELLOW + "Spielerübersicht";
        this.inventory = Bukkit.createInventory(null, 3*9, title);
        inventory.setItem(9, new PreviousSite(site, dead));
        inventory.setItem(11, new Enderpearl(p.getDisplayName()));
        inventory.setItem(12, new ChorusFruit(p.getDisplayName()));
        inventory.setItem(13, new Playerhead(p, ChatColor.YELLOW));
        if(dead){
            inventory.setItem(14, new BlazeRod(p.getDisplayName()));
        }else {
            inventory.setItem(14, new SkeletenSkull(p.getDisplayName()));
        }
        inventory.setItem(15, new Bucket(p.getDisplayName()));
        inventory.setItem(17, new CoinItem(p.getDisplayName()));
    }



    public Inventory getInventory(){
        return inventory;
    }

    public String getTitle(){
        return this.title;
    }

}
