package de.twyco.stegisagt.Listener.CustomItems.Settings;

import de.twyco.stegisagt.Inventorys.Settings.GiveItemInventory;
import de.twyco.stegisagt.Inventorys.Settings.SettingsInventory;
import de.twyco.stegisagt.Inventorys.Settings.SettingsMenuInventory;
import de.twyco.stegisagt.Inventorys.Settings.ToolsInventory;
import de.twyco.stegisagt.Items.InventoryItems.Settings.*;
import de.twyco.stegisagt.Items.InventoryItems.Tools.*;
import de.twyco.stegisagt.Items.ModItems.Settigns;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SettingsMenuListener implements Listener {

    private final Stegisagt instance;

    public SettingsMenuListener() {
        instance = Stegisagt.getInstance();
    }


    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId())) {
            if (!player.getItemInHand().getType().equals(new Settigns().getType())) {
                return;
            }
            if (!player.getItemInHand().getItemMeta().equals(new Settigns().getItemMeta())) {
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
        if (!e.getItem().getType().equals(new Settigns().getType())) {
            return;
        }
        if (!e.getItem().getItemMeta().equals(new Settigns().getItemMeta())) {
            return;
        }
        p.openInventory(new SettingsMenuInventory().getInventory());
        p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
    }


    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getView().getTitle().equals(new SettingsMenuInventory().getTitle())) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getType().equals(new DiamondPickaxe().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new DiamondPickaxe().getItemMeta())) {
                    e.getWhoClicked().openInventory(new ToolsInventory().getInventory());
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                }
            } else if (e.getCurrentItem().getType().equals(new CommandBlock().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new CommandBlock().getItemMeta())) {
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                }
            } else if (e.getCurrentItem().getType().equals(new Visibility().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new Visibility().getItemMeta())) {
                    Stegisagt.getInstance().setPlayerVisibility(!Stegisagt.getInstance().isPlayerVisibility());
                    e.getWhoClicked().openInventory(new SettingsMenuInventory().getInventory());
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                }
            }
        } else if (e.getView().getTitle().equals(new ToolsInventory().getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(new IronTools().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new IronTools().getItemMeta())) {
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    if (e.getClick().equals(ClickType.LEFT)) {
                        for (UUID uuid : Stegisagt.getAlivePlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            giveIronTools(player);
                        }
                    } else if (e.getClick().equals(ClickType.RIGHT)) {
                        for (UUID uuid : Stegisagt.getDeadPlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            giveIronTools(player);
                        }
                    }
                }
            } else if (e.getCurrentItem().getType().equals(new BowTool().getType())) {
                if (e.getCurrentItem().getItemMeta().
                        equals(new BowTool().getItemMeta())) {
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    if (e.getClick().equals(ClickType.LEFT)) {
                        for (UUID uuid : Stegisagt.getAlivePlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().addItem(new ItemStack(Material.BOW, 1));
                        }
                    } else if (e.getClick().equals(ClickType.RIGHT)) {
                        for (UUID uuid : Stegisagt.getDeadPlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().addItem(new ItemStack(Material.BOW, 1));
                        }
                    }
                }
            } else if (e.getCurrentItem().getType().equals(new ArrowTool().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new ArrowTool().getItemMeta())) {
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    if (e.getClick().equals(ClickType.LEFT)) {
                        for (UUID uuid : Stegisagt.getAlivePlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
                        }
                    } else if (e.getClick().equals(ClickType.RIGHT)) {
                        for (UUID uuid : Stegisagt.getDeadPlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
                        }
                    }
                }
            } else if (e.getCurrentItem().getType().equals(new InvClear().getType())) {
                if (e.getCurrentItem().getItemMeta().equals(new InvClear().getItemMeta())) {
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    if (e.getClick().equals(ClickType.LEFT)) {
                        for (UUID uuid : Stegisagt.getAlivePlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().clear();
                        }
                    } else if (e.getClick().equals(ClickType.RIGHT)) {
                        for (UUID uuid : Stegisagt.getDeadPlayer()) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.getInventory().clear();
                        }
                    }
                }
            } else if (e.getCurrentItem().getType().equals(Material.CHEST)) {
                if (e.getCurrentItem().getItemMeta().equals(new AliveChest().getItemMeta())) {
                    e.getWhoClicked().openInventory(new GiveItemInventory().getAliveInventory());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                } else if (e.getCurrentItem().getItemMeta().equals(new DeadChest().getItemMeta())) {
                    e.getWhoClicked().openInventory(new GiveItemInventory().getDeadInventory());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                }
            }

        } else if (e.getView().getTitle().equals(new SettingsInventory().getTitle())) {
            e.setCancelled(true);
            if (!e.getCurrentItem().getType().equals(new On().getType())
                    && !e.getCurrentItem().getType().equals(new Off().getType())
                    && !e.getCurrentItem().getType().equals(new AttackSpeed1_19().getType())
                    && !e.getCurrentItem().getType().equals(new Attackspeed1_8().getType())) {
                return;
            }
            if (!e.getCurrentItem().getItemMeta().equals(new On().getItemMeta())
                    && !e.getCurrentItem().getItemMeta().equals(new Off().getItemMeta())
                    && !e.getCurrentItem().getItemMeta().equals(new AttackSpeed1_19().getItemMeta())
                    && !e.getCurrentItem().getItemMeta().equals(new Attackspeed1_8().getItemMeta())) {
                return;
            }
            if (e.getCurrentItem().getType().equals(new On().getType())
                    || e.getCurrentItem().getType().equals(new Off().getType())) {
                if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new PvP().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new PvP().getItemMeta())) {
                        return;
                    }
                    instance.setPvp(!instance.isPvp());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new FallDamage().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new FallDamage().getItemMeta())) {
                        return;
                    }
                    instance.setFallDamage(!instance.isFallDamage());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new Food().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new Food().getItemMeta())) {
                        return;
                    }
                    instance.setHunger(!instance.isHunger());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new BuildPlace().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new BuildPlace().getItemMeta())) {
                        return;
                    }
                    instance.setBuildPlace(!instance.isBuildPlace());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new BuildBreak().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new BuildBreak().getItemMeta())) {
                        return;
                    }
                    instance.setBuildBreak(!instance.isBuildBreak());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new BlockDrop().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new BlockDrop().getItemMeta())) {
                        return;
                    }
                    instance.setBlockDrop(!instance.isBlockDrop());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new EntityDrop().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new EntityDrop().getItemMeta())) {
                        return;
                    }
                    instance.setEntityDrop(!instance.isEntityDrop());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                } else if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new Collide().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new Collide().getItemMeta())) {
                        return;
                    }
                    instance.setPlayerCollision(!instance.isPlayerCollision());
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                }
            } else {
                if (e.getInventory().getItem(e.getSlot() - 9).getType().equals(new AttackSpeed().getType())) {
                    if (!e.getInventory().getItem(e.getSlot() - 9).getItemMeta().equals(new AttackSpeed().getItemMeta())) {
                        return;
                    }
                    if (instance.getAttackSpeed() == 4) {
                        instance.setAttackSpeed(100);
                    }else {
                        instance.setAttackSpeed(4);
                    }
                    Player p = (Player) e.getWhoClicked();
                    p.playSound(p, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
                    e.getWhoClicked().openInventory(new SettingsInventory().getInventory());
                }
            }
        }
    }

    private void giveIronTools(final Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_SHOVEL));
        player.getInventory().addItem(new ItemStack(Material.IRON_HOE));
        player.getInventory().addItem(new ItemStack(Material.SHEARS));
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        if (e.getView().getTitle().equals(new GiveItemInventory().getAliveTitle())) {
            for (int i = 0; i < e.getInventory().getSize(); i++) {
                ItemStack im = e.getInventory().getItem(i);
                if (im != null) {
                    for (UUID uuid : Stegisagt.getAlivePlayer()) {
                        Player player = Bukkit.getPlayer(uuid);
                        player.getInventory().addItem(im);
                    }
                }
            }
        } else if (e.getView().getTitle().equals(new GiveItemInventory().getDeadTitle())) {
            for (int i = 0; i < e.getInventory().getSize(); i++) {
                ItemStack im = e.getInventory().getItem(i);
                if (im != null) {
                    for (UUID uuid : Stegisagt.getDeadPlayer()) {
                        Player player = Bukkit.getPlayer(uuid);
                        player.getInventory().addItem(im);
                    }
                }
            }
        }
    }


}
