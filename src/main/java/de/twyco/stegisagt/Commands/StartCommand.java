package de.twyco.stegisagt.Commands;

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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StartCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player) {
            Player player = (Player) s;
            if (args.length == 0) {
                if (Stegisagt.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
                    if (Stegisagt.isMod(player.getUniqueId())) {
                        Stegisagt.getInstance().start();
                        return true;
                    }
                    return false;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("Wahr-oder-Falsch")) {
                    String usageMessage = ChatColor.RED + "/start Wahr-oder-Falsch <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    TrueOrFalse.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte Wahr oder Falsch...");
                } else if (args[0].equalsIgnoreCase("hide-n-seek")) {
                    String usageMessage = ChatColor.RED + "/start Hide-n-Seek <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Hide_n_Seek.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte Hide-n-Seek...");
                } else if (args[0].equalsIgnoreCase("MLG")) {
                    String usageMessage = ChatColor.RED + "/start MLG <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    MLG.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte MLG...");
                } else if (args[0].equalsIgnoreCase("snowfall")) {
                    String usageMessage = ChatColor.RED + "/start snowfall <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    SnowFall.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte snowfall...");
                } else if (args[0].equalsIgnoreCase("blockparty")) {
                    String usageMessage = ChatColor.RED + "/start blockparty <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Blockparty.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte Blockparty...");
                } else if (args[0].equalsIgnoreCase("reihenfolge")) {
                    String usageMessage = ChatColor.RED + "/start reihenfolge <lebende | tote | ausgewählte>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Reihenfolge.startGame(players, getModPlayer());
                    player.sendMessage(ChatColor.YELLOW + "Starte Reihenfolge...");
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("ampelrennen")) {
                    String usageMessage = ChatColor.RED + "/start ampelrennen <lebende | tote | ausgewählte> <Anzahl, wie viele das spiel schaffen>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    int playerCount;
                    try {
                        playerCount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Ampelrennen.startGame(players, getModPlayer(), playerCount);
                    player.sendMessage(ChatColor.YELLOW + "Starte Ampelrennen...");
                } else if (args[0].equalsIgnoreCase("molerace")) {
                    String usageMessage = ChatColor.RED + "/start molerace <lebende | tote | ausgewählte> <Anzahl, wie viele das spiel schaffen>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    int playerCount;
                    try {
                        playerCount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Molerace.startGame(players, getModPlayer(), playerCount);
                    player.sendMessage(ChatColor.YELLOW + "Starte Molerace...");
                } else if (args[0].equalsIgnoreCase("spleef")) {
                    String usageMessage = ChatColor.RED + "/start spleef <lebende | tote | ausgewählte> <Anzahl, wie viele das spiel schaffen>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    int playerCount;
                    try {
                        playerCount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Spleef.startGame(players, getModPlayer(), playerCount);
                    player.sendMessage(ChatColor.YELLOW + "Starte Spleef...");
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("schatzsuche")) {
                    String usageMessage = ChatColor.RED + "/start schatzsuche <lebende | tote | ausgewählte> " +
                            "<Anzahl, wie viele das spiel schaffen> <Anzahl der versteckten Kisten>";
                    ArrayList<Player> players = getPlayingPlayer(args[1]);
                    if (players == null) {
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    int playerCount;
                    int chestCount;
                    try {
                        playerCount = Integer.parseInt(args[2]);
                        chestCount = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        player.sendMessage(usageMessage);
                        return false;
                    }
                    Schatzsuche.startGame(players, getModPlayer(), playerCount, chestCount);
                    player.sendMessage(ChatColor.YELLOW + "Starte Ampelrennen...");
                }
            }
            return false;
        }
        return false;
    }

    private ArrayList<Player> getPlayingPlayer(String args) {
        if (args.equalsIgnoreCase("lebende")) {
            ArrayList<Player> alivePlayer = new ArrayList<>();
            for (UUID uuid : Stegisagt.getAlivePlayer()) {
                alivePlayer.add(Bukkit.getPlayer(uuid));
            }
            return alivePlayer;
        } else if (args.equalsIgnoreCase("tote")) {
            ArrayList<Player> deadPlayer = new ArrayList<>();
            for (UUID uuid : Stegisagt.getDeadPlayer()) {
                deadPlayer.add(Bukkit.getPlayer(uuid));
            }
            return deadPlayer;
        } else if (args.equalsIgnoreCase("ausgewählte")) {
            return Stegisagt.getInstance().getSelectedPlayer();
        } else {
            return null;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> playerSurvive = new ArrayList<>();
        playerSurvive.add("ampelrennen");
        playerSurvive.add("schatzsuche");
        playerSurvive.add("molerace");
        playerSurvive.add("spleef");
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("Ampelrennen");
            list.add("Schatzsuche");
            list.add("Wahr-oder-Falsch");
            list.add("Hide-n-Seek");
            list.add("MLG");
            list.add("Snowfall");
            list.add("molerace");
            list.add("spleef");
            list.add("blockparty");
            list.add("reihenfolge");
        } else if (args.length == 2) {
            list.add("lebende");
            list.add("tote");
            list.add("ausgewählte");
        } else if (args.length == 3) {
            if (playerSurvive.contains(args[0])) {
                list.add("<Überlebende_Spieler_Anzahl>");
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("schatzsuche")) {
                list.add("<Anzahl_versteckter_Kisten>");
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String currentArg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentArg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }

    private ArrayList<Player> getModPlayer() {
        ArrayList<Player> modPlayer = new ArrayList<>();
        for (UUID uuid : Stegisagt.getModPlayer()) {
            modPlayer.add(Bukkit.getPlayer(uuid));
        }
        return modPlayer;
    }
}
