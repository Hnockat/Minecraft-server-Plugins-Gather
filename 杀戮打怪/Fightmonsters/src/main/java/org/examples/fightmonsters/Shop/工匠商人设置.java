package org.examples.fightmonsters.Shop;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.prop.浮空文字;
import org.examples.fightmonsters.工匠.工匠修复;
import org.examples.fightmonsters.工匠.工匠文件处理;

import java.util.List;

public class 工匠商人设置 {
    public static 工匠商人设置 工匠商人设置class = new 工匠商人设置();
    public Inventory 工匠商人 = Bukkit.createInventory(null,6*9,"工匠商店菜单_home");
    public static boolean 是否有人正在使用该工匠 = false;
    public void 初始化商人(World world){
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
        villager.setCustomName((String)Fightmonsters.商人.Yml("String","工匠商人设置.name"));
        villager.setCustomNameVisible(true); //设置名字可见*//*
    }
    public void 初始化箱子标签(World world){
        List<String> Loc_list = (List<String>)工匠文件处理.工匠商店.Yml("List","物品箱.位置");
        Location loc = new Location(world,Double.parseDouble(Loc_list.get(0)),
                Double.parseDouble(Loc_list.get(1)),
                        Double.parseDouble(Loc_list.get(2)),
                                Float.parseFloat(Loc_list.get(3)),Float.parseFloat(Loc_list.get(4)));
        浮空文字.classe文字.设置浮空文字((String) 工匠文件处理.工匠商店.Yml("String","物品箱.浮空文字"),loc,false);
    }

    public void 初始化商人主菜单(World world){
        ItemStack 玻璃板 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for(int i = 0;i<9;i++){
            工匠商人.setItem(i,玻璃板);
            工匠商人.setItem(i+45,玻璃板);
        }
        for (int i = 1;i != 5; i++){
            工匠商人.setItem(i*9,玻璃板);
            工匠商人.setItem(i*9+8,玻璃板);
        }

        ItemStack 工匠升级 = new ItemStack(Material.CRAFTER);
        ItemMeta 工匠升级Meta = 工匠升级.getItemMeta();
        工匠升级Meta.setDisplayName((String) 工匠文件处理.工匠商店.Yml("String","工匠升级.name"));
        工匠升级Meta.setLore((List<String>) 工匠文件处理.工匠商店.Yml("List","工匠升级.Lore"));
        工匠升级.setItemMeta(工匠升级Meta);
        工匠商人.setItem(20,工匠升级);

        ItemStack 工匠修复 = new ItemStack(Material.ANVIL);
        ItemMeta 工匠修复Meta = 工匠修复.getItemMeta();
        工匠修复Meta.setDisplayName((String) 工匠文件处理.工匠商店.Yml("String","工匠修复.name"));
        工匠修复Meta.setLore((List<String>) 工匠文件处理.工匠商店.Yml("List","工匠修复.Lore"));
        工匠修复.setItemMeta(工匠修复Meta);
        工匠商人.setItem(24,工匠修复);




    }

}
