package org.examples.fightmonsters.工匠;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Shop.工匠商人设置;

public class 工匠打造时刻 {
    private int Time_Ticks = 0;
    public void 正在打造装备(int Time_Tick, World world, ItemStack 完成品){
        //删除原本商人
        this.Time_Ticks = 0; //该物品打造需要的时间?
        for (Entity entity: world.getEntities()){
            if (entity.getName().equals((String)Fightmonsters.商人.Yml("String","工匠商人设置.name"))){
                entity.remove();
                break;
            }
        }
        //增加打造商人
        this.工匠打造装备中(world);
        //更新位置
        Location loc = new Location(world
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.x")
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.y")
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.z"));
        //线程异步
        Bukkit.getScheduler().runTaskTimer(Fightmonsters.getInstance(),bukkit->{
            System.out.println("1");
            if (Time_Ticks >= Time_Tick){
                for (Entity entity: world.getEntities()){
                    if (entity.getName().equals(ChatColor.RED+"该工匠正在打造装备")){
                        entity.remove();
                        break;
                    }
                }
                工匠商人设置.工匠商人设置class.初始化商人(world); //初始化商人重回
                //如果箱子满了 进行处理
                if (工匠打造箱子.打造箱子.firstEmpty() != -1){
                    工匠打造箱子.打造箱子.addItem(完成品);
                }else{
                    world.dropItemNaturally(loc,完成品);
                }
                bukkit.cancel();//打造成功
            }
            if ((Time_Ticks % 40) == 0){
                world.playSound(loc, Sound.BLOCK_ANVIL_USE,1,1);
            }
            //显示该进度的进行
            Time_Ticks+= 20;
        },20,20);
    }
    private void 显示进度条(int ALLTime_Tick){

    }
    private void 工匠打造装备中(World world){
        double yaw = (double) Fightmonsters.商人.Yml("double","工匠商人设置.yaw");
        double pitch = (double) Fightmonsters.商人.Yml("double","工匠商人设置.pitch");
        Location loc = new Location(world
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.x")
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.y")
                ,(double) Fightmonsters.商人.Yml("double","工匠商人设置.z")
                ,(float) yaw
                ,(float) pitch
        );
        Villager villager = (Villager)world.spawnEntity(loc, EntityType.VILLAGER);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setCustomName(ChatColor.RED+"该工匠正在打造装备");
        villager.setCustomNameVisible(true); //设置名字可见*//*
    }

}
