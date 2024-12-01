package org.login;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.List;

public class 监听器 implements Listener {
    public HashMap<Player,Integer> 线程id = new HashMap<>();
    public Location 初始化位置(Player player){
        List<Double> List = (List<Double>) Login.登录大厅.Yml("List","登录大厅");
        return new Location(player.getWorld(),List.get(0),List.get(1),List.get(2));
    }

    //玩家加入游戏
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.teleport(初始化位置(player));
        boolean 玩家档案是否为新 = 玩家档案.玩家档案class.查顺档案是否为新(player);
        Bukkit.getScheduler().runTaskLater(Login.getPlugin(Login.class),bukkitTask1 -> { //登录超时
            if (player.isOnline()){
                player.kickPlayer(ChatColor.RED+"登录超时!!!");
            }
            bukkitTask1.cancel();
        },20*25);
        Bukkit.getScheduler().runTaskTimerAsynchronously(Login.getPlugin(Login.class),bukkitTask ->  {
            if (!线程id.containsKey(player)){
                线程id.put(player,bukkitTask.getTaskId());
            }
            if (玩家档案是否为新){
                player.sendMessage(ChatColor.RED+"检测到该账号未注册请注册账号");
                player.sendMessage(ChatColor.RED+"注册指令模板: /reg <密码> <密码>");
            }else{
                player.sendMessage(ChatColor.GREEN+"您好"+player.getName()+"欢迎游玩硝酸服");
                player.sendMessage(ChatColor.GREEN+"请您登录账号谢谢配合");
                player.sendMessage(ChatColor.GREEN+"登录指令模板: /l <密码>");;
            }
        },0,60L);
    }
    @EventHandler
    public void 玩家退出游戏(PlayerQuitEvent event){
        Bukkit.getScheduler().cancelTask(线程id.get(event.getPlayer()));
        if (玩家档案.零时保存已经注册的玩家.contains(event.getPlayer())){
            玩家档案.零时保存已经注册的玩家.remove(event.getPlayer());
        }
        if (玩家档案.零时保存的未注册玩家.contains(event.getPlayer())){
            玩家档案.零时保存的未注册玩家.remove(event.getPlayer());
        }
        if (连接处理.玩家正在登录.contains(event.getPlayer())){
            连接处理.玩家正在登录.remove(event.getPlayer());
        }
    }
    //玩家死亡事件
    @EventHandler
    public void 玩家死亡事件(PlayerDeathEvent event){
        event.setCancelled(true);
    }

}
