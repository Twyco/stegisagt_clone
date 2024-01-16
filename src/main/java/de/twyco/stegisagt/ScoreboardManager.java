package de.twyco.stegisagt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardManager {

    private static Scoreboard scoreboard;
    private static boolean collidable = false;
    private static boolean nameTag = true;

    public static Scoreboard setScoreboard() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        scoreboard.registerNewTeam("001Mod");
        scoreboard.registerNewTeam("098lebenderSpieler");
        scoreboard.registerNewTeam("099toterSpieler");
        scoreboard.registerNewTeam("100online").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        scoreboard.getTeam("001Mod").setPrefix(ChatColor.DARK_PURPLE + "Moderator" + ChatColor.GRAY + " | ");
        scoreboard.getTeam("098lebenderSpieler").setPrefix(ChatColor.GRAY + "");
        scoreboard.getTeam("099toterSpieler").setPrefix(ChatColor.RED + "\u271E" + ChatColor.GRAY + " | ");

        scoreboard.getTeam("001Mod").setOption(Team.Option.COLLISION_RULE, (collidable ? Team.OptionStatus.ALWAYS : Team.OptionStatus.NEVER));
        scoreboard.getTeam("098lebenderSpieler").setOption(Team.Option.COLLISION_RULE, (collidable ? Team.OptionStatus.ALWAYS : Team.OptionStatus.NEVER));
        scoreboard.getTeam("099toterSpieler").setOption(Team.Option.COLLISION_RULE, (collidable ? Team.OptionStatus.ALWAYS : Team.OptionStatus.NEVER));


        scoreboard.getTeam("001Mod").setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        scoreboard.getTeam("098lebenderSpieler").setOption(Team.Option.NAME_TAG_VISIBILITY, (nameTag ? Team.OptionStatus.ALWAYS : Team.OptionStatus.NEVER));
        scoreboard.getTeam("099toterSpieler").setOption(Team.Option.NAME_TAG_VISIBILITY, (nameTag ? Team.OptionStatus.ALWAYS : Team.OptionStatus.NEVER));

        for (Player p : Bukkit.getOnlinePlayers()) {
            setTeam(p);
        }
        setSidebar();
        updateCollide(false);
        return scoreboard;
    }

    public static void updateCollide(boolean collidable) {
        ScoreboardManager.collidable = collidable;
    }

    public static void updateNameTag(boolean nameTag) {
        ScoreboardManager.nameTag = nameTag;
    }

    public static void unregisterWaitingTeam() {
        scoreboard.getTeam("100online").unregister();
    }

    private static void setSidebar() {
        if (Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            Objective objective = scoreboard.registerNewObjective("warten", "warten", ChatColor.GOLD.toString() + ChatColor.BOLD + "Stegi.Twyco.de");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.getScore(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + ChatColor.STRIKETHROUGH + "                     "
                    + ChatColor.GRAY + ChatColor.RESET).setScore(0);
            objective.getScore(ChatColor.GRAY.toString() + ChatColor.BOLD + "Maximale Spieler").setScore(2);
            objective.getScore("").setScore(3);
            objective.getScore(ChatColor.GRAY.toString() + ChatColor.BOLD + "Spieler in Lobby").setScore(5);
            objective.getScore(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + ChatColor.STRIKETHROUGH + "                     "
                    + ChatColor.RED + ChatColor.RESET).setScore(6);

            Team online = scoreboard.registerNewTeam("101waiting");
            online.addEntry(ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH);
            objective.getScore(ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH).setScore(4);
            online.setPrefix(ChatColor.WHITE + "" + (Bukkit.getOnlinePlayers().size() - Stegisagt.getModPlayerCount()));

            Team alive = scoreboard.registerNewTeam("101maxPlayer");
            alive.addEntry(ChatColor.BLACK + "" + ChatColor.STRIKETHROUGH);
            objective.getScore(ChatColor.BLACK + "" + ChatColor.STRIKETHROUGH).setScore(1);
            alive.setPrefix(ChatColor.WHITE + "" + Stegisagt.getInstance().getMaxPlayer());
        } else {
            Objective objective = scoreboard.registerNewObjective("main", "main", ChatColor.GOLD.toString() + ChatColor.BOLD + "Stegi.Twyco.de");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.getScore(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + ChatColor.STRIKETHROUGH + "                     " + ChatColor.GRAY + ChatColor.RESET).setScore(0);
            objective.getScore(ChatColor.GRAY.toString() + ChatColor.BOLD + "Spieler Lebendig").setScore(2);
            objective.getScore("").setScore(3);
            objective.getScore(ChatColor.GRAY.toString() + ChatColor.BOLD + "Spieler Online").setScore(5);
            objective.getScore(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + ChatColor.STRIKETHROUGH + "                     " + ChatColor.RED + ChatColor.RESET).setScore(6);

            Team online = scoreboard.registerNewTeam("002online");
            online.addEntry(ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH);
            objective.getScore(ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH).setScore(4);
            online.setPrefix(ChatColor.WHITE + "" + (Stegisagt.getAlivePlayerCount() + Stegisagt.getDeadPlayerCount()));

            Team alive = scoreboard.registerNewTeam("003alive");
            alive.addEntry(ChatColor.BLACK + "" + ChatColor.STRIKETHROUGH);
            objective.getScore(ChatColor.BLACK + "" + ChatColor.STRIKETHROUGH).setScore(1);
            alive.setPrefix(ChatColor.WHITE + "" + Stegisagt.getAlivePlayerCount());
        }
    }

    public static void setTeam(Player p) {
        if (Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            if (Stegisagt.isMod(p.getUniqueId())) {
                Objects.requireNonNull(scoreboard.getTeam("001Mod")).addEntry(p.getName());
            } else {
                Objects.requireNonNull(scoreboard.getTeam("100online")).addEntry(p.getName());
            }
        } else {
            if (Stegisagt.isMod(p.getUniqueId())) {
                Objects.requireNonNull(scoreboard.getTeam("001Mod")).addEntry(p.getName());
            } else if (Stegisagt.isAlive(p.getUniqueId())) {
                Objects.requireNonNull(scoreboard.getTeam("098lebenderSpieler")).addEntry(p.getName());
            } else if (Stegisagt.isDead(p.getUniqueId())) {
                Objects.requireNonNull(scoreboard.getTeam("099toterSpieler")).addEntry(p.getName());
            }
        }
        p.setScoreboard(scoreboard);
    }

    public static ArrayList<UUID> getMods(){
        ArrayList<UUID> modPlayer = new ArrayList<>();
        for(String str : Objects.requireNonNull(scoreboard.getTeam("001Mod")).getEntries()){
            Player player = Bukkit.getPlayer(str);
            if(player != null){
                modPlayer.add(player.getUniqueId());
            }
        }
        return modPlayer;
    }

    public static ArrayList<UUID> getAlivePlayer(){
        ArrayList<UUID> alivePlayer = new ArrayList<>();
        for(String str : Objects.requireNonNull(scoreboard.getTeam("098lebenderSpieler")).getEntries()){
            Player player = Bukkit.getPlayer(str);
            if(player != null){
                alivePlayer.add(player.getUniqueId());
            }
        }
        return alivePlayer;
    }

    public static ArrayList<UUID> getDeadPlayer(){
        ArrayList<UUID> deadPlayer = new ArrayList<>();
        for(String str : Objects.requireNonNull(scoreboard.getTeam("099toterSpieler")).getEntries()){
            Player player = Bukkit.getPlayer(str);
            if(player != null){
                deadPlayer.add(player.getUniqueId());
            }
        }
        return deadPlayer;
    }

}
