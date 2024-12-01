package org.examples.hub.ScoreboardSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.examples.hub.Hub;
import java.util.HashMap;
import java.util.List;

public class 记分板 {
    public static 记分板 记分板class = new 记分板();
    public static HashMap<String,Integer> SchedulerId = new HashMap<>(); //<玩家名字,线程id>
    public void 创建玩家记分板主大厅(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //初始化team条例
        Objective objective = scoreboard.registerNewObjective("sidebar", Criteria.DUMMY, (String) Hub.记分板.Yml("String","MainhallScoreboard.Title"), RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR); //设置为侧边栏
        player.setScoreboard(scoreboard);
        List<String> listText = (List<String>)Hub.记分板.Yml("List","MainhallScoreboard.Text");
        for (int i = 0;i < listText.size();i++){
            Team team = scoreboard.registerNewTeam("Score_"+i);
            team.addEntry("§"+i);
            team.setPrefix(listText.get(i));
            Score score = objective.getScore("§"+i);
            score.setScore(i);
        }

        /*Bukkit.getScheduler().runTaskTimer(Hub.getPlugin(Hub.class), bukkitTask -> {

        }, 0, 30);*/

    }


}
