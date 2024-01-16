package de.twyco.stegisagt.Inventorys.PlayerOverview;

import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.Playerhead;
import de.twyco.stegisagt.Items.InventoryItems.generell.NextSite;
import de.twyco.stegisagt.Items.InventoryItems.generell.*;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class DeadPlayerInventory {

    private final Inventory[] inventory;
    private final String title;
    private static boolean alphabetSort = true;

    public DeadPlayerInventory(){
        this.title = ChatColor.DARK_RED + "Tote Spieler Übersicht";
        int inventorySites = 1;
        int deadPlayer = Stegisagt.getDeadPlayerCount();
        if(deadPlayer > 36){
            deadPlayer -=36;
            while (deadPlayer > 0){
                deadPlayer -=36;
                inventorySites++;
            }
        }
        inventory = new Inventory[inventorySites];
        ArrayList<Player> players = new ArrayList<>();
        for (UUID uuid : Stegisagt.getDeadPlayer()){
            players.add(Bukkit.getPlayer(uuid));
        }

        if(alphabetSort){
            if(players.size() > 0){players.sort(Comparator.comparing(o -> o.getName().toLowerCase()));            }
        }else {
            if(players.size() > 0) {Collections.reverse(players);}
        }
        for(int i = 0; i < inventorySites; i++){
            inventory[i] = Bukkit.createInventory(null, 5*9, title);
            if(i == 0){
                if(inventorySites > 1){
                    inventory[i].setItem(44, new NextSite(i + 2));
                }
            }else {
                inventory[i].setItem(36, new PreviousSite(i));
                if(i != inventorySites - 1){
                    inventory[i].setItem(44, new NextSite(i + 2));
                }
            }
            for(int j = 0; j < 36; j++){
                if(((j + 1) + (i * 36)) <= Stegisagt.getDeadPlayerCount()){
                    if(players.get(j + (i * 36)) == null){

                    }
                    inventory[i].setItem(j, new Playerhead(players.get(j + (i * 36)), ChatColor.RED));
                }
            }
        }


    }

    public Inventory getInventory(int index) {
        return this.inventory[index];
    }

    public String getTitle() {
        return this.title;
    }

    public static boolean isAlphabetSort() {
        return alphabetSort;
    }

    public static void setAlphabetSort(boolean alphabetSort) {
        DeadPlayerInventory.alphabetSort = alphabetSort;
    }
}
