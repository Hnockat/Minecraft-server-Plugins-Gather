package org.examples.hub.跑酷;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.examples.hub.Hub;
import org.examples.hub.数据.玩家展示;
public class 计时器 {
    private Player player;
    public int 当前记录点 = 0;
    public int 总共记录点 = 0;
    private int 线程id = 0;
    private float time = 0;

    计时器(Player player,int 总共记录点){
        this.player = player;
        this.总共记录点 = 总共记录点;
    }


    public void Run(){
        Bukkit.getScheduler().runTaskTimer(Hub.getInstance(),bukkitTask -> {
            if (线程id == 0){
                线程id = bukkitTask.getTaskId();
            }
            if (player.isOnline() == false){
                bukkitTask.cancel();
            }
            time += 0.2;
            玩家展示.玩家展示实例化.玩家展示下标("跑酷时间: "+time,player, ChatColor.DARK_BLUE);
        },0,4);//精度0.2秒
    }
    public void 结束线程(){
         Bukkit.getScheduler().cancelTask(线程id);
    }
    public void 记录点增加(){

    }




}
