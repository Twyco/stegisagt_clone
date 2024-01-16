package de.twyco.stegisagt.Listener.Selection;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Items.SelectionItems.CoinflipSelection;
import de.twyco.stegisagt.Miigames.CoinFlip;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.stegisagt.Util.Config;
import net.minecraft.world.level.block.BlockLever;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class CoinFlipListener implements Listener {

    public Stegisagt instance;

    public CoinFlipListener() {
        instance = Stegisagt.getInstance();
    }


    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (Stegisagt.getGameStatus().equals(GameStatus.CLOSED)) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null) {
                return;
            }
            if (!itemStack.getType().equals(new CoinflipSelection().getType()) ||
                    !itemMeta.equals(new CoinflipSelection().getItemMeta())) {
                return;
            }

            Config config = instance.getLocationsConfig();
            if (event.getRightClicked().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
                config.getFileConfiguration().set("Coinflip.ItemFrame", event.getRightClicked().getLocation());
                config.save();
                event.setCancelled(true);
            }
        }else {
            if (event.getRightClicked().getType().equals(EntityType.GLOW_ITEM_FRAME)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (Stegisagt.getGameStatus().equals(GameStatus.CLOSED)) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null) {
                return;
            }
            if (!itemStack.getType().equals(new CoinflipSelection().getType()) ||
                    !itemMeta.equals(new CoinflipSelection().getItemMeta())) {
                return;
            }
            if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                return;
            }
            Config config = instance.getLocationsConfig();
            if (event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
                config.getFileConfiguration().set("Coinflip.Button", event.getClickedBlock().getLocation());
                config.save();
                event.setCancelled(true);
                return;
            } else if (event.getClickedBlock().getType().equals(Material.LEVER)) {
                config.getFileConfiguration().set("Coinflip.Lever", event.getClickedBlock().getLocation());
                config.save();
                event.setCancelled(true);
                return;
            } else if (event.getClickedBlock().getBlockData() instanceof TrapDoor) {
                config.getFileConfiguration().set("Coinflip.TrapDoor", event.getClickedBlock().getLocation());
                config.save();
                event.setCancelled(true);
                return;
            }
        } else {
            Config config = instance.getLocationsConfig();
            if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                return;
            }
            if (event.getClickedBlock().getBlockData() instanceof TrapDoor) {
                Location location = config.getFileConfiguration().getLocation("Coinflip.TrapDoor");
                if (event.getClickedBlock().getLocation().equals(location)) {
                    event.setCancelled(true);
                }
            } else if (event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
                Location location = config.getFileConfiguration().getLocation("Coinflip.Button");
                if (event.getClickedBlock().getLocation().equals(location)) {
                    CoinFlip.setItemFrame(!CoinFlip.isAutomat());
                    CoinFlip.setAutomat(!CoinFlip.isAutomat());
                    event.setCancelled(true);
                }
            } else if (event.getClickedBlock().getType().equals(Material.LEVER)) {
                Location location = config.getFileConfiguration().getLocation("Coinflip.Lever");
                if (event.getClickedBlock().getLocation().equals(location)) {
                    BlockData blockData = event.getClickedBlock().getBlockData();
                    Powerable powerable = (Powerable) blockData;
                    if (!powerable.isPowered()) {
                        if(CoinFlip.isSpinning()){
                            event.setUseInteractedBlock(Event.Result.DENY);
                            return;
                        }
                        ArrayList<Player> gamba = new ArrayList<>();
                        for(UUID uuid : Stegisagt.getModPlayer()){
                            gamba.add(Bukkit.getPlayer(uuid));
                        }
                        gamba.add(event.getPlayer());
                        CoinFlip.flipCoin(gamba);
                        Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
                            @Override
                            public void run() {
                                powerable.setPowered(false);
                                event.getClickedBlock().setBlockData(powerable);
                            }
                        }, 10L);
                    }else {
                        event.setUseInteractedBlock(Event.Result.DENY);
                    }
                }
            }
        }
    }

}
