package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    private final Stegisagt instance;

    public PlayerLoginListener() {
        this.instance = Stegisagt.getInstance();
    }

    @EventHandler
    public void onPlayerLogin(final PlayerLoginEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            e.allow();
            return;
        }
        if (e.getResult().equals(PlayerLoginEvent.Result.KICK_BANNED)) {
            return;
        }
        switch (Stegisagt.getGameStatus()) {
            case CLOSED -> {
                if (!p.isOp()) {
                    PlayerLoginEvent.Result result = PlayerLoginEvent.Result.KICK_FULL;
                    e.disallow(result, ChatColor.RED + "Es gibt aktuell keine Runde!");
                }
            }
            case WAITING_FOR_PLAYERS -> {
                int curPlayer = Bukkit.getOnlinePlayers().size() - Stegisagt.getModPlayerCount();
                if (curPlayer < instance.getMaxPlayer()) {
                    e.allow();
                } else {
                    PlayerLoginEvent.Result result = PlayerLoginEvent.Result.KICK_FULL;
                    e.disallow(result, ChatColor.RED + "Die Runde ist leider voll!");
                }
            }
            default -> {
                if (instance.isLeaved(p.getUniqueId())) {
                    if(instance.isRejoin()){
                        e.allow();
                    }else {
                        PlayerLoginEvent.Result result = PlayerLoginEvent.Result.KICK_OTHER;
                        e.disallow(result, ChatColor.RED + "Das Rejoinen wurde deaktiviert!");
                    }
                } else {
                    PlayerLoginEvent.Result result = PlayerLoginEvent.Result.KICK_FULL;
                    e.disallow(result, ChatColor.RED + "Die Runde läuft bereits!");
                }
            }

        }
    }


}
