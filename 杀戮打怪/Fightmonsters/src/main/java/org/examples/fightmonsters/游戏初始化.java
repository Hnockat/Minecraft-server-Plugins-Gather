package org.examples.fightmonsters;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class 游戏初始化 {
   public 游戏初始化(){
        World world = Bukkit.getWorld("world");
        //游戏开始设置!
       double yaw =  (double)Fightmonsters.初始化列表.Yml("double","游戏开始npc.yaw");
       double pitch = (double) Fightmonsters.初始化列表.Yml("double","游戏开始npc.pitch");
        Location loc = new Location(Bukkit.getWorld("world")
                , (double)Fightmonsters.初始化列表.Yml("double","游戏开始npc.x")
                ,(double)Fightmonsters.初始化列表.Yml("double","游戏开始npc.y")
                ,(double)Fightmonsters.初始化列表.Yml("double","游戏开始npc.z")
                ,(float) yaw
                ,(float) pitch);
        for (Entity entity:world.getEntities()){ //清除该实体
            if (!entity.getType().equals(EntityType.PLAYER)){
                entity.remove();
            }
        }
        Villager villager = (Villager)world.spawnEntity(loc, EntityType.VILLAGER);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setCustomName((String)Fightmonsters.初始化列表.Yml("String","游戏开始npc.name"));
        villager.setCustomNameVisible(true); //设置名字可见*//*
    }




}
