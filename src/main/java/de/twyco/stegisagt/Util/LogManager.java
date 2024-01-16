package de.twyco.stegisagt.Util;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.Stegisagt;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager {
/*
    private static Stegisagt instance = Stegisagt.getInstance();
    private static File logPath = new File(instance.getDataFolder() + "//logs/");

    private static void setLatestLog() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String date = simpleDateFormat.format(new Date());
        List<String> logHistory;
        if (instance.getLogConfig().getFileConfiguration().isList("log.history")) {
            logHistory = (List<String>) instance.getLogConfig().getFileConfiguration().getList("log.history");
        } else {
            logHistory = new ArrayList<>();
            logHistory.add("latest.log");
        }
        File latestLog = new File(logPath, "latest.log");
        if (latestLog.exists()) {
            String oldDate = instance.getSpecificLogConfig("latest.log", logPath).getFileConfiguration().getString("log.created");
            File newFile = new File(logPath, oldDate + ".log");
            assert logHistory != null;
            logHistory.add(newFile.getName().toString());
            latestLog.renameTo(newFile);
            latestLog.delete();
        }
        instance.getLogConfig().getFileConfiguration().set("log.history", logHistory);
        instance.getLogConfig().save();
        Config config = new Config("latest.log", logPath);
        config.getFileConfiguration().set("log.created", date);
        config.save();
    }


    private static void saveGame() {
        Config config = new Config("latest.log", logPath);
        FileConfiguration fileConfig = config.getFileConfiguration();
        fileConfig.set("Game.alivePlayer", Stegisagt.getAlivePlayer());
        fileConfig.set("Game.deadPlayer", Stegisagt.getDeadPlayer());
        fileConfig.set("Game.modPlayer", Stegisagt.getModPlayer());
        fileConfig.set("Game.leavedPlayer", instance.getLeavedPlayer());
        fileConfig.set("Game.maxPlayer", instance.getMaxPlayer());
        fileConfig.set("Game.pvp", instance.isPvp());
        fileConfig.set("Game.fallDamage", instance.isFallDamage());
        fileConfig.set("Game.hunger", instance.isHunger());
        fileConfig.set("Game.buildPlace", instance.isBuildPlace());
        fileConfig.set("Game.buildBreak", instance.isBuildBreak());
        fileConfig.set("Game.blockDrop", instance.isBlockDrop());
        fileConfig.set("Game.entityDrop", instance.isEntityDrop());
        fileConfig.set("Game.playerCollision", instance.isPlayerCollision());
        fileConfig.set("Game.playerVisibility", instance.isPlayerVisibility());
        fileConfig.set("Game.attackSpeed", instance.getAttackSpeed());
        config.save();
    }

    private static void loadGameFromLog(String logName) {
        if(!latestExist()){
            return;
        }
        Config config = instance.getSpecificLogConfig(logName, logPath);
        FileConfiguration fileConfig = config.getFileConfiguration();
        Stegisagt.setGameStatus(GameStatus.PLAYING);
        Stegisagt.setAlivePlayer((ArrayList<Player>) fileConfig.get("Game.alivePlayer"));
        Stegisagt.setDeadPlayer((ArrayList<Player>) fileConfig.get("Game.deadPlayer"));
        Stegisagt.setModPlayer((ArrayList<Player>) fileConfig.get("Game.modPlayer"));
        instance.setLeavedPlayer((ArrayList<OfflinePlayer>) fileConfig.get("Game.leavedPlayer"));
        instance.setMaxPlayer(fileConfig.getInt("Game.maxPlayer"));
        instance.setPvp(fileConfig.getBoolean("Game.pvp"));
        instance.setFallDamage(fileConfig.getBoolean("Game.fallDamage"));
        instance.setHunger(fileConfig.getBoolean("Game.hunger"));
        instance.setBuildPlace(fileConfig.getBoolean("Game.buildPlace"));
        instance.setBuildBreak(fileConfig.getBoolean("Game.buildBreak"));
        instance.setBlockDrop(fileConfig.getBoolean("Game.blockDrop"));
        instance.setEntityDrop(fileConfig.getBoolean("Game.entityDrop"));
        instance.setPlayerCollision(fileConfig.getBoolean("Game.playerCollision"));
        instance.setPlayerVisibility(fileConfig.getBoolean("Game.playerVisibility"));
        instance.setAttackSpeed(fileConfig.getDouble("Game.attackSpeed"));
        for (OfflinePlayer offlinePlayer : Stegisagt.getAlivePlayer()) {
            instance.addLeavedPlayer(offlinePlayer);
        }
        for (OfflinePlayer offlinePlayer : Stegisagt.getDeadPlayer()) {
            instance.addLeavedPlayer(offlinePlayer);
        }
        for (OfflinePlayer offlinePlayer : Stegisagt.getModPlayer()) {
            instance.addLeavedPlayer(offlinePlayer);
        }
        Stegisagt.updateScoreboard();
    }

    private static List<String> getLogHistory() {
        List<String> logHistory;
        if (instance.getLogConfig().getFileConfiguration().isList("log.history")) {
            logHistory = (List<String>) instance.getLogConfig().getFileConfiguration().getList("log.history");
        } else {
            logHistory = new ArrayList<>();
        }
        return logHistory;
    }

    private static boolean latestExist() {
        File latestLog = new File(logPath, "latest.log");
        return latestLog.exists();
    }

*/
}
