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
import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.trueorfalse.TrueOrFalse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent e) {
        if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED) &&
                !Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            if (!Stegisagt.getInstance().isEntityDrop()) {
                e.setDroppedExp(0);
                e.getDrops().clear();
            }
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                switch (Stegisagt.getGameStatus()){
                    case PLAYING -> {
                            Stegisagt.killPlayer(player);
                    }
                    case PLAYING_SCHATZSUCHE -> {
                        Schatzsuche.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_AMPELRENNEN -> {
                        Ampelrennen.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_TRUEORFALSE -> {
                        TrueOrFalse.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_HIDE_N_SEEK -> {
                        Hide_n_Seek.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_MLG -> {
                        MLG.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_MOLERACE -> {
                        Molerace.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_SNOWFALL -> {
                        SnowFall.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_BLOCKPARTY -> {
                        Blockparty.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_SPLEEF -> {
                        Spleef.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    case PLAYING_REIHENFOLGE -> {
                        Reihenfolge.killPlayer(player);
                        Stegisagt.killPlayer(player);
                    }
                    //TODO MINIGAME
                }
            }
        }
    }

}
