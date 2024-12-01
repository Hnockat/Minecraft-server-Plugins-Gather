package org.examples.fightmonsters.Setdata;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Game.游戏开始;
import org.examples.fightmonsters.Shop.金币;

import java.util.HashMap;

public class 记分板 {
    public static 记分板 记分板 = new 记分板();
    public static HashMap<String,Integer> SchedulerId = new HashMap<>(); //<玩家名字,线程id>
    public void 创建游戏记分板(Player player)
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Team team = scoreboard.registerNewTeam("sb_");
        team.addEntry("§1");
        Team team1 = scoreboard.registerNewTeam("sb_1");
        team1.addEntry("§2");

        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY,ChatColor.GOLD+"Hnockat服务器",RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = objective.getScore("§1");
        score.setScore(2);
        Score score1 = objective.getScore("§2");
        score1.setScore(1);
        Score score2 = objective.getScore(ChatColor.GOLD+"qq群:958333627");
        score2.setScore(0);

        team1.setPrefix(ChatColor.GREEN+"当前层数:"+游戏开始.当前层数+"."+游戏开始.当前层数的副本数);
        team.setPrefix(ChatColor.GREEN +"当前金币: "+ 金币.金币设置.金币.get(player.getName()));
        player.setScoreboard(scoreboard);
        Bukkit.getScheduler().runTaskTimer(Fightmonsters.getPlugin(Fightmonsters.class), bukkitTask -> {
            if (!SchedulerId.containsKey(player.getName())){
                SchedulerId.put(player.getName(),bukkitTask.getTaskId());
            }
            if (!team1.getPrefix().equals(ChatColor.GREEN+"当前层数:"+游戏开始.当前层数+"."+游戏开始.当前层数的副本数)){
                team1.setPrefix(ChatColor.GREEN+"当前层数:"+游戏开始.当前层数+"."+游戏开始.当前层数的副本数);
            }
            if (!team.getPrefix().equals(ChatColor.GREEN +"当前金币: "+ 金币.金币设置.金币.get(player.getName()))) {
                team.setPrefix(ChatColor.GREEN + "当前金币: " + 金币.金币设置.金币.get(player.getName()));
            }
        }, 0, 30);
    }

    public void 创建服务器介绍记分板(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY,ChatColor.GOLD+"Hnockat服务器",RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = objective.getScore("[杀戮打怪]0.2测试版");
        score.setScore(1);
        Score score1 = objective.getScore(ChatColor.GOLD+"qq群:958333627");
        score1.setScore(0);
        player.setScoreboard(scoreboard);
    }

    public void 消除该玩家线程(Player player){
        if (SchedulerId.containsKey(player.getName())){
            Bukkit.getScheduler().cancelTask(SchedulerId.get(player.getName()));
            SchedulerId.remove(player.getName());
        }else{
            System.out.println("消除该玩家线程 没有该玩家档案: "+player.getName());
        }
    }
}
