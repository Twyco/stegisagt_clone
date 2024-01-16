package de.twyco.stegisagt.Listener.CustomItems.ModItems;

import de.twyco.stegisagt.Inventorys.PlayerOverview.AlivePlayerInventory;
import de.twyco.stegisagt.Inventorys.PlayerOverview.DeadPlayerInventory;
import de.twyco.stegisagt.Inventorys.PlayerOverview.OverviewMenu;
import de.twyco.stegisagt.Inventorys.PlayerOverview.PlayerOverviewInventory;
import de.twyco.stegisagt.Items.InventoryItems.PlayerOverview.*;
import de.twyco.stegisagt.Items.InventoryItems.generell.*;
import de.twyco.stegisagt.Items.ModItems.PlayerOverview;
import de.twyco.stegisagt.Miigames.CoinFlip;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerOverviewListener implements Listener {

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId())) {
            if (!player.getItemInHand().getType().equals(new PlayerOverview(player).getType())) {
                return;
            }
            if (!player.getItemInHand().getItemMeta().equals(new PlayerOverview(player).getItemMeta())) {
                return;
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) {
            return;
        }
        if (!e.getItem().getType().equals(new PlayerOverview(p).getType())) {
            return;
        }
        if (!e.getItem().getItemMeta().equals(new PlayerOverview(p).getItemMeta())) {
            return;
        }
        p.openInventory(new OverviewMenu().getInventory());
        p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getView().getTitle().equals(new OverviewMenu().getTitle())) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getType().equals(new AlivePlayer().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new AlivePlayer().getItemMeta())) {
                    e.getWhoClicked().openInventory(new AlivePlayerInventory().getInventory(0));
                    p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                }
            } else if (e.getCurrentItem().getType().equals(new DeadPlayer().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new DeadPlayer().getItemMeta())) {
                    e.getWhoClicked().openInventory(new DeadPlayerInventory().getInventory(0));
                    p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                }
            } else if (e.getCurrentItem().getType().equals(new SortByItem().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new SortByItem().getItemMeta())) {
                    DeadPlayerInventory.setAlphabetSort(!DeadPlayerInventory.isAlphabetSort());
                    e.getWhoClicked().openInventory(new OverviewMenu().getInventory());
                    p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                }
            }
        } else if (e.getView().getTitle().equals(new AlivePlayerInventory().getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new NextSite(0).getItemMeta().getDisplayName())) {
                    return;
                }
                int newSite = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0));
                e.getWhoClicked().openInventory(new AlivePlayerInventory().getInventory(newSite - 1));
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new PreviousSite(0).getItemMeta().getDisplayName())) {
                    return;
                }
                int newSite = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0));
                e.getWhoClicked().openInventory(new AlivePlayerInventory().getInventory(newSite - 1));
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().substring(2));
                e.getWhoClicked().openInventory(new PlayerOverviewInventory(p, false, 1).getInventory());//TODO
                Player p2 = (Player) e.getWhoClicked();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            }
        } else if (e.getView().getTitle().equals(new DeadPlayerInventory().getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new NextSite(0).getItemMeta().getDisplayName())) {
                    return;
                }
                int newSite = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0));
                e.getWhoClicked().openInventory(new DeadPlayerInventory().getInventory(newSite - 1));
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new PreviousSite(0).getItemMeta().getDisplayName())) {
                    return;
                }
                int newSite = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0));
                e.getWhoClicked().openInventory(new DeadPlayerInventory().getInventory(newSite - 1));
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().substring(2));
                e.getWhoClicked().openInventory(new PlayerOverviewInventory(p, true,1).getInventory()); //TODO
                Player p2 = (Player) e.getWhoClicked();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            }
        } else if (e.getView().getTitle().equals(new PlayerOverviewInventory((Player) e.getWhoClicked(), false, 0).getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.ENDER_PEARL)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new Enderpearl("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                Player p2 = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                p.teleport(p2);
            } else if (e.getCurrentItem().getType().equals(Material.CHORUS_FRUIT)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new ChorusFruit("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                Player p2 = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                p2.teleport(p);
            } else if (e.getCurrentItem().getType().equals(Material.BLAZE_ROD)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new BlazeRod("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p2 = (Player) e.getWhoClicked();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                Stegisagt.revivePlayer(p, p2.getLocation());
            } else if (e.getCurrentItem().getType().equals(Material.SKELETON_SKULL)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new SkeletenSkull("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p2 = (Player) e.getWhoClicked();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                Stegisagt.killPlayer(p);
            } else if (e.getCurrentItem().getType().equals(Material.BUCKET)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new Bucket("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                p.getInventory().clear();
                Player p2 = (Player) e.getWhoClicked();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.LIGHT)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new CoinItem("").getItemMeta().getDisplayName())) {
                    return;
                }
                Player p = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0));
                Player p2 = (Player) e.getWhoClicked();
                CoinFlip.flipCoin(p, p2);
                p2.closeInventory();
                p2.playSound(p2, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equals(new PreviousSite(0, false).getItemMeta().getDisplayName())) {
                    return;
                }
                int newSite = Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(1));
                String deadOrAlive = e.getCurrentItem().getItemMeta().getLore().get(0);
                if(deadOrAlive.equals(PreviousSite.getToteLore())){
                    e.getWhoClicked().openInventory(new DeadPlayerInventory().getInventory(newSite - 1));
                }else {
                    e.getWhoClicked().openInventory(new AlivePlayerInventory().getInventory(newSite - 1));
                }
                Player p = (Player) e.getWhoClicked();
                p.playSound(p, Sound.UI_BUTTON_CLICK,1f, 1.25f);
            }
        }

    }

}
