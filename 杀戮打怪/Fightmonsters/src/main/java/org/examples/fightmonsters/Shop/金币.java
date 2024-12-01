package org.examples.fightmonsters.Shop;

import org.bukkit.entity.Player;
import org.examples.fightmonsters.Setdata.记分板;

import java.util.HashMap;
import java.util.Map;

public class 金币 {
    public static Map<String,Integer> 金币 = new HashMap<>(); //<玩家名,金币数量>
    public static 金币 金币设置 = new 金币();
    public void 创建玩家金币档案(Player player){
        if (金币.containsKey(player.getName())){
            System.out.println("[Fightmonsters]该玩家"+player.getName()+"已经创建档案了!");
        }else{
            金币.put(player.getName(),0);
        }
    }
    public void 删除该文件档案(Player player)
    {
        if (金币.containsKey(player.getName())){
            金币.remove(player.getName());
        }else{
            System.out.println("[Fightmonsters]该玩家"+player.getName()+"不存在!");
        }
    }
    //可增可少
    public void add金币(Player player,int i){
        try{
            if (金币.containsKey(player.getName())){
                金币.put(player.getName(),金币.get(player.getName()) + i);
            }else{
                System.out.println("[金币类]该玩家不存在?"+player.getName());
            }
        }catch (Exception e){
            System.out.println("金币出现错误 !"+ e);
        }
    }
    public void 金币支付(Player player,int i)
    {
        if (金币.containsKey(player.getName())){
            this.add金币(player,-i);
        }else{
            System.out.println("[金币类]该文件不存在?");
        }
    }
    public void set金币(Player player,int i)
    {
        try{
            if (金币.containsKey(player.getName())){
                金币.put(player.getName(),i);
            }else{
                System.out.println("[金币类]该玩家不存在?"+player.getName());
            }
        }catch (Exception e){
            System.out.println("金币出现错误 !"+ e);
        }
    }
    public boolean 检测是否购买金币是否满足(Player player, int i)
    {
        if (金币.containsKey(player.getName())){
            int 金币set = 金币.get(player.getName()) - i;
            if (金币set < 0){
                return false;
            }
            return true;
        }else{
            System.out.println("容器没有玩家！！");
            return false;
        }

    }
}
