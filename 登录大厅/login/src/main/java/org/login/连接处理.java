package org.login;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class 连接处理 {
    public static 连接处理 Sockets = new 连接处理();
    public static List<Player> 玩家正在登录 = new ArrayList<>();
    // 传送请求 string = "[玩家名字] [服务器名字]"
    public void ServerSocket(String Commands){
        try {
            Socket socket = new Socket("127.0.0.1",25598);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(Commands.getBytes());
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e+"数据请求失败!");
        }
    }
    //开发中
    public void 跨服传送到Hub(String playername){
        Bukkit.getPlayer(playername).sendMessage(ChatColor.BLUE+"等待3秒传送---------*****------*****-------------------------");
        Bukkit.getPlayer(playername).sendMessage(ChatColor.BLUE+"等待3秒传送-------------*Hnckat*----(欢迎你)------------------");
        Bukkit.getPlayer(playername).sendMessage(ChatColor.BLUE+"等待3秒传送---------*****------*****-------------------------");
        玩家正在登录.add(Bukkit.getPlayer(playername));
        Bukkit.getScheduler().runTaskLater(Login.getPlugin(Login.class),bukkitTask -> {
            ServerSocket("ServerTp "+playername+" HUB");
            bukkitTask.cancel();
        },20*3);

    }

    public void 向跨服端发送玩家已经登录(String playerName){
        ServerSocket("ServerLogin "+playerName);
    }

}
