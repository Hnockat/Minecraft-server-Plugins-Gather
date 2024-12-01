package org.werewolf;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 职业 implements Listener {
    public static Map<Player,Integer> 线程id = new HashMap<>();
    public static 职业 职业class = new 职业();
    public void 平民(Player player){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Werewolf.getPlugin(Werewolf.class),bukkitTask -> {
            if (!线程id.containsKey(player)){
                线程id.put(player,bukkitTask.getTaskId());
            }
            if (player.getGameMode().equals(GameMode.SPECTATOR)){
                bukkitTask.cancel();
            }
            if (player.getInventory().contains(Material.GOLD_INGOT)){
                for (ItemStack itemStack:player.getInventory().getContents()){
                    if (itemStack.getType().equals(Material.GOLD_INGOT)){
                        if (itemStack.getAmount() >= 10){
                            if (player.getInventory().contains(Material.BOW)){
                                player.getInventory().addItem(ItemStack.of(Material.ARROW));
                            }else{
                                player.getInventory().addItem(ItemStack.of(Material.BOW));
                            }
                            if (itemStack.getAmount() == 10){
                                player.getInventory().remove(itemStack);
                            }else{
                                itemStack.setAmount(itemStack.getAmount() - 10);
                            }
                        }
                        return;
                    }
                    //for end
                }
            }
        },0,20);
    }

    public void 狼人(Player player){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Werewolf.getPlugin(Werewolf.class),bukkitTask -> {
            if (!线程id.containsKey(player)){
                线程id.put(player,bukkitTask.getTaskId());
            }
            if (player.getGameMode().equals(GameMode.SPECTATOR)){
                bukkitTask.cancel();
            }
            if (player.getInventory().contains(Material.GOLD_INGOT)){
                for (ItemStack itemStack:player.getInventory().getContents()){
                    if (itemStack.getType().equals(Material.GOLD_INGOT)){
                        if (itemStack.getAmount() >= 10){
                            if (player.getInventory().contains(Material.BOW)){
                                player.getInventory().addItem(ItemStack.of(Material.ARROW));
                            }else{
                                player.getInventory().addItem(ItemStack.of(Material.BOW));
                            }
                            if (itemStack.getAmount() == 10){
                                player.getInventory().remove(itemStack);
                            }else{
                                itemStack.setAmount(itemStack.getAmount() - 10);
                            }
                        }
                        return;
                    }
                    //for end
                }
            }
        },0,20);
    }
    private static List<Player> 侦探申请箭 = new ArrayList<>();

    public void 侦探(Player player){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Werewolf.getPlugin(Werewolf.class),bukkitTask -> {
            if (!线程id.containsKey(player)){
                线程id.put(player,bukkitTask.getTaskId());
            }
            if (player.getGameMode().equals(GameMode.SPECTATOR)){
                bukkitTask.cancel();
            }
            if (!player.getInventory().contains(Material.ARROW)){
                if (侦探申请箭.contains(player)){
                    return;
                }
                侦探申请箭.add(player);
                player.sendMessage(ChatColor.RED+"等待3秒获取箭");
                Bukkit.getScheduler().runTaskLater(Werewolf.getPlugin(Werewolf.class), () ->
                {
                    player.getInventory().addItem(ItemStack.of(Material.ARROW));
                    侦探申请箭.remove(player);
                }, 20*3);
            }
        },0,20);
    }
    public void 预言家(Player player){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Werewolf.getPlugin(Werewolf.class),bukkitTask -> {
            if (!线程id.containsKey(player)){
                线程id.put(player,bukkitTask.getTaskId());
            }
            if (player.getGameMode().equals(GameMode.SPECTATOR)){
                bukkitTask.cancel();
            }
            if (player.getInventory().contains(Material.GOLD_INGOT)){
                for (ItemStack itemStack:player.getInventory().getContents()){
                    if (itemStack.getType().equals(Material.GOLD_INGOT)){
                        if (itemStack.getAmount() >= 10){
                            if (player.getInventory().contains(Material.BOW)){
                                player.getInventory().addItem(ItemStack.of(Material.ARROW));
                            }else{
                                player.getInventory().addItem(ItemStack.of(Material.BOW));
                            }
                            if (itemStack.getAmount() == 10){
                                player.getInventory().remove(itemStack);
                            }else{
                                itemStack.setAmount(itemStack.getAmount() - 10);
                            }
                        }
                        return;
                    }
                    //for end
                }
            }
        },0,20);
    }



}
