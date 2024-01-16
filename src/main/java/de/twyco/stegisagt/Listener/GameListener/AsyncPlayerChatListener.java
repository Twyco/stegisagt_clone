package de.twyco.stegisagt.Listener.GameListener;

import de.twyco.stegisagt.Stegisagt;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class AsyncPlayerChatListener implements Listener {

    private final HashMap<Player, String> lastPlayerMessage;

    public AsyncPlayerChatListener() {
        lastPlayerMessage = new HashMap<>();
    }

    @EventHandler
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Stegisagt.isMod(player.getUniqueId())) {
            event.setFormat(ChatColor.DARK_PURPLE + "Mod" + ChatColor.GRAY + " | " + player.getName() +
                    ChatColor.DARK_GRAY + " >> " + ChatColor.WHITE + event.getMessage());
        } else {
            if (event.getMessage().length() > 69 && !player.isOp()) {
                player.sendMessage(ChatColor.RED + "Deine Nachrichten dürfen maximal 69 Zeichen lang sein!");
                event.setCancelled(true);
                return;
            }
            if (lastPlayerMessage.getOrDefault(player, "").equalsIgnoreCase(event.getMessage())) {
                player.sendMessage(ChatColor.RED + "Du Wiederholst dich!");
                event.setCancelled(true);
                return;
            }
                event.setCancelled(true);
                String chatMessage = (Stegisagt.isDead(player.getUniqueId()) ? (ChatColor.RED + "\u271E" + ChatColor.GRAY + " | ") : "")
                        + (player.isOp() ? ChatColor.LIGHT_PURPLE : ChatColor.GRAY) + player.getName() +
                        ChatColor.DARK_GRAY + " >> " + ChatColor.WHITE + event.getMessage();
                if(player.isOp()){
                    chatMessage = chatMessage.replace('&' , '§');
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Stegisagt.isMod(p.getUniqueId())) {
                        TextComponent message = new TextComponent(chatMessage);
                        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new Text(Stegisagt.isAlive(player.getUniqueId()) ? "Kill" : "Revive")));
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                                Stegisagt.isAlive(player.getUniqueId()) ? ("/kill " + player.getName()) : ("/revive " + player.getName())));
                        p.spigot().sendMessage(message);
                    } else {
                        p.sendMessage(chatMessage);
                    }
                }
                Bukkit.getConsoleSender().sendMessage(chatMessage);


            lastPlayerMessage.put(player, event.getMessage());
            Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> lastPlayerMessage.remove(player, event.getMessage()), 20 * 20L);
        }

    }

}
