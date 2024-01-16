package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.stegisagt.Util.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.isOp() || Stegisagt.isMod(p.getUniqueId())) {
            return;
        }
        if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED) &&
                !Stegisagt.getGameStatus().equals(GameStatus.PLAYING_MOLERACE)) {
            if (!Stegisagt.getInstance().isBuildBreak()) {
                e.setCancelled(true);
            }
            if(isProtectZone(e.getBlock().getLocation())){
                e.setCancelled(true);
            }
        }
        if(isProtectZone(e.getBlock().getLocation())){
            e.setCancelled(true);
        }
    }

    private boolean isProtectZone(Location location) {
        Config config = Stegisagt.getInstance().getLocationsConfig();
        List<String> allProtectLocs = config.getFileConfiguration().getStringList("World.Protect.list");
        for (String str : allProtectLocs) {
            ArrayList<Location> locs = (ArrayList<Location>) config.getFileConfiguration().getList("World.Protect." + str + ".Block");
            for (Location loc : locs) {
                if(location.equals(loc)){
                    return true;
                }
            }
        }
        return false;
    }

}
