package org.examples.fightmonsters.building;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Game.游戏开始;

import java.util.List;

import static org.bukkit.Material.GOLDEN_APPLE;
import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class 建筑初始化 {

    public void 钟生成(World world){
             Block block = world.getBlockAt(
             (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.x"),
             (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.y"),
             (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.z"));
             block.setType(Material.BELL);
        Location loc = new Location(world,
                (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.x"),
                ((int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.y")+1),
                (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.z"));
        ArmorStand armorStand = (ArmorStand)world.spawnEntity(loc,ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName((String) Fightmonsters.初始化列表.Yml("String","游戏钟生成.name"));
        armorStand.setCustomNameVisible(true); //设置名字可见*//*
        armorStand.setVisible(false);
        if (!(boolean)Fightmonsters.区门设置.Yml("boolean","打怪时设置.打怪时是否可以进入商店区")){
            if (区门设置.普通商店区是否打开){
                游戏开始.区门设置.开启商店区(world);
                for (Entity entity:world.getEntities()){
                    if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","打怪时设置.name"))){
                        entity.remove();
                    }
                }
            }else{
                for (Entity entity:world.getEntities()){
                    if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","打怪时设置.name"))){
                        entity.remove();
                    }
                }
                ItemStack PIGLINHEAD = new ItemStack(GOLDEN_APPLE);
                List<Double> list_普通商店区 = (List<Double>)Fightmonsters.区门设置.Yml("List","普通商店区.介绍.位置");
                double yam_ =  list_普通商店区.get(3);
                double pitch_ = list_普通商店区.get(4);
                Location loc_ = new Location(world,list_普通商店区.get(0),
                        list_普通商店区.get(1),
                        list_普通商店区.get(2),(float) yam_,(float)pitch_);
                ArmorStand armorStand_ = (ArmorStand)world.spawnEntity(loc_,ARMOR_STAND);
                armorStand_.setGravity(false);
                armorStand_.setInvulnerable(true);
                armorStand_.setCustomName((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"));
                armorStand_.setCustomNameVisible(true); //设置名字可见*//*
                armorStand_.setVisible(false);
                EntityEquipment st = armorStand_.getEquipment();
                st.setChestplate(PIGLINHEAD);
                st.setLeggings(PIGLINHEAD);
                st.setBoots(PIGLINHEAD);
            }
        }

    }
    public void 钟消失(World world)
    {
        Block block = world.getBlockAt(
                (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.x"),
                (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.y"),
                (int)Fightmonsters.初始化列表.Yml("int","游戏钟生成.z"));
        block.setType(Material.AIR);
        for (Entity entity:world.getEntities()) //消除此标题
        {
            if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"))){
                entity.remove();
            }
            if (entity.getName().equals((String) Fightmonsters.初始化列表.Yml("String","游戏钟生成.name"))){
                entity.remove();
            }
        }
        if (!(boolean)Fightmonsters.区门设置.Yml("boolean","打怪时设置.打怪时是否可以进入商店区"))
            new 区门设置().打怪开始禁止进入商店区(world);
    }



}
