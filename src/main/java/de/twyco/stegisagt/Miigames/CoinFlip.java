package de.twyco.stegisagt.Miigames;

import de.twyco.stegisagt.Items.Minigames.Coin;
import de.twyco.stegisagt.Stegisagt;
import de.twyco.stegisagt.Util.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CoinFlip {

    private static boolean head = false;
    private static long time = 1;
    private static int flips;
    private static int flipAnimation;
    private static boolean spinning;
    private static boolean automat = true;

    public static void setItemFrame(boolean head) {
        if(spinning){
            return;
        }
        Config config = Stegisagt.getInstance().getLocationsConfig();
        Location location = config.getFileConfiguration().getLocation("Coinflip.ItemFrame");
        assert location != null;
        World world = location.getWorld();
        assert world != null;
        boolean exists = false;
        GlowItemFrame glowItemFrame = null;
        for (Entity entity : world.getEntities()) {
            if (entity.getType().equals(EntityType.GLOW_ITEM_FRAME)) {
                if (entity.getLocation().equals(location)) {
                    glowItemFrame = (GlowItemFrame) entity;
                    exists = true;
                    break;
                }
            }
        }
        if (exists) {
            glowItemFrame.setItem(new Coin(head));
        } else {
            glowItemFrame = (GlowItemFrame) world.spawnEntity(location, EntityType.GLOW_ITEM_FRAME);
            glowItemFrame.setItem(new Coin(head));
        }
    }
/*
    public static void flipCoin(ArrayList<Player>... playerArrayLists) {
        ArrayList<Player> playerList = new ArrayList<>();
        for (ArrayList<Player> players : playerArrayLists) {
            playerList.addAll(players);
        }
        flipCoin(playerList);
    }
*/

    public static void flipCoin(Player... players) {
        ArrayList<Player> playerArrayList = new ArrayList<>();
        for (Player player : players){
            playerArrayList.add(player);
        }
        flipCoin(playerArrayList);
    }

    public static void flipCoin(ArrayList<Player> players) {
        if(spinning){
            return;
        }
        setSpinning(true);
        head = automat;
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        flips = (int) (Math.random() * range) + min;
        flip(players);
    }

    private static void flip(ArrayList<Player> players) {
        flipAnimation = 0;
        head = !head;
        if (flips <= 0) {
            spinEnd(players);
        } else {
            halfSpin(players);
        }
        flips--;
    }

    private static void halfSpin(ArrayList<Player> players) {
        flipAnimation++;
        if (head) {
            switch (flipAnimation) {
                case 1 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE001", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 2 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE002", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 3 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE003", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 4 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE004", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 5 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE005", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 6 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE006", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 7 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE007", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 8 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE008", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 9 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE009", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 10 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE010", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                default -> flip(players);
            }
        } else {
            switch (flipAnimation) {
                case 1 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE101", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 2 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE102", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 3 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE103", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 4 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE104", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 5 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE105", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 6 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE106", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 7 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE107", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 8 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE108", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 9 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE109", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                case 10 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE110", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> halfSpin(players), time);
                }
                default -> flip(players);
            }

        }
    }

    private static void spinEnd(ArrayList<Player> players) {
        flipAnimation++;
        if (head) {
            switch (flipAnimation) {
                case 1 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE001", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 2 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE002", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 3 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE003", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 4 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE004", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                default -> {
                    setSpinning(false);
                    for (Player player : players) {
                        player.sendTitle("", "\uE011", 0, 100, 0);
                    }
                }
            }
        } else {
            switch (flipAnimation) {
                case 1 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE101", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 2 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE102", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 3 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE103", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                case 4 -> {
                    for (Player player : players) {
                        player.sendTitle("", "\uE104", 0, 10, 0);
                    }
                    Bukkit.getScheduler().runTaskLater(Stegisagt.getInstance(), () -> spinEnd(players), time);
                }
                default -> {
                    setSpinning(false);
                    for (Player player : players) {
                        player.sendTitle("", "\uE111", 0, 100, 20);
                    }
                }
            }

        }
    }

    public static boolean isSpinning() {
        return spinning;
    }

    public static void setSpinning(boolean spinning) {
        CoinFlip.spinning = spinning;
    }

    public static boolean isAutomat() {
        return automat;
    }

    public static void setAutomat(boolean automat) {
        CoinFlip.automat = automat;
    }
}
