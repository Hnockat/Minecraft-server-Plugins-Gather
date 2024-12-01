package org.werewolf;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 记分板 {
    public static 记分板 记分板 = new 记分板();
    public static HashMap<World,Integer> GameId = new HashMap<>(); //<玩家名字,线程id>
    public void 创建等待记分板(World world)
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, ChatColor.BLUE+"Hnockat服务器", RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        List<String> list_Score = (List<String>)Werewolf.记分板.Yml("List","玩家加入某个地图创建记分板.Score");
        int 至少人数 = Integer.parseInt(游戏管理器.游戏管理器class.获取至少人数(world));
        for (int i = 0;i < list_Score.size();i++){
            Team team = scoreboard.registerNewTeam("Score_"+i);
            team.addEntry("§"+i);
            team.setPrefix(替换标签(list_Score.get(i),world,"null"));
            Score score = objective.getScore("§"+i);
            score.setScore((list_Score.size() - 1) - i);
        }
        Bukkit.getScheduler().runTaskTimer(Werewolf.getPlugin(Werewolf.class), bukkitTask -> {
            if (world.getPlayers().size() == 0){
                bukkitTask.cancel();
            }
            if (!GameId.containsKey(world)) {
                GameId.put(world, bukkitTask.getTaskId());
            }
            if (world.getPlayers().size() >= 至少人数){
                游戏管理器.游戏管理器class.开启一个游戏(world); //开启游戏!!!
                bukkitTask.cancel();
            }
            for (int i =0; i<list_Score.size();i++){
                scoreboard.getTeam("Score_"+i).setPrefix(替换标签(list_Score.get(i),world,"null"));
            }
            for (Player player:world.getPlayers()){
                player.setScoreboard(scoreboard);

            }
        }, 0, 60);
    }
    public void 游戏开始的记分板(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, ChatColor.BLUE+"Hnockat服务器", RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        List<String> list_Score = (List<String>)Werewolf.记分板.Yml("List","玩家开始游戏处理.Score");
        for (int i = 0;i < list_Score.size();i++){
            Team team = scoreboard.registerNewTeam("Score_"+i);
            team.addEntry("§"+i);
            team.setPrefix(替换标签(list_Score.get(i),player.getWorld(),player.getName()));
            Score score = objective.getScore("§"+i);
            score.setScore((list_Score.size() - 1) - i);
        }
        player.setScoreboard(scoreboard);
    }


    public String 替换标签(String string,World world,String player){
        if (string.contains("%Work%")){
            if (!player.equalsIgnoreCase("null")){
                return string.replace("%Work%",游戏管理器.GameList.get(world).预言该玩家职业(Bukkit.getPlayer(player)));
            }
            return "null";
        }
        if (string.contains("%Players%")){
            return string.replace("%Players%",world.getPlayers().size()+"");
        }
        if (string.contains("%Map%")){
            return string.replace("%Map%",游戏管理器.游戏管理器class.获取地图名字(world));
        }
        if (string.contains("%GameMINPlayer%")){
            return string.replace("%GameMINPlayer%",游戏管理器.游戏管理器class.获取至少人数(world));
        }
        return string;
    }
    public void 创建服务器介绍记分板(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY,ChatColor.BLUE+"Hnockat服务器",RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score_1 = objective.getScore(" ");
        score_1.setScore(3);
        Score score_2 = objective.getScore("[狼人杀重构版]0.2测试版");
        score_2.setScore(2);
        Score score_3 = objective.getScore("");
        score_3.setScore(1);
        Score score_4 = objective.getScore(ChatColor.BLUE+"qq群:958333627");
        score_4.setScore(0);
        player.setScoreboard(scoreboard);
    }

}
