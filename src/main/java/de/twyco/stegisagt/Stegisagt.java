package de.twyco.stegisagt;

import de.twyco.ampelrennen.Ampelrennen;
import de.twyco.blockparty.Blockparty;
import de.twyco.hidenseek.Hide_n_Seek;
import de.twyco.mlg.MLG;
import de.twyco.molerace.Molerace;
import de.twyco.reihenfolge.Reihenfolge;
import de.twyco.schatzsuche.Schatzsuche;
import de.twyco.snowfall.SnowFall;
import de.twyco.spleef.Spleef;
import de.twyco.stegisagt.Commands.*;
import de.twyco.stegisagt.Items.ModItems.*;
import de.twyco.stegisagt.Listener.CustomItems.ModItems.*;
import de.twyco.stegisagt.Listener.CustomItems.Settings.SettingsMenuListener;
import de.twyco.stegisagt.Listener.GameListener.*;
import de.twyco.stegisagt.Listener.Minigames.MinigamesMenuListener;
import de.twyco.stegisagt.Listener.Selection.CoinFlipListener;
import de.twyco.stegisagt.Miigames.CoinFlip;
import de.twyco.stegisagt.Util.Config;
import de.twyco.trueorfalse.TrueOrFalse;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class Stegisagt extends JavaPlugin {

    private static Stegisagt instance;

    private Config locationsConfig;
    private Config resetConfig;
    private Config logConfig;

    private static GameStatus gameStatus;
    private static ArrayList<UUID> alivePlayer;
    private static ArrayList<UUID> deadPlayer;
    private static ArrayList<UUID> modPlayer;
    private ArrayList<UUID> leavedPlayer;
    private ArrayList<Player> selectedPlayer;
    private static HashMap<UUID, Integer> reviveCount;
    private int maxPlayer;
    private boolean pvp;
    private boolean fallDamage;
    private boolean hunger;
    private boolean buildPlace;
    private boolean buildBreak;
    private boolean blockDrop;
    private boolean entityDrop;
    private boolean playerCollision;
    private boolean playerVisibility;
    private double attackSpeed;
    private boolean rejoin;

    public static Stegisagt getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        resetConfig = new Config("resetConfig.yml", getDataFolder());
        Config config = getResetConfig();
        File logPath = new File(getDataFolder() + "//logs/");
        if (!logPath.isDirectory() || !logPath.exists()) {
            logPath.mkdirs();
        }
        logConfig = new Config("logConfig.yml", logPath);
        if (!config.getFileConfiguration().contains("World.isReset")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "KEEPING WORLD (FirstStart)!!!");
            config.getFileConfiguration().set("World.isReset", false);
            config.save();
            return;
        }
        if (config.getFileConfiguration().getBoolean("World.isReset")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "RESETTING WORLD!!!");
            try {
                resetWorld(config.getFileConfiguration().getString("World.Name"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            config.getFileConfiguration().set("World.isReset", false);
            config.save();
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "KEEPING WORLD!!!");
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Das Plugin wird geladen...");
        instance = this;
        reviveCount = new HashMap<>();
        registerListener();
        registerCommands();
        alivePlayer = new ArrayList<>();
        deadPlayer = new ArrayList<>();
        modPlayer = new ArrayList<>();
        leavedPlayer = new ArrayList<>();
        selectedPlayer = new ArrayList<>();
        Bukkit.setDefaultGameMode(GameMode.SURVIVAL);
        Bukkit.setWhitelist(false);
        gameStatus = GameStatus.CLOSED;
        pvp = false;
        fallDamage = false;
        hunger = false;
        buildPlace = false;
        buildBreak = false;
        blockDrop = false;
        entityDrop = false;
        playerCollision = false;
        playerVisibility = true;
        attackSpeed = 4;
        rejoin = true;
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                locationsConfig = new Config("worldConfig.yml", getDataFolder());
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Die Location Config wurde geladen");
                for (World world1 : Bukkit.getWorlds()) {
                    setGamerules(world1);
                }
                CoinFlip.setItemFrame(true);
                //LogManager.loadGameFromLog("latest.log");
            }
        }, 0L);
        ScoreboardManager.updateNameTag(true);
    }

    @Override
    public void onDisable() {
        resetSelectedPlayer();
        //Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Savig game....");
        //LogManager.saveGame();
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockDropItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new HangingBreakByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandSendListener(), this);


        Bukkit.getPluginManager().registerEvents(new BowListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerOverviewListener(), this);
        Bukkit.getPluginManager().registerEvents(new SettingsMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportListener(), this);
        Bukkit.getPluginManager().registerEvents(new TeleportCompassListener(), this);

        Bukkit.getPluginManager().registerEvents(new MinigamesMenuListener(), this);

        Bukkit.getPluginManager().registerEvents(new CoinFlipListener(), this);

    }

    private void registerCommands() {
        Objects.requireNonNull(Bukkit.getPluginCommand("open")).setExecutor(new OpenCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("set")).setExecutor(new SetCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("mod")).setExecutor(new ModCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("start")).setExecutor(new StartCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("reset")).setExecutor(new ResetCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("revive")).setExecutor(new ReviveCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("items")).setExecutor(new ItemsCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("rejoin")).setExecutor(new RejoinCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("coinflip")).setExecutor(new CoinflipCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("msg")).setExecutor(new MSGCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fix")).setExecutor(new FixCommand());
        //Objects.requireNonNull(Bukkit.getPluginCommand("minigame")).setExecutor(new DiscordCommand());
    }

    private void setGamerules(World world) {
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setAutoSave(false);
        world.setHardcore(false);
        world.setPVP(true);
        world.setTime(6000L);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, true);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_MOB_LOOT, true);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, false);
        world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
        world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 10000);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, true);
        world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        world.setGameRule(GameRule.REDUCED_DEBUG_INFO, false);
        world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, true);
        world.setGameRule(GameRule.SPAWN_RADIUS, 0);
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, true);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
    }

    public void giveModItems(Player p) {
        p.getInventory().clear();
        p.getInventory().addItem(new Sword());
        p.getInventory().addItem(new Bow());
        p.getInventory().addItem(new Revivor());
        p.getInventory().addItem(new TeleportSelector());
        p.getInventory().addItem(new TeleportCompass());
        p.getInventory().addItem(new MinigamesSelector());
        p.getInventory().addItem(new PlayerOverview(p));
        p.getInventory().addItem(new Settigns());
    }

    //----------------Alive Player----------------

    private static void addAlivePlayer(UUID uuid) {
        alivePlayer.add(uuid);
    }

    private static void removeAlivePlayer(UUID uuid) {
        alivePlayer.remove(uuid);
    }

    public static boolean isAlive(UUID uuid) {
        return alivePlayer.contains(uuid);
    }

    public static int getAlivePlayerCount() {
        return alivePlayer.size();
    }

    public static ArrayList<UUID> getAlivePlayer() {
        return alivePlayer;
    }

    public static void fixAlivePlayer() {
        Stegisagt.alivePlayer = ScoreboardManager.getAlivePlayer();
    }

    //----------------Dead Player----------------

    private static void addDeadPlayer(UUID uuid) {
        deadPlayer.add(uuid);
    }

    private static void removeDeadPlayer(UUID uuid) {
        deadPlayer.remove(uuid);
    }

    public static boolean isDead(UUID uuid) {
        return deadPlayer.contains(uuid);
    }

    public static int getDeadPlayerCount() {
        return deadPlayer.size();
    }

    public static ArrayList<UUID> getDeadPlayer() {
        return deadPlayer;
    }

    public static void fixDeadPlayer() {
        Stegisagt.deadPlayer = ScoreboardManager.getDeadPlayer();
    }

    //----------------Mod Player----------------

    public void addModPlayer(UUID uuid) {
        modPlayer.add(uuid);
    }

    public void removeModPlayer(UUID uuid) {
        modPlayer.remove(uuid);
    }

    public static boolean isMod(UUID uuid) {
        return modPlayer.contains(uuid);
    }

    public static ArrayList<UUID> getModPlayer() {
        return modPlayer;
    }

    public static int getModPlayerCount() {
        return modPlayer.size();
    }

    public static void fixModPlayer() {
        Stegisagt.modPlayer = ScoreboardManager.getMods();
    }

    //----------------Selected Player----------------

    public void addSelectedPlayer(Player player) {
        selectedPlayer.add(player);
    }

    public void removeSelectedPlayer(Player player) {
        selectedPlayer.remove(player);
    }

    public boolean isSelected(Player player) {
        return selectedPlayer.contains(player);
    }

    public void clearSelectedPlayer() {
        selectedPlayer.clear();
    }

    public ArrayList<Player> getSelectedPlayer() {
        return selectedPlayer;
    }


    //----------------Game Status----------------

    public static void setGameStatus(GameStatus pGameStatus) {
        gameStatus = pGameStatus;
    }

    public static GameStatus getGameStatus() {
        return gameStatus;
    }

    //----------------Max Player----------------

    public int getMaxPlayer() {
        return this.maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    //----------------Leaved Player----------------

    public void addLeavedPlayer(UUID uuid) {
        this.leavedPlayer.add(uuid);
    }

    public void removeLeavedPlayer(UUID uuid) {
        this.leavedPlayer.remove(uuid);
    }

    public boolean isLeaved(UUID uuid) {
        return this.leavedPlayer.contains(uuid);
    }

    //----------------Revive Count----------------

    public static int getPlayerReviveCount(UUID uuid) {
        return reviveCount.getOrDefault(uuid, -1);
    }

    public static void addPlayerReviveCount(UUID uuid) {
        if (!reviveCount.containsKey(uuid)) {
            reviveCount.put(uuid, 1);
            return;
        }
        reviveCount.replace(uuid, getPlayerReviveCount(uuid) + 1);
    }

    //----------------PvP----------------

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pPvp) {
        pvp = pPvp;
    }

    //----------------FallDamage----------------

    public boolean isFallDamage() {
        return fallDamage;
    }

    public void setFallDamage(boolean pFallDamage) {
        fallDamage = pFallDamage;
    }

    //----------------Hunger----------------

    public boolean isHunger() {
        return hunger;
    }

    public void setHunger(boolean pHunger) {
        if (!pHunger) {
            resetHunger();
        }
        hunger = pHunger;
    }

    private void resetHunger() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setFoodLevel(20);
        }
    }

    //----------------Build----------------

    public boolean isBuildBreak() {
        return buildBreak;
    }

    public void setBuildBreak(boolean pBuildBreak) {
        buildBreak = pBuildBreak;
    }

    public boolean isBuildPlace() {
        return buildPlace;
    }

    public void setBuildPlace(boolean pBuildPlace) {
        buildPlace = pBuildPlace;
    }

    //----------------Blockdrop----------------

    public boolean isBlockDrop() {
        return blockDrop;
    }

    public void setBlockDrop(boolean pBlockDrop) {
        blockDrop = pBlockDrop;
    }

    //----------------Mobdrop----------------

    public boolean isEntityDrop() {
        return entityDrop;
    }

    public void setEntityDrop(boolean pEntityDrop) {
        entityDrop = pEntityDrop;
    }

    //----------------playerCollision----------------

    public boolean isPlayerCollision() {
        return playerCollision;
    }

    public void setPlayerCollision(boolean pPlayerCollision) {
        ScoreboardManager.updateCollide(pPlayerCollision);
        playerCollision = pPlayerCollision;
    }

    //----------------AttackSpeed----------------


    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
        updateAttackSpeed();
    }

    private void updateAttackSpeed() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(attackSpeed);
        }
    }

    //----------------playerVisibility----------------


    public boolean isPlayerVisibility() {
        return playerVisibility;
    }

    public void setPlayerVisibility(boolean playerVisibility) {
        this.playerVisibility = playerVisibility;
        if (playerVisibility) {
            Bukkit.getOnlinePlayers().forEach(player ->
                    Bukkit.getOnlinePlayers().forEach(player1 -> {
                        player.showPlayer(instance, player1);
                    })
            );
        } else {
            ArrayList<Player> allPlayerWithoutMods = new ArrayList<>();
            for (UUID uuid : getAlivePlayer()) {
                Player player = Bukkit.getPlayer(uuid);
                if(player == null){
                    continue;
                }
                allPlayerWithoutMods.add(player);
            }
            for (UUID uuid : getDeadPlayer()) {
                Player player = Bukkit.getPlayer(uuid);
                if(player == null){
                    continue;
                }
                allPlayerWithoutMods.add(player);
            }
            allPlayerWithoutMods.forEach(player ->
                    allPlayerWithoutMods.forEach(player1 -> {
                        player.hidePlayer(instance, player1);
                    })
            );
        }

/*
        if (playerVisibility) { //Spieler sehen sich
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    if (player != null && player1 != null) {
                        player.showPlayer(instance, player1);
                        System.out.println("Show");
                    }
                }
            }
        } else {
            ArrayList<Player> allPlayerWithoutMods = new ArrayList<>();
            for (UUID uuid : getAlivePlayer()) {
                allPlayerWithoutMods.add(Bukkit.getPlayer(uuid));
            }
            for (UUID uuid : getDeadPlayer()) {
                allPlayerWithoutMods.add(Bukkit.getPlayer(uuid));
            }
            for (Player player : allPlayerWithoutMods) {
                for (Player player1 : allPlayerWithoutMods) {
                    if (player != null && player1 != null) {
                        player.hidePlayer(instance, player1);
                        System.out.println("Hide");
                    }
                }
            }
        }

 */
    }

    //----------------Config---------------


    public Config getLocationsConfig() {
        return locationsConfig;
    }

    public Config getResetConfig() {
        return resetConfig;
    }
