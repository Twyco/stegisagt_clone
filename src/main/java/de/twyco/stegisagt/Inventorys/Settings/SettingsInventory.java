package de.twyco.stegisagt.Inventorys.Settings;

import de.twyco.stegisagt.Items.InventoryItems.Settings.*;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class SettingsInventory {

    private final Inventory inventory;
    private final String title;
    private final Stegisagt instance;

    public SettingsInventory() {
        this.instance = Stegisagt.getInstance();
        title = ChatColor.GRAY + ChatColor.BOLD.toString() + "Settings";
        this.inventory = Bukkit.createInventory(null, 2 * 9, title);
        //PVP
        inventory.setItem(0, new PvP());
        if (instance.isPvp()) {
            inventory.setItem(9, new On());
        } else {
            inventory.setItem(9, new Off());
        }
        //Fallschaden
        inventory.setItem(1, new FallDamage());
        if (instance.isFallDamage()) {
            inventory.setItem(10, new On());
        } else {
            inventory.setItem(10, new Off());
        }
        //Hunger
        inventory.setItem(2, new Food());
        if (instance.isHunger()) {
            inventory.setItem(11, new On());
        } else {
            inventory.setItem(11, new Off());
        }
        //Bauen
        inventory.setItem(3, new BuildPlace());
        if (instance.isBuildPlace()) {
            inventory.setItem(12, new On());
        } else {
            inventory.setItem(12, new Off());
        }
        //Abbauen
        inventory.setItem(4, new BuildBreak());
        if (instance.isBuildBreak()) {
            inventory.setItem(13, new On());
        } else {
            inventory.setItem(13, new Off());
        }
        //BlockDrop
        inventory.setItem(5, new BlockDrop());
        if (instance.isBlockDrop()) {
            inventory.setItem(14, new On());
        } else {
            inventory.setItem(14, new Off());
        }
        //MobDrop
        inventory.setItem(6, new EntityDrop());
        if (instance.isEntityDrop()) {
            inventory.setItem(15, new On());
        } else {
            inventory.setItem(15, new Off());
        }
        //PlayerCollision
        inventory.setItem(7, new Collide());
        if (instance.isPlayerCollision()) {
            inventory.setItem(16, new On());
        } else {
            inventory.setItem(16, new Off());
        }
        //AttackSpeed
        inventory.setItem(8, new AttackSpeed());
        if (instance.getAttackSpeed() == 4) {
            inventory.setItem(17, new AttackSpeed1_19());
        }else {
            inventory.setItem(17, new Attackspeed1_8());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return this.title;
    }

}
