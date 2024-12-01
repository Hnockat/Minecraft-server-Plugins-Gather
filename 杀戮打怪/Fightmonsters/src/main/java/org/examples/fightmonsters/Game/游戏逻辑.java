package org.examples.fightmonsters.Game;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.examples.fightmonsters.Fightmonsters;

import java.util.List;

public class 游戏逻辑 {
    public static boolean 是否可以到下一场 = false;
    public void 怪物刷新_指令执行(int time, World world){
        if (time == 10){
            游戏开始.当前是否可以刷怪 = true;
           游戏开始.怪物刷新.怪物获取();
        }
        游戏开始.怪物刷新.开始刷怪();
        下一场(world);
    }
    public void 下一场(World world){
        if (是否可以到下一场 == false) return;
            for (Entity ent : world.getEntities()) {
                if (!设置保护枚举.isProtectedEntityType(ent.getType().name())) return;
            }
        List<Integer> list = (List<Integer>) Fightmonsters.各层关卡数.Yml("List","List");
        for (int i :list)
        {
            List<Integer> list_ = (List<Integer>) Fightmonsters.各层关卡数.Yml("List",i+"");
            if (list_.contains(游戏开始.当前层数)){
                if (游戏开始.当前层数的副本数 < i)
                {
                    游戏开始.当前层数的副本数++;
                    游戏开始.times = 4;
                    for (Player p: world.getPlayers()){
                        p.sendTitle(ChatColor.GREEN + ""+游戏开始.当前层数+"."+游戏开始.当前层数的副本数, ChatColor.GOLD+"决斗场正在蓄力", 10, 100, 20);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    }
                    是否可以到下一场 = false;
                    return;
                }
                else
                {
                    int end_size = 游戏开始.当前层数 + 1;
                    if (end_size == (int)Fightmonsters.各层关卡数.Yml("int","end")){
                       new 游戏结束().游戏结束(world);
                       return;
                    }
                    //等于后 执行操作
                    for (Player p: world.getPlayers()){
                        p.sendTitle(ChatColor.GREEN + "游戏中场暂停", "点击钟启动下一层", 10, 60, 20);
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    }
                    是否可以到下一场 = false;
                    游戏开始.墙关闭 = true;
                    return;
                }
            }
        }
    }


}
