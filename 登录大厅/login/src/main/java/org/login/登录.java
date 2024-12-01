package org.login;

import org.bukkit.entity.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class 登录 {
    public static 登录 登录class = new 登录();
    public boolean 登录(Player player,String 密码)
    {

        String 玩家档案路径 = 玩家档案.玩家档案class.返回玩家档案文件的字符串();
        玩家档案路径 = 玩家档案路径+"\\"+player.getName()+".txt";
        try {
            FileReader FR = new FileReader(玩家档案路径);
            int r = 0;
            int 次数 = 0;
            String arr = "";
            while ((r = FR.read()) != -1)
            {
                arr += (char)r;
                if ((char) r == '\"'){
                    次数++;
                    if (次数 == 2){
                        break;
                    }
                }
            }
            FR.close(); //关流
            arr = arr.replace("password: ","");
            arr = arr.replace("\"","");
            if (arr.equals(密码)){
                return true;
            }
                return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
