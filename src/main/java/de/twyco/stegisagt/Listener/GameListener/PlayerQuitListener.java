package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.ampelrennen.Ampelrennen;
import de.twyco.blockparty.Blockparty;
import de.twyco.hidenseek.Hide_n_Seek;
import de.twyco.mlg.MLG;
import de.twyco.molerace.Molerace;
import de.twyco.reihenfolge.Reihenfolge;
import de.twyco.schatzsuche.Schatzsuche;
import de.twyco.snowfall.SnowFall;
import de.twyco.spleef.Spleef;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.trueorfalse.TrueOrFalse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Stegisagt instance;

    public PlayerQuitListener() {
        this.instance = Stegisagt.getInstance();
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        Player p = event.getPlayer();
        p.getInventory().clear();
        switch (Stegisagt.getGameStatus()) {
            case CLOSED -> {
                if (p.isOp()) {
                    event.setQuitMessage(ChatColor.RED + event.getPlayer().getName() + ChatColor.GRAY + " hat den Server verlassen.");
                }else {
                    event.setQuitMessage(ChatColor.GRAY + event.getPlayer().getName() + " hat den Server verlassen.");
                }
            }
            case WAITING_FOR_PLAYERS, PLAYING -> {
                leaveEvent(event);
            }
            case PLAYING_SCHATZSUCHE -> {
                Schatzsuche.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_AMPELRENNEN -> {
                Ampelrennen.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_TRUEORFALSE -> {
                TrueOrFalse.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_HIDE_N_SEEK -> {
                Hide_n_Seek.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_MLG -> {
                MLG.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_MOLERACE -> {
                Molerace.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_SNOWFALL -> {
                SnowFall.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_BLOCKPARTY -> {
                Blockparty.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_SPLEEF -> {
                Spleef.killPlayer(p);
                leaveEvent(event);
            }
            case PLAYING_REIHENFOLGE -> {
                Reihenfolge.killPlayer(p);
                leaveEvent(event);
            }

            //TODO MINIGAME
        }
    }

    private void leaveEvent(final PlayerQuitEvent event) {
        Player p = event.getPlayer();
        instance.leavePlayer(p);
        if (Stegisagt.isMod(p.getUniqueId())) {
            event.setQuitMessage(ChatColor.DARK_PURPLE + "Mod" + ChatColor.GRAY + " | " + p.getName() + " hat den Server verlassen.");
        } else if (Stegisagt.isAlive(p.getUniqueId())) {
            event.setQuitMessage(ChatColor.GRAY + p.getName() + " hat den Server verlassen.");
        } else {
            event.setQuitMessage(ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + p.getName() + " hat den Server verlassen.");
        }
    }

}
