package org.login;

import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class 玩家档案 {
    public static 玩家档案 玩家档案class = new 玩家档案();
    public static List<Player> 零时保存的未注册玩家 = new ArrayList<>();
    public static List<Player> 零时保存已经注册的玩家 = new ArrayList<>();
    public File 返回玩家档案文件(){
        return new File((String)Login.登录大厅.Yml("String","玩家密码路径"));
    }
    public String 返回玩家档案文件的字符串(){
        return (String)Login.登录大厅.Yml("String","玩家密码路径");// STOPSHIP: 2024/9/13
    }
    //新档案将创建档案
    public boolean 查顺档案是否为新(Player player){
        if (零时保存的未注册玩家.contains(player)){
            return true;
        }
        String players = player.getName()+".txt";
        File file = 返回玩家档案文件();
        for (String PlayerName:file.list()){
            if (players.equals(PlayerName)){
                零时保存已经注册的玩家.add(player);
                return false;
            }
        }
        零时保存的未注册玩家.add(player);
        return true;
    }

}