/*
    public Config getSpecificLogConfig(String fileName, File filePath) {
        return new Config(fileName, filePath);
    }

    public Config getLogConfig() {
        return logConfig;
    }
*/
    //----------------xxx---------------

    public void resetSelectedPlayer() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.removePotionEffect(PotionEffectType.GLOWING);
        }
        Stegisagt.getInstance().clearSelectedPlayer();
    }

    public void leavePlayer(Player player) {
        //LogManager.saveGame();
        UUID uuid = player.getUniqueId();
        if (getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            if (isAlive(uuid)) {
                removeAlivePlayer(uuid);
            } else if (isDead(uuid)) {
                removeDeadPlayer(uuid);
            } else if (isMod(uuid)) {
                removeModPlayer(uuid);
            }
        } else if (!getGameStatus().equals(GameStatus.CLOSED)) {
            removeAlivePlayer(uuid);
            removeDeadPlayer(uuid);
            removeModPlayer(uuid);
            addLeavedPlayer(uuid);
        }
        //LogManager.saveGame();
        updateScoreboard();
    }

    public void rejoin(Player player) {
        UUID uuid = player.getUniqueId();
        removeLeavedPlayer(uuid);
        if (isAlive(uuid)) {
            alivePlayer.add(uuid);
            Location location = getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
            if (location == null) {
                return;
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } else if (isDead(uuid)) {
            deadPlayer.add(uuid);
            Location location = getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
            if (location == null) {
                return;
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } else if (isMod(uuid)) {
            modPlayer.add(uuid);
            Location location = getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
            if (location == null) {
                return;
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            giveModItems(player);
        } else {
            deadPlayer.add(uuid);
            Location location = getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
            if (location == null) {
                return;
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
        updateScoreboard();
    }

    public void addMod(Player player) {
        UUID uuid = player.getUniqueId();
        player.setGameMode(GameMode.CREATIVE);
        removeDeadPlayer(uuid);
        removeAlivePlayer(uuid);
        addModPlayer(uuid);
        giveModItems(player);
        updateScoreboard();
    }

    public static void revivePlayer(Player p) {
        revivePlayer(p, null);
    }

    public static void revivePlayer(Player player, Location location) {
        UUID uuid = player.getUniqueId();
        if (location == null) {
            location = getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
        }
        assert location != null;
        if (isMod(uuid) || isAlive(uuid)) {
            if (isDead(uuid)) {
                removeDeadPlayer(uuid);
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            return;
        }
        addPlayerReviveCount(player.getUniqueId());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isMod(p.getUniqueId())) {
                TextComponent message = new TextComponent(ChatColor.AQUA + player.getName() + " wurde wiederbelebt.");
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Kill")));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/kill " + player.getName()));
                p.spigot().sendMessage(message);
            } else {
                p.sendMessage(ChatColor.AQUA + player.getName() + " wurde wiederbelebt.");
            }
        }
        addAlivePlayer(uuid);
        removeDeadPlayer(uuid);
        updateScoreboard();
        player.getInventory().clear();
        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    public static void killPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        switch (getGameStatus()) {
            case PLAYING_SCHATZSUCHE -> Schatzsuche.killPlayer(player);
            case PLAYING_AMPELRENNEN -> Ampelrennen.killPlayer(player);
            case PLAYING_TRUEORFALSE -> TrueOrFalse.killPlayer(player);
            case PLAYING_HIDE_N_SEEK -> Hide_n_Seek.killPlayer(player);
            case PLAYING_MOLERACE -> Molerace.killPlayer(player);
            case PLAYING_SNOWFALL -> SnowFall.killPlayer(player);
            case PLAYING_MLG -> MLG.killPlayer(player);
            case PLAYING_BLOCKPARTY -> Blockparty.killPlayer(player);
            case PLAYING_SPLEEF -> Spleef.killPlayer(player);
            case PLAYING_REIHENFOLGE -> Reihenfolge.killPlayer(player);
            //TODO MINIGAME
        }
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        if (!isDead(uuid)) {
            addDeadPlayer(uuid);
        }
        if (isAlive(uuid)) {
            removeAlivePlayer(uuid);
        }
        Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
        if (location == null) {
            throw new RuntimeException("unknown dead location");
        }
        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isMod(p.getUniqueId())) {
                TextComponent message = new TextComponent(ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + ChatColor.RED + player.getName() + " ist gestorben.");
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Revive")));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/revive " + player.getName()));
                p.spigot().sendMessage(message);
            } else {
                p.sendMessage(ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + player.getName() + ChatColor.RED + " ist gestorben.");
            }
        }
        updateScoreboard();
    }

    public static void updateScoreboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ScoreboardManager.setScoreboard();
            }
        }.runTaskLater(Stegisagt.getInstance(), 1);
    }

    public static void teleportAllDeadToSpawn() {
        Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
        if (location == null) {
            throw new RuntimeException("unknown dead location");
        }
        for (UUID uuid : Stegisagt.getDeadPlayer()) {
            Player player = Bukkit.getPlayer(uuid);
            assert player != null;
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    public static void teleportAllAliveToStart() {
        Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
        if (location == null) {
            throw new RuntimeException("unknown start location");
        }
        for (UUID uuid : Stegisagt.getAlivePlayer()) {
            Player player = Bukkit.getPlayer(uuid);
            assert player != null;
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    public static void teleportToAliveOrDead(Player player) {
        UUID uuid = player.getUniqueId();
        if (isMod(uuid)) {
            player.setGameMode(GameMode.CREATIVE);
        } else {
            player.setGameMode(GameMode.SURVIVAL);
        }
        if (isDead(uuid)) {
            Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.dead");
            if (location == null) {
                throw new RuntimeException("unknown dead location");
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } else {
            Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
            if (location == null) {
                throw new RuntimeException("unknown start location");
            }
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    public void start() {
        //LogManager.setLatestLog();
        ScoreboardManager.unregisterWaitingTeam();
        setPlayerVisibility(true);
        Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.start");
        if (location == null) {
            throw new RuntimeException("unknown start location");
        }
        setGameStatus(GameStatus.PLAYING);
        ArrayList<Player> allPlayer = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (UUID uuid : getModPlayer()) {
            allPlayer.remove(Bukkit.getPlayer(uuid));
        }
        for (UUID uuid : getModPlayer()) {
            Player player = Bukkit.getPlayer(uuid);
            assert player != null;
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.sendTitle(ChatColor.GOLD + "Stegi sagt:", ChatColor.RED + "\"Stegi sagt startet jetzt!\"", 20, 80, 20);
        }
        for (Player player : allPlayer) {
            addAlivePlayer(player.getUniqueId());
            reviveCount.put(player.getUniqueId(), 0);
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.sendTitle(ChatColor.GOLD + "Stegi sagt:", ChatColor.RED + "\"Stegi sagt startet jetzt!\"", 20, 80, 20);
        }
        updateScoreboard();
    }

    //----------------WORLD RESET---------------

    private void resetWorld(String worldName) throws IOException {
        File backupPath = new File("ResetBackup/");
        File backup_world = new File(backupPath, worldName + "/");
        File backup_nether = new File(backupPath, worldName + "_nether/");
        File backup_end = new File(backupPath, worldName + "_the_end/");

        if (!(backup_world.isDirectory() && backup_nether.isDirectory() && backup_end.isDirectory())) {
            return;
        }

        File world = new File(Bukkit.getWorldContainer(), worldName);
        File nether = new File(Bukkit.getWorldContainer(), worldName + "_nether");
        File end = new File(Bukkit.getWorldContainer(), worldName + "_the_end");

        FileUtils.deleteDirectory(world);
        FileUtils.deleteDirectory(nether);
        FileUtils.deleteDirectory(end);
        FileUtils.copyDirectoryToDirectory(backup_world, Bukkit.getWorldContainer());
        FileUtils.copyDirectoryToDirectory(backup_nether, Bukkit.getWorldContainer());
        FileUtils.copyDirectoryToDirectory(backup_end, Bukkit.getWorldContainer());
    }

    public void saveWorld(String worldName) throws IOException {
        File world = new File(Bukkit.getWorldContainer(), worldName);
        File nether = new File(Bukkit.getWorldContainer(), worldName + "_nether");
        File end = new File(Bukkit.getWorldContainer(), worldName + "_the_end");
        File backupPath = new File("ResetBackup/");
        if (!backupPath.isDirectory() || !backupPath.exists()) {
            backupPath.mkdirs();
        }
        if (new File(backupPath, worldName).exists()) {
            FileUtils.deleteDirectory(new File(backupPath, worldName));
        }
        if (new File(backupPath, worldName + "_nether").exists()) {
            FileUtils.deleteDirectory(new File(backupPath, worldName + "_nether"));
        }
        if (new File(backupPath, worldName + "_the_end").exists()) {
            FileUtils.deleteDirectory(new File(backupPath, worldName + "_the_end"));
        }
        FileUtils.copyDirectoryToDirectory(world, backupPath);
        FileUtils.copyDirectoryToDirectory(nether, backupPath);
        FileUtils.copyDirectoryToDirectory(end, backupPath);
    }

    public boolean isRejoin() {
        return rejoin;
    }

    public void setRejoin(boolean rejoin) {
        this.rejoin = rejoin;
    }
}
