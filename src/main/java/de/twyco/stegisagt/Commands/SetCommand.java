package de.twyco.stegisagt.Commands;

import de.twyco.stegisagt.Stegisagt;
import de.twyco.stegisagt.Util.Config;
import de.twyco.stegisagt.Util.LogManager;
import de.twyco.trueorfalse.items.SelectionBlock;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetCommand implements CommandExecutor {

    private final Stegisagt instance;

    public SetCommand() {
        this.instance = Stegisagt.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (args.length == 2) {
                if (args[0].equals("locations") || args[0].equals("loc")) {
                    Config config = Stegisagt.getInstance().getLocationsConfig();
                    switch (args[1]) {
                        case "dead" -> {
                            config.getFileConfiguration().set("Locations.spawn.dead", p.getLocation());
                            config.save();
                            s.sendMessage(ChatColor.GREEN + "Der Spawn für die Toten wurde gespeichert");
                            return true;
                        }
                        case "login" -> {
                            config.getFileConfiguration().set("Locations.spawn.login", p.getLocation());
                            config.save();
                            s.sendMessage(ChatColor.GREEN + "Der Spawn für den Login wurde gespeichert");
                            return true;
                        }
                        case "start" -> {
                            config.getFileConfiguration().set("Locations.spawn.start", p.getLocation());
                            config.save();
                            s.sendMessage(ChatColor.GREEN + "Der Spawn für den Start wurde gespeichert");
                            return true;
                        }
                    }
                } else if (args[0].equals("world")) {
                    Config config = instance.getResetConfig();
                    if (args[1].equals("save")) {
                        config.getFileConfiguration().set("World.Name", Objects.requireNonNull(p.getLocation().getWorld()).getName());
                        config.getFileConfiguration().set("World.isReset", false);
                        config.save();
                        try {
                            instance.saveWorld(instance.getResetConfig().getFileConfiguration().getString("World.Name"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            s.sendMessage(ChatColor.RED + "Die Aktuelle Welt konnte nicht für den Reset gespeichert werden!");
                            return false;
                        }
                        s.sendMessage(ChatColor.GREEN + "Die Aktuelle Welt wurde für den Reset gespeichert!");
                        return true;
                    }
                }
            } else {
                if (args[0].equalsIgnoreCase("protect")) {
                    if(args[1].equalsIgnoreCase("add")){
                        if(args[3].equalsIgnoreCase("1")){
                            List<String> allProtectLocs = instance.getLocationsConfig().getFileConfiguration().getStringList("World.Protect.list");
                            if(!allProtectLocs.contains(args[2])){
                                allProtectLocs.add(args[2]);
                                instance.getLocationsConfig().getFileConfiguration().set("World.Protect.list", allProtectLocs);
                            }
                            instance.getLocationsConfig().getFileConfiguration().set("World.Protect." + args[2] + ".1", p.getLocation());
                            instance.getLocationsConfig().save();
                        }else if (args[3].equalsIgnoreCase("2")){
                            instance.getLocationsConfig().getFileConfiguration().set("World.Protect." + args[2] + ".2", p.getLocation());
                            instance.getLocationsConfig().save();
                            saveArea(instance.getLocationsConfig(), instance.getLocationsConfig().getFileConfiguration().getLocation("World.Protect." + args[2] + ".2"),
                                    instance.getLocationsConfig().getFileConfiguration().getLocation("World.Protect." + args[2] + ".1"),
                                    "World.Protect." + args[2] + ".Block");
                        }
                    }
                }
            }
        }
        return false;
    }

    private void saveArea(Config config, Location firstLocation, Location sndLocation, String configPath) {
        config.getFileConfiguration().set(configPath, null);
        ArrayList<Location> area = new ArrayList<>();
        World world = firstLocation.getWorld();
        if (world == null) {
            return;
        }
        int minX = (int) Math.min(firstLocation.getX(), sndLocation.getX());
        int minY = (int) Math.min(firstLocation.getY(), sndLocation.getY());
        int minZ = (int) Math.min(firstLocation.getZ(), sndLocation.getZ());
        int maxX = (int) Math.max(firstLocation.getX(), sndLocation.getX());
        int maxY = (int) Math.max(firstLocation.getY(), sndLocation.getY());
        int maxZ = (int) Math.max(firstLocation.getZ(), sndLocation.getZ());
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    if(!block.getType().equals(Material.AIR)){
                        area.add(block.getLocation());
                    }
                }
            }
        }
        config.getFileConfiguration().set(configPath, area);
        config.save();
    }

}
