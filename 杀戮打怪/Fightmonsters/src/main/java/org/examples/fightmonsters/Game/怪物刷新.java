package org.examples.fightmonsters.Game;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.examples.fightmonsters.Fightmonsters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class
怪物刷新 {
    public static List<String> 怪物指令列表_left = new ArrayList<>();
    public static List<String> 怪物指令列表_right = new ArrayList<>();
   public static CommandSender commandSender = Bukkit.getConsoleSender();

    public void 开始刷怪(){
        if (游戏开始.当前是否可以刷怪 == false){
            return;
        }
        if (Fightmonsters.左右门设置注册.contains_List_value("List",游戏开始.当前层数)){
            if (!怪物指令列表_left.isEmpty()){
                Bukkit.dispatchCommand(commandSender,怪物指令列表_left.get(0));
                怪物指令列表_left.remove(0);

            }
            if (!怪物指令列表_right.isEmpty()){
                Bukkit.dispatchCommand(commandSender,怪物指令列表_right.get(0));
                怪物指令列表_right.remove(0);
            }
            if (怪物指令列表_left.isEmpty() && 怪物指令列表_right.isEmpty()){
                游戏开始.当前是否可以刷怪 = false;
                游戏逻辑.是否可以到下一场 = true;
            }
        }else{
            if (!怪物指令列表_left.isEmpty()){
                Bukkit.dispatchCommand(commandSender,怪物指令列表_left.get(0));
                怪物指令列表_left.remove(0);
            }
            if (怪物指令列表_left.isEmpty() && 怪物指令列表_right.isEmpty()){
                游戏开始.当前是否可以刷怪 = false;
                游戏逻辑.是否可以到下一场 = true;
            }
        }
    }
    //此结束怪物刷新 并执行墙关闭等操作

    public void 怪物获取()
    {
        if (Fightmonsters.左右门设置注册.contains_List_value("List",游戏开始.当前层数)){
            File lift = new File(Fightmonsters.getPlugin(Fightmonsters.class).getDataFolder(), "monster\\"+游戏开始.当前层数+"\\"+"left"+"\\"+游戏开始.当前层数的副本数+".txt");
            File right = new File(Fightmonsters.getPlugin(Fightmonsters.class).getDataFolder(), "monster\\"+游戏开始.当前层数+"\\"+"right"+"\\"+游戏开始.当前层数的副本数+".txt");
            try{
                FileReader file_lift = new FileReader(lift);
                FileReader file_right = new FileReader(right);
                int len;
                String getString = "";
                //lift
                while((len = file_lift.read() ) != -1){
                    if ((char)len == '\r'){
                        怪物指令列表_left.add(getString);
                        getString = "";
                        continue;
                    }
                    if ((char)len == '\n'){
                        continue;
                    }
                    getString += (char)len;
                }
                怪物指令列表_left.add(getString);
                getString = "";
                //right
                while((len = file_right.read() ) != -1){
                    if ((char)len == '\r'){
                        怪物指令列表_right.add(getString);
                        getString = "";
                        continue;
                    }
                    if ((char)len == '\n'){
                        continue;
                    }
                    getString += (char)len;
                }
                怪物指令列表_right.add(getString);
                file_lift.close();
            }catch(IOException e){
                throw new RuntimeException(e+"错误抛出 ");
            }
        }else{
            File outFile = new File(Fightmonsters.getPlugin(Fightmonsters.class).getDataFolder(), "monster\\"+游戏开始.当前层数+"\\"+游戏开始.当前层数的副本数+".txt");
            try {
                FileReader file = new FileReader(outFile);
                int len;
                String get = "";
                while ((len = file.read() ) != -1){
                    if ((char)len == '\r'){
                        怪物指令列表_left.add(get);
                        get = "";
                        continue;
                    }
                    if ((char)len == '\n'){
                        continue;
                    }
                    get += (char)len;
                }
                怪物指令列表_left.add(get);
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e+"错误抛出");
            }
        }

    }


}

