package org.gunPvP;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
public class 世界初始化 {
    public void 初始化世界(){
        for (String str : GunPvP.地图Yml.获取该节点的所有子节点名字集合("WorldName")){
            Bukkit.createWorld(new WorldCreator(str));
        }
    }

    public void 重载该世界(){

    }

}
