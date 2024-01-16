package de.twyco.stegisagt.Listener.CustomItems.ModItems;

import de.twyco.stegisagt.Items.ModItems.Bow;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;

public class BowListener implements Listener {

    private final HashMap<Projectile, Boolean> projectileBooleanHashMap;

    public BowListener() {
        projectileBooleanHashMap = new HashMap<>();
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource projectileSource = projectile.getShooter();
        if (projectileSource instanceof Player) {
            Player shooter = ((Player) projectileSource);
            if (Stegisagt.isMod(shooter.getUniqueId())) {
                ItemStack itemStack = shooter.getInventory().getItemInMainHand();
                if (itemStack.getType().equals(new Bow().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta != null) {
                        if (itemMeta.equals(new Bow().getItemMeta())) {
                            projectileBooleanHashMap.put(projectile, true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getHitBlock() != null){
            //Player hitPlayer = (Player) event.getHitEntity();
            //if (!Stegisagt.isMod(hitPlayer.getUniqueId())) {
                Projectile projectile = event.getEntity();
                boolean kill = projectileBooleanHashMap.getOrDefault(projectile, false);
                if (kill) {
                    projectile.remove();
                }
            //}
        }
        if(event.getHitEntity() == null){
            return;
        }
        if (event.getHitEntity() instanceof Player) {
            Player hitPlayer = (Player) event.getHitEntity();
            if (!Stegisagt.isMod(hitPlayer.getUniqueId())) {
                Projectile projectile = event.getEntity();
                boolean kill = projectileBooleanHashMap.getOrDefault(projectile, false);
                if (kill) {
                    Stegisagt.killPlayer(hitPlayer);
                    projectile.remove();
                }
            }
        }
    }

}
