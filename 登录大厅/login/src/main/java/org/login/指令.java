package org.login;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class 指令 implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)){
            System.out.println("非玩家 禁止使用此指令!");
            return false;
        }
        Player player = (Player)commandSender;
        if (command.getName().equalsIgnoreCase("reg")) {
            if (玩家档案.零时保存已经注册的玩家.contains(player)){
                player.sendMessage(ChatColor.BLUE+"你小子不是注册了吗???");
                return false;
            }
            try {
                if (strings[0].equals(strings[1])) {
                    if (strings[0].length() >= 15) {
                        player.sendMessage(ChatColor.BLUE + "我的妈啊你的密码设置那么长,生怕别人偷你密码吗???<密码最大14>");
                        return false;
                    }
                    if(!validatePassword(strings[0])){
                        player.sendMessage(ChatColor.RED+ "密码必须是[英文,数字,符合]组合,必须是ASCII码的字符");
                        return false;
                    }
                    注册.注册class.注册玩家档案(player, strings[0]);
                    return true;
                }
                player.sendMessage(ChatColor.BLUE+"格式错误!!!!!");
                player.sendMessage(ChatColor.BLUE+"注册指令模板: /reg <密码> <和前面相同的密码>");
            }catch (Exception e){
                player.sendMessage(ChatColor.BLUE+"格式错误!!!!!");
                player.sendMessage(ChatColor.BLUE+"注册指令模板: /reg <密码> <和前面相同的密码>");
                return false;
            }
            return false;
        }

        if (command.getName().equalsIgnoreCase("l")||command.getName().equalsIgnoreCase("login")){
            if (玩家档案.零时保存的未注册玩家.contains(player)){
                player.sendMessage(ChatColor.BLUE+"卧槽你不注册,还想登录???");
                return false;
            }
            if (连接处理.玩家正在登录.contains(player)){
                player.sendMessage(ChatColor.BLUE+"你已经登录了,正在为你传送到指定服务器!");
                return false;
            }
           if (登录.登录class.登录(player,strings[0]))
           {
               连接处理.Sockets.向跨服端发送玩家已经登录(player.getName());
               连接处理.Sockets.跨服传送到Hub(player.getName());
               return true;
           }else{
               player.kickPlayer(ChatColor.RED+"\n"+"         密码不正确!         "+"\n"+
                                                  "    如果密码忘记请联系管理员    "+"\n"+
                                                  "    qq群: 958333627        ");
                return false;
           }
        }


        return false;
    }

    //查看是否是ASCII码
    public static boolean validatePassword(String str) {
        // 正则表达式，匹配英文字符、数字和常见符号
        String regex = "^[a-zA-Z0-9~!@#$%^&*()_+=-]*$";
        return str.matches(regex);
    }


}
