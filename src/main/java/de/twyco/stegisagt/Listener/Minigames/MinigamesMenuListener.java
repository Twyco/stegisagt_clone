package de.twyco.stegisagt.Listener.Minigames;

import com.google.common.reflect.ClassPath;
import de.twyco.ampelrennen.Ampelrennen;
import de.twyco.blockparty.Blockparty;
import de.twyco.hidenseek.Hide_n_Seek;
import de.twyco.mlg.MLG;
import de.twyco.molerace.Molerace;
import de.twyco.reihenfolge.Reihenfolge;
import de.twyco.schatzsuche.Schatzsuche;
import de.twyco.snowfall.SnowFall;
import de.twyco.spleef.Spleef;
import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Inventorys.Minigames.*;
import de.twyco.stegisagt.Items.InventoryItems.generell.Back;
import de.twyco.stegisagt.Items.Minigames.*;
import de.twyco.stegisagt.Items.Minigames.ExtraSettings.SchatzsucheExtraSetting;
import de.twyco.stegisagt.Items.Minigames.menuItems.*;
import de.twyco.stegisagt.Items.ModItems.MinigamesSelector;
import de.twyco.stegisagt.MinigamePlayers;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.trueorfalse.TrueOrFalse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class MinigamesMenuListener implements Listener {

    private int ssPlayerCount;
    private int ssChestCount;
    private MinigamePlayers ssPlayer;

    private int arPlayerCount;
    private MinigamePlayers arPlayer;

    private MinigamePlayers tofPlayer;

    private MinigamePlayers hidnseekPlayer;

    private MinigamePlayers mlgPlayer;

    private MinigamePlayers sfPlayer;

    private MinigamePlayers bpPlayer;

    private MinigamePlayers rhPlayer;

    private int mrPlayerCount;
    private MinigamePlayers mrPlayer;

    private int spPlayerCount;
    private MinigamePlayers spPlayer;

    public MinigamesMenuListener() {
        ssPlayerCount = 1;
        ssChestCount = 1;
        ssPlayer = MinigamePlayers.ALIVE;

        arPlayerCount = 1;
        arPlayer = MinigamePlayers.ALIVE;

        tofPlayer = MinigamePlayers.ALIVE;

        hidnseekPlayer = MinigamePlayers.ALIVE;

        mlgPlayer = MinigamePlayers.ALIVE;

        sfPlayer = MinigamePlayers.ALIVE;

        mrPlayer = MinigamePlayers.ALIVE;
        mrPlayerCount = 1;

        bpPlayer = MinigamePlayers.ALIVE;

        spPlayerCount = 1;
        spPlayer = MinigamePlayers.ALIVE;

        rhPlayer = MinigamePlayers.ALIVE;
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (hasMinigamesSelectorInHand(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (hasMinigamesSelectorInHand(player)) {
            //if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
            player.openInventory(new MinigamesMenuInventory().getInventory());
            player.playSound(player, Sound.UI_BUTTON_CLICK, 5f, 1.25f);
            //} else {
            //    player.sendMessage(ChatColor.RED + "Du kannst dieses Item nur verwenden, wenn du kein anderes Minigames spielst!");
            //}
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        if (event.getView().getTitle().equals(new MinigamesMenuInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 2f, 1.25f);

            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta == null) {
                return;
            }

            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new SchatzsucheItem().getType()) && itemMeta.equals(new SchatzsucheItem().getItemMeta())) { //Schatzsuche
                    ssPlayerCount = 1;
                    ssChestCount = 1;
                    ssPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new SchatzsucheInventory().getInventory(ssPlayerCount, ssChestCount, ssPlayer));
                } else if (itemStack.getType().equals(new AmpelrennenItem().getType()) && itemMeta.equals(new AmpelrennenItem().getItemMeta())) { //Ampelrennen
                    arPlayerCount = 1;
                    arPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new AmpelrennenInventory().getInventory(arPlayerCount, arPlayer));
                } else if (itemStack.getType().equals(new TrueOrFalseItem().getType()) && itemMeta.equals(new TrueOrFalseItem().getItemMeta())) { //True Or False
                    tofPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new TrueOrFalseInventory().getInventory(tofPlayer));
                } else if (itemStack.getType().equals(new HidenSeekItem().getType()) && itemMeta.equals(new HidenSeekItem().getItemMeta())) { //Hide n Seek
                    hidnseekPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new HidenSeekInventory().getInventory(hidnseekPlayer));
                } else if (itemStack.getType().equals(new MLGItem().getType()) && itemMeta.equals(new MLGItem().getItemMeta())) { //MLG
                    mlgPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new MLGInventory().getInventory(mlgPlayer));
                } else if (itemStack.getType().equals(new SnowFallItem().getType()) && itemMeta.equals(new SnowFallItem().getItemMeta())) { //SnowFall
                    sfPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new SnowFallInventory().getInventory(sfPlayer));
                } else if (itemStack.getType().equals(new MoleRaceItem().getType()) && itemMeta.equals(new MoleRaceItem().getItemMeta())) { //Molerace
                    mrPlayerCount = 1;
                    mrPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new MoleraceInventory().getInventory(mrPlayerCount, mrPlayer));
                } else if (itemStack.getType().equals(new BlockPartyItem().getType()) && itemMeta.equals(new BlockPartyItem().getItemMeta())) { //BlockParty
                    bpPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new BlockpartyInventory().getInventory(bpPlayer));
                } else if (itemStack.getType().equals(new SpleefItem().getType()) && itemMeta.equals(new SpleefItem().getItemMeta())) { //Spleef
                    spPlayerCount = 1;
                    spPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new SpleefInventory().getInventory(spPlayerCount, spPlayer));
                } else if (itemStack.getType().equals(new ReihenfolgeItem().getType()) && itemMeta.equals(new ReihenfolgeItem().getItemMeta())) { //Reihenfolge
                    rhPlayer = MinigamePlayers.ALIVE;
                    player.openInventory(new ReihenfolgeInventory().getInventory(rhPlayer));
                }
            }
        } else if (event.getView().getTitle().equals(new SchatzsucheInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 2f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta == null) {
                    return;
                }
                if (itemStack.getType().equals(new Back().getType())) {
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (ssPlayer) {
                            case DEAD ->
                                    Schatzsuche.startGame(getDeadPlayer(), getModPlayer(), ssChestCount, ssPlayerCount);
                            case ALIVE ->
                                    Schatzsuche.startGame(getAlivePlayer(), getModPlayer(), ssChestCount, ssPlayerCount);
                            case SELECTED -> {
                                Schatzsuche.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer(), ssChestCount, ssPlayerCount);
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) { //Lebende -> Tote  -> Selected -> Lebende
                        switch (ssPlayer) {
                            case DEAD -> ssPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> ssPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> ssPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new SchatzsucheInventory().getInventory(ssPlayerCount, ssChestCount, ssPlayer));
                    }
                } else if (itemStack.getType().equals(Material.STONE_BUTTON)) {
                    int delta = calDelta(event.getClick(), itemStack);
                    ItemStack clickSetting = event.getInventory().getItem((event.getSlot() >= 9) ? (event.getSlot() - 9) : (event.getSlot() + 9));
                    if (clickSetting == null) {
                        return;
                    }
                    if (clickSetting.getType().equals(new SpielerAnzahl().getType())) {
                        ssPlayerCount += delta;
                        ssPlayerCount = Math.max(ssPlayerCount, 1);
                        if (ssPlayerCount > ssChestCount) {
                            ssChestCount = ssPlayerCount;
                        }
                    } else if (clickSetting.getType().equals(new SchatzsucheExtraSetting().getType())) {
                        ssChestCount += delta;
                        ssChestCount = Math.max(ssChestCount, 1);
                        if (ssPlayerCount > ssChestCount) {
                            ssPlayerCount = ssChestCount;
                        }
                    }
                    player.openInventory(new SchatzsucheInventory().getInventory(ssPlayerCount, ssChestCount, ssPlayer));
                }
            }
        } else if (event.getView().getTitle().equals(new AmpelrennenInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (arPlayer) {
                            case DEAD -> Ampelrennen.startGame(getDeadPlayer(), getModPlayer(), arPlayerCount);
                            case ALIVE -> Ampelrennen.startGame(getAlivePlayer(), getModPlayer(), arPlayerCount);
                            case SELECTED -> {
                                Ampelrennen.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer(), arPlayerCount);
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (arPlayer) {
                            case DEAD -> arPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> arPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> arPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new AmpelrennenInventory().getInventory(arPlayerCount, arPlayer));
                    }
                } else if (itemStack.getType().equals(Material.STONE_BUTTON)) {
                    int delta = calDelta(event.getClick(), itemStack);
                    ItemStack clickSetting = event.getInventory().getItem((event.getSlot() >= 9) ? (event.getSlot() - 9) : (event.getSlot() + 9));
                    if (clickSetting == null) {
                        return;
                    }
                    if (clickSetting.getType().equals(new SpielerAnzahl().getType())) {
                        arPlayerCount += delta;
                        arPlayerCount = Math.max(arPlayerCount, 1);
                    }
                    player.openInventory(new AmpelrennenInventory().getInventory(arPlayerCount, arPlayer));
                }
            }
        } else if (event.getView().getTitle().equals(new TrueOrFalseInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (tofPlayer) {
                            case DEAD -> TrueOrFalse.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> TrueOrFalse.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                TrueOrFalse.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (tofPlayer) {
                            case DEAD -> tofPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> tofPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> tofPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new TrueOrFalseInventory().getInventory(tofPlayer));
                    }
                }
            }
        } else if (event.getView().getTitle().equals(new MLGInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (mlgPlayer) {
                            case DEAD -> MLG.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> MLG.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                MLG.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (mlgPlayer) {
                            case DEAD -> mlgPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> mlgPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> mlgPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new MLGInventory().getInventory(mlgPlayer));
                    }
                }
            }
        } else if (event.getView().getTitle().equals(new HidenSeekInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (hidnseekPlayer) {
                            case DEAD -> Hide_n_Seek.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> Hide_n_Seek.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                Hide_n_Seek.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (hidnseekPlayer) {
                            case DEAD -> hidnseekPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> hidnseekPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> hidnseekPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new HidenSeekInventory().getInventory(hidnseekPlayer));
                    }
                }
            }
        } else if (event.getView().getTitle().equals(new SnowFallInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (sfPlayer) {
                            case DEAD -> SnowFall.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> SnowFall.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                SnowFall.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (sfPlayer) {
                            case DEAD -> sfPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> sfPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> sfPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new SnowFallInventory().getInventory(sfPlayer));
                    }
                }
            }
        } else if (event.getView().getTitle().equals(new MoleraceInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (mrPlayer) {
                            case DEAD -> {
                                if (Stegisagt.getDeadPlayerCount() > 30) {
                                    player.sendMessage(ChatColor.RED + "Du kannst dieses Spiel nur mit maximal 30. Spielern starten");
                                } else {
                                    Molerace.startGame(getDeadPlayer(), getModPlayer(), mrPlayerCount);
                                }
                            }
                            case ALIVE -> {
                                if (Stegisagt.getAlivePlayerCount() > 30) {
                                    player.sendMessage(ChatColor.RED + "Du kannst dieses Spiel nur mit maximal 30. Spielern starten");
                                } else {
                                    Molerace.startGame(getAlivePlayer(), getModPlayer(), mrPlayerCount);
                                }
                            }
                            case SELECTED -> {
                                if (Stegisagt.getInstance().getSelectedPlayer().size() > 30) {
                                    player.sendMessage(ChatColor.RED + "Du kannst dieses Spiel nur mit maximal 30. Spielern starten");
                                } else {
                                    Molerace.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer(), mrPlayerCount);
                                    Stegisagt.getInstance().resetSelectedPlayer();
                                }
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (mrPlayer) {
                            case DEAD -> {
                                mrPlayer = MinigamePlayers.SELECTED;
                            }
                            case ALIVE -> mrPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> mrPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new MoleraceInventory().getInventory(mrPlayerCount, mrPlayer));
                    }
                } else if (itemStack.getType().equals(Material.STONE_BUTTON)) {
                    int delta = calDelta(event.getClick(), itemStack);
                    ItemStack clickSetting = event.getInventory().getItem((event.getSlot() >= 9) ? (event.getSlot() - 9) : (event.getSlot() + 9));
                    if (clickSetting == null) {
                        return;
                    }
                    if (clickSetting.getType().equals(new SpielerAnzahl().getType())) {
                        mrPlayerCount += delta;
                        mrPlayerCount = Math.max(mrPlayerCount, 1);
                        mrPlayerCount = Math.min(mrPlayerCount, 30);
                    }
                    player.openInventory(new MoleraceInventory().getInventory(mrPlayerCount, mrPlayer));
                }
            }
        } else if (event.getView().getTitle().equals(new BlockpartyInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (bpPlayer) {
                            case DEAD -> Blockparty.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> Blockparty.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                Blockparty.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (bpPlayer) {
                            case DEAD -> bpPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> bpPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> bpPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new BlockpartyInventory().getInventory(bpPlayer));
                    }
                }
            }
        } else if (event.getView().getTitle().equals(new SpleefInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (spPlayer) {
                            case DEAD -> Spleef.startGame(getDeadPlayer(), getModPlayer(), spPlayerCount);
                            case ALIVE -> Spleef.startGame(getAlivePlayer(), getModPlayer(), spPlayerCount);
                            case SELECTED -> {
                                Spleef.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer(), spPlayerCount);
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (spPlayer) {
                            case DEAD -> spPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> spPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> spPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new SpleefInventory().getInventory(spPlayerCount, spPlayer));
                    }
                } else if (itemStack.getType().equals(Material.STONE_BUTTON)) {
                    int delta = calDelta(event.getClick(), itemStack);
                    ItemStack clickSetting = event.getInventory().getItem((event.getSlot() >= 9) ? (event.getSlot() - 9) : (event.getSlot() + 9));
                    if (clickSetting == null) {
                        return;
                    }
                    if (clickSetting.getType().equals(new SpielerAnzahl().getType())) {
                        spPlayerCount += delta;
                        spPlayerCount = Math.max(spPlayerCount, 1);
                    }
                    player.openInventory(new SpleefInventory().getInventory(spPlayerCount, arPlayer));
                }
            }
        } else if (event.getView().getTitle().equals(new ReihenfolgeInventory().getTitle())) {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1f, 1.25f);
            if (Stegisagt.getGameStatus().equals(GameStatus.PLAYING)) {
                if (itemStack.getType().equals(new Back().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Back().getItemMeta())) { //Zurück
                        player.openInventory(new MinigamesMenuInventory().getInventory());
                    }
                } else if (itemStack.getType().equals(new Start().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new Start().getItemMeta())) { //Start
                        switch (rhPlayer) {
                            case DEAD -> Reihenfolge.startGame(getDeadPlayer(), getModPlayer());
                            case ALIVE -> Reihenfolge.startGame(getAlivePlayer(), getModPlayer());
                            case SELECTED -> {
                                Reihenfolge.startGame(Stegisagt.getInstance().getSelectedPlayer(), getModPlayer());
                                Stegisagt.getInstance().resetSelectedPlayer();
                            }
                        }
                    }
                } else if (itemStack.getType().equals(new AlivePlayer().getType()) || itemStack.getType().equals(new DeadPlayer().getType()) || itemStack.getType().equals(new SelectedPlayer().getType())) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) {
                        return;
                    }
                    if (itemMeta.equals(new AlivePlayer().getItemMeta()) || itemMeta.equals(new DeadPlayer().getItemMeta()) || itemMeta.equals(new SelectedPlayer().getItemMeta())) {//Lebende -> Tote  -> Selected -> Lebende
                        switch (rhPlayer) {
                            case DEAD -> rhPlayer = MinigamePlayers.SELECTED;
                            case ALIVE -> rhPlayer = MinigamePlayers.DEAD;
                            case SELECTED -> rhPlayer = MinigamePlayers.ALIVE;
                        }
                        player.openInventory(new ReihenfolgeInventory().getInventory(rhPlayer));
                    }
                }
            }
        }
    }

    private int calDelta(ClickType clickType, ItemStack itemStack) {
        int delta = 0;
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return delta;
        }
        int n;
        try {
            n = Integer.parseInt(itemMeta.getDisplayName().substring(2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return delta;
        }
        if (clickType.isShiftClick()) {
            if (itemMeta.equals(new SettingButtonUp(n).getItemMeta())) { //UP
                delta = 5;
            } else if (itemMeta.equals(new SettingButtonDown(n).getItemMeta())) { //Down
                delta = -5;
            }
        } else {
            if (itemMeta.equals(new SettingButtonUp(n).getItemMeta())) { //UP
                delta = 1;
            } else if (itemMeta.equals(new SettingButtonDown(n).getItemMeta())) { //Down
                delta = -1;
            }
        }
        return delta;
    }

    private boolean hasMinigamesSelectorInHand(Player player) {
        if (!Stegisagt.isMod(player.getUniqueId())) {
            return false;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!itemStack.getType().equals(new MinigamesSelector().getType())) {
            return false;
        }
        if (itemStack.getItemMeta() == null) {
            return false;
        }
        return itemStack.getItemMeta().equals(new MinigamesSelector().getItemMeta());
    }

    private ArrayList<Player> getDeadPlayer() {
        ArrayList<Player> deadPlayer = new ArrayList<>();
        for (UUID uuid : Stegisagt.getDeadPlayer()) {
            deadPlayer.add(Bukkit.getPlayer(uuid));
        }
        return deadPlayer;
    }

    private ArrayList<Player> getAlivePlayer() {
        ArrayList<Player> alivePlayer = new ArrayList<>();
        for (UUID uuid : Stegisagt.getAlivePlayer()) {
            alivePlayer.add(Bukkit.getPlayer(uuid));
        }
        return alivePlayer;
    }

    private ArrayList<Player> getModPlayer() {
        ArrayList<Player> modPlayer = new ArrayList<>();
        for (UUID uuid : Stegisagt.getModPlayer()) {
            modPlayer.add(Bukkit.getPlayer(uuid));
        }
        return modPlayer;

    }

}
