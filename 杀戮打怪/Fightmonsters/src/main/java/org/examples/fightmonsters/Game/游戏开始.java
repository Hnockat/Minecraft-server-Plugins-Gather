package org.examples.fightmonsters.Game;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Setdata.记分板;
import org.examples.fightmonsters.building.区门设置;
import org.examples.fightmonsters.building.墙设置;
import org.examples.fightmonsters.building.建筑初始化;
import org.examples.fightmonsters.prop.垃圾处理箱;

public class 游戏开始 {
    public static int times = 5; //游戏处理时间
    public static int 主线程循环时间 = 12;//默认12
    public static boolean 当前是否可以刷怪 = false;
    public static boolean 游戏是否开始 = false;
    public static boolean 当前主线程是否启动 = false;
    public static boolean 墙关闭 = false;
    public static int 当前层数 = 0;
    public static int 当前层数的副本数 = 0;
    public static 墙设置 wallset = new 墙设置();
    public static 怪物刷新 怪物刷新 = new 怪物刷新();
    public static 游戏逻辑 游戏逻辑 = new 游戏逻辑();
    public static 建筑初始化 建筑初始化 = new 建筑初始化();
    public static 区门设置 区门设置 = new 区门设置();
    public 游戏开始() {
        World world = Bukkit.getWorld("world");
        org.examples.fightmonsters.building.区门设置.加载普通商店区容器();
        org.examples.fightmonsters.building.区门设置.加载工匠商店区容器();
        org.examples.fightmonsters.building.区门设置.加载水族馆区容器();
        垃圾处理箱.垃圾处理箱_class.垃圾处理箱初始化(world);
        for (Player player : world.getPlayers()) {
            记分板.记分板.创建游戏记分板(player);
            player.setHealth(20);
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.setLevel(0);
            player.getActivePotionEffects();
            if (!player.getActivePotionEffects().isEmpty()) { //移除玩家药水效果
                for (PotionEffect potionEffect : player.getActivePotionEffects()) {
                    player.removePotionEffect(potionEffect.getType());
                }
            }
            player.teleport(Fightmonsters.返回游戏开始处());
            //游戏正式开始
        }
        new 区门设置().区门设置生成实体(world);
        Bukkit.getScheduler().runTaskAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class), bukkitTask -> {
            Bukkit.getScheduler().runTaskTimer(Fightmonsters.getPlugin(Fightmonsters.class), bukkitTask1 -> {
                for (Player player1 : world.getPlayers()) {
                    player1.sendTitle(ChatColor.RED + "" + times, " ", 10, 60, 20);
                    player1.getWorld().playSound(player1.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
                }
                times--;
                if (times == 0) {
                    当前主线程是否启动 = true;
                    游戏是否开始 = true;
                    当前层数 = 1;
                    当前层数的副本数 = 1;
                    for (Player p: world.getPlayers()){ //倒计时
                        p.sendTitle(net.md_5.bungee.api.ChatColor.GREEN + ""+游戏开始.当前层数+"."+游戏开始.当前层数的副本数, net.md_5.bungee.api.ChatColor.GOLD+"决斗场正在蓄力", 10, 100, 20);
                        p.getWorld().playSound(p, Sound.ENTITY_PLAYER_LEVELUP,1,1);
                    }
                    for (Entity entity:world.getEntities()){ //删除初始化区门
                        if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"))){
                            entity.remove();
                        }
                    }
                    区门设置.打怪开始禁止进入商店区(world);
                    bukkitTask1.cancel();
                    bukkitTask.cancel();
                }
            }, 10, 20);
        });
        //此线程为主线程 ! ! !
        Bukkit.getScheduler().runTaskTimer(Fightmonsters.getPlugin(Fightmonsters.class), bukkitTask -> {
            if (当前主线程是否启动 == false) return;
            wallset.墙打开(times,world); //执行墙打开操作
            游戏逻辑.怪物刷新_指令执行(times,world); //怪物刷新操作
            wallset.墙关闭(times,world);
            times++;
        }, 20*8, 主线程循环时间);
    }//对该区域内所有玩家进行处理
}
