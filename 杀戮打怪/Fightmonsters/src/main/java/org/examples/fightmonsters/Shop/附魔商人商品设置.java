package org.examples.fightmonsters.Shop;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.fightmonsters.Fightmonsters;

import java.util.ArrayList;
import java.util.List;

public class 附魔商人商品设置 {
    public static 附魔商人商品设置 附魔商人商品设置class = new 附魔商人商品设置();
    public static Inventory 附魔商人菜单_home = Bukkit.createInventory(null,9*6,"附魔商人菜单_home");
    public static Inventory 附魔商人菜单_00 = Bukkit.createInventory(null,9*6,"附魔商人菜单_00");
    public void 初始化商人(World world)
    {
        double yaw = (double) Fightmonsters.商人.Yml("double","附魔商店设置.yaw");
        double pitch = (double) Fightmonsters.商人.Yml("double","附魔商店设置.pitch");
        Location loc = new Location(world
                ,(double) Fightmonsters.商人.Yml("double","附魔商店设置.x")
                ,(double) Fightmonsters.商人.Yml("double","附魔商店设置.y")
                ,(double) Fightmonsters.商人.Yml("double","附魔商店设置.z")
                ,(float) yaw
                ,(float) pitch
        );
        Villager villager = (Villager)world.spawnEntity(loc, EntityType.VILLAGER);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setCustomName((String)Fightmonsters.商人.Yml("String","附魔商店设置.name"));
        villager.setCustomNameVisible(true); //设置名字可见*//*
        初始化附魔商人列表();
    }
    public void 初始化附魔商人列表()
    {
        ItemStack 玻璃板 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 返回功能 = new ItemStack(Material.CLOCK);
        ItemMeta 玻璃板_M = 玻璃板.getItemMeta();
        ItemMeta 返回功能_M = 返回功能.getItemMeta();
        返回功能_M.setDisplayName(ChatColor.GREEN+"[左键点击]返回主菜单");
        玻璃板_M.setDisplayName(ChatColor.DARK_PURPLE+"菜单功能区");
        玻璃板.setItemMeta(玻璃板_M);
        返回功能.setItemMeta(返回功能_M);
        for (int i = 45;i <= 53;i++){
            附魔商人菜单_00.setItem(i,玻璃板);
        }
        附魔商人菜单_00.setItem(53,返回功能);

        for (int a = 0;;a++){
            if (Fightmonsters.附魔商人菜单.contains("Function_"+a))
            {
                ItemStack it_ = new ItemStack(Material.valueOf((String) Fightmonsters.附魔商人菜单.Yml("String","Function_"+a+".Item")));
                ItemMeta im_ = it_.getItemMeta();
                if (Fightmonsters.附魔商人菜单.contains("Function_"+a+".title"))
                    im_.setDisplayName((String) Fightmonsters.附魔商人菜单.Yml("String","Function_"+a+".title"));

                if (Fightmonsters.附魔商人菜单.contains("Function_"+a+".Lore"))
                    im_.setLore((List<String>) Fightmonsters.附魔商人菜单.Yml("List","Function_"+a+".Lore"));

                it_.setItemMeta(im_);
                附魔商人菜单_home.setItem((int) Fightmonsters.附魔商人菜单.Yml("int","Function_"+a+".RawSlot"),it_);
                for (int i = 0;Fightmonsters.附魔商人菜单.contains("Function_"+a+".Get."+i);i++)
                {/////
                    ItemStack itemStack = new ItemStack(Material.valueOf((String) Fightmonsters.附魔商人菜单.Yml("String","Function_"+a+".Get."+i+".Item")));
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if(Fightmonsters.附魔商人菜单.contains("Function_"+a+".Get."+i+".物品名称设置.name"))
                        itemMeta.setDisplayName((String) Fightmonsters.附魔商人菜单.Yml("String","Function_"+a+".Get."+i+".物品名称设置.name"));
                    if (Fightmonsters.附魔商人菜单.contains("Function_"+a+".Get."+i+".物品名称设置.lore")){
                        List<String> list;
                        list = (List<String>) Fightmonsters.附魔商人菜单.Yml("List","Function_"+a+".Get."+i+".物品名称设置.lore");
                        list.add("§b商品价格: §a"+Fightmonsters.附魔商人菜单.Yml("int","Function_"+a+".Get."+i+".Pay.price"));
                        itemMeta.setLore(list);
                    }else{
                        List<String> list = new ArrayList<>();
                        list.add("§b商品价格: §a"+Fightmonsters.附魔商人菜单.Yml("int","Function_"+a+".Get."+i+".Pay.price"));
                        itemMeta.setLore(list);
                    }
                    itemStack.setItemMeta(itemMeta);
                    switch (a)
                    {
                        case 0:{附魔商人菜单_00.setItem(i,itemStack); break;}
                    }
                }/////
            }else{
                return;
            }
        }
    }

}
