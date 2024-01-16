package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.GameStatus;
import de.twyco.stegisagt.ScoreboardManager;
import de.twyco.stegisagt.Stegisagt;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class PlayerJoinListener implements Listener {

    private final Stegisagt instance;

    public PlayerJoinListener() {
        this.instance = Stegisagt.getInstance();
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.setHealth(20);
        p.setFoodLevel(20);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(instance.getAttackSpeed());
        if (!Stegisagt.getGameStatus().equals(GameStatus.CLOSED)) {
            instance.setPlayerCollision(instance.isPlayerCollision());
        }
        instance.setPlayerVisibility(instance.isPlayerVisibility());
        p.setPlayerListHeaderFooter("\n" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "        " +
                        ChatColor.DARK_GRAY + "[ " + ChatColor.GOLD + "STEGI SAGT " + ChatColor.DARK_GRAY + "]" +
                        ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "        " + "\n" + ChatColor.LIGHT_PURPLE + "twitch.tv/stegi" + ChatColor.RESET,
                ChatColor.AQUA + "Twitch Prime ist kostenlos!");

        switch (Stegisagt.getGameStatus()) {
            case CLOSED -> {
                if (p.isOp()) {
                    e.setJoinMessage(ChatColor.RED + e.getPlayer().getName() + ChatColor.GRAY + " hat den Server betreten.");
                }
                Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.login");
                if (location == null) {
                    throw new RuntimeException("unknown Login Location");
                }
                p.teleport(location);
            }
            case WAITING_FOR_PLAYERS -> {
                p.setGameMode(GameMode.SURVIVAL);
                int curPlayer = Bukkit.getOnlinePlayers().size() - Stegisagt.getModPlayerCount();
                System.out.println(curPlayer);
                e.setJoinMessage(ChatColor.GRAY + e.getPlayer().getName() + " hat den Server betreten. [" +
                        curPlayer + "/" + instance.getMaxPlayer() + "]");

                Location location = Stegisagt.getInstance().getLocationsConfig().getFileConfiguration().getLocation("Locations.spawn.login");
                if (location == null) {
                    throw new RuntimeException("unknown Login Location");
                }
                Stegisagt.updateScoreboard();
                p.teleport(location);
            }
            default -> {
                e.setJoinMessage("");
                if (Stegisagt.isAlive(p.getUniqueId())) {
                    e.setJoinMessage(ChatColor.GRAY + p.getName() + " hat den Server betreten.");
                } else if (Stegisagt.isMod(p.getUniqueId())) {
                    e.setJoinMessage(ChatColor.DARK_PURPLE + "Mod" + ChatColor.GRAY + " | " + p.getName() + " hat den Server betreten.");
                } else {
                    String chatMessage = ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + p.getName() + " hat den Server betreten. " +
                            "(" + ChatColor.RED + "Rejoin" + ChatColor.GRAY + ")";

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Stegisagt.isMod(player.getUniqueId())) {
                            TextComponent message = new TextComponent(chatMessage);
                            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Revive")));
                            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/revive " + p.getName()));
                            player.spigot().sendMessage(message);
                        } else {
                            player.sendMessage(chatMessage);
                        }
                    }
                    //e.setJoinMessage(ChatColor.RED + "\u271E" + ChatColor.GRAY + " | " + p.getName() + " hat den Server betreten. (Rejoin)");
                    p.setGameMode(GameMode.SURVIVAL);
                }
                instance.rejoin(p);
            }

        }
    }

}
