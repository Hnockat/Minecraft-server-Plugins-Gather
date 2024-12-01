package org.login;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class 注册 {
    public static 注册 注册class = new 注册();

    /*
    *  password: "密码"
    *
    *
    * */
    public void 注册玩家档案(Player player,String 密码){
        玩家档案.零时保存已经注册的玩家.add(player); //防止玩家持续注册
        
        String 玩家档案路径 = 玩家档案.玩家档案class.返回玩家档案文件的字符串();
        玩家档案路径 = 玩家档案路径+"\\"+player.getName()+".txt";
        try {
            FileWriter writer = new FileWriter(玩家档案路径,true);
            writer.write("password: "+"\""+密码+"\"");
            writer.close();
            连接处理.Sockets.向跨服端发送玩家已经登录(player.getName());
            连接处理.Sockets.跨服传送到Hub(player.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
