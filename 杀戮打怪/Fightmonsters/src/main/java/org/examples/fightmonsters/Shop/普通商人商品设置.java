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

public class 普通商人商品设置 {
        public static Inventory 普通商人菜单_home = Bukkit.createInventory(null,9*6,"普通商人菜单_home");
        public static Inventory 普通商人菜单_00 = Bukkit.createInventory(null,9*6,"普通商人菜单_00");
        public static Inventory 普通商人菜单_01 = Bukkit.createInventory(null,9*6,"普通商人菜单_01");
        public static Inventory 普通商人菜单_02 = Bukkit.createInventory(null,9*6,"普通商人菜单_02");
        public static Inventory 普通商人菜单_03 = Bukkit.createInventory(null,9*6,"普通商人菜单_03");
        public static Inventory 普通商人菜单_04 = Bukkit.createInventory(null,9*6,"普通商人菜单_04");
    public static 普通商人商品设置 普通商人class = new 普通商人商品设置();
    public void 初始化商人(World world)
    {
        double yaw = (double) Fightmonsters.商人.Yml("double","普通商店设置.yaw");
        double pitch = (double) Fightmonsters.商人.Yml("double","普通商店设置.pitch");
        Location loc = new Location(world
                ,(double) Fightmonsters.商人.Yml("double","普通商店设置.x")
                ,(double) Fightmonsters.商人.Yml("double","普通商店设置.y")
                ,(double) Fightmonsters.商人.Yml("double","普通商店设置.z")
                ,(float) yaw
                ,(float) pitch
        );
        Villager villager = (Villager)world.spawnEntity(loc, EntityType.VILLAGER);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setCustomName((String)Fightmonsters.商人.Yml("String","普通商店设置.name"));
        villager.setCustomNameVisible(true); //设置名字可见*//*
        初始化商人菜单();
    }
    //初始化商人菜单 向菜单塞物品
    public void 初始化商人菜单()
    {
        //帮助按键设置 !
        ItemStack 玻璃板 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 返回功能 = new ItemStack(Material.CLOCK);
        ItemMeta 玻璃板_M = 玻璃板.getItemMeta();
        ItemMeta 返回功能_M = 返回功能.getItemMeta();
        返回功能_M.setDisplayName(ChatColor.GREEN+"[左键点击]返回主菜单");
        玻璃板_M.setDisplayName(ChatColor.DARK_PURPLE+"菜单功能区");
        玻璃板.setItemMeta(玻璃板_M);
        返回功能.setItemMeta(返回功能_M);
        for (int i = 45;i <= 53;i++){
            普通商人菜单_00.setItem(i,玻璃板);
            普通商人菜单_01.setItem(i,玻璃板);
            普通商人菜单_02.setItem(i,玻璃板);
            普通商人菜单_03.setItem(i,玻璃板);
            普通商人菜单_04.setItem(i,玻璃板);
        }
        普通商人菜单_00.setItem(53,返回功能);
        普通商人菜单_01.setItem(53,返回功能);
        普通商人菜单_02.setItem(53,返回功能);
        普通商人菜单_03.setItem(53,返回功能);
        普通商人菜单_04.setItem(53,返回功能);

        for (int a = 0;;a++){
            if (Fightmonsters.普通商人菜单.contains("Item_"+a))
            {
                ItemStack it_ = new ItemStack(Material.valueOf((String) Fightmonsters.普通商人菜单.Yml("String","Item_"+a+".Item")));
                ItemMeta im_ = it_.getItemMeta();
                if (Fightmonsters.普通商人菜单.contains("Item_"+a+".title"))
                    im_.setDisplayName((String) Fightmonsters.普通商人菜单.Yml("String","Item_"+a+".title"));

                if (Fightmonsters.普通商人菜单.contains("Item_"+a+".Lore"))
                    im_.setLore((List<String>) Fightmonsters.普通商人菜单.Yml("List","Item_"+a+".Lore"));

                it_.setItemMeta(im_);
                普通商人菜单_home.setItem((int) Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".RawSlot"),it_);
                for (int i = 0;Fightmonsters.普通商人菜单.contains("Item_"+a+".Get."+i);i++)
                {/////
                    ItemStack itemStack = new ItemStack(Material.valueOf((String) Fightmonsters.普通商人菜单.Yml("String","Item_"+a+".Get."+i+".Item")));
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (Fightmonsters.普通商人菜单.contains("Item_"+a+".Get."+i+".Pay.quantity"))
                        itemStack.setAmount((int)Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".Get."+i+".Pay.quantity"));
                    if(Fightmonsters.普通商人菜单.contains("Item_"+a+".Get."+i+".物品名称设置.name"))
                    itemMeta.setDisplayName((String) Fightmonsters.普通商人菜单.Yml("String","Item_"+a+".Get."+i+".物品名称设置.name"));
                    if (Fightmonsters.普通商人菜单.contains("Item_"+a+".Get."+i+".物品名称设置.lore")){
                        List<String> list;
                        list = (List<String>) Fightmonsters.普通商人菜单.Yml("List","Item_"+a+".Get."+i+".物品名称设置.lore");
                        list.add("§b商品价格: §a"+Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".Get."+i+".Pay.price"));
                        itemMeta.setLore(list);
                    }else{
                        List<String> list = new ArrayList<>();
                        list.add("§b商品价格: §a"+Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".Get."+i+".Pay.price"));
                        itemMeta.setLore(list);
                    }
                    if (Fightmonsters.普通商人菜单.contains("Item_"+a+".Get."+i+".Enchantment"))
                    {
                       for (String set : Fightmonsters.普通商人菜单.获取该节点的所有子节点名字集合("Item_"+a+".Get."+i+".Enchantment"))
                       {
                           itemMeta.addEnchant(Enchantment.getByName(set),(int)Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".Get."+i+".Enchantment."+set),true);
                       }
                    }
                    itemStack.setItemMeta(itemMeta);
                    switch (a)
                    {
                        case 0:{普通商人菜单_00.setItem(i,itemStack); break;}
                        case 1:{普通商人菜单_01.setItem(i,itemStack); break;}
                        case 2:{普通商人菜单_02.setItem(i,itemStack); break;}
                        case 3:{普通商人菜单_03.setItem(i,itemStack); break;}
                        case 4:{普通商人菜单_04.setItem(i,itemStack); break;}
                    }
                }/////
            }else{
                return;
            }
        }
    }
    public ItemStack 物品给以(String File){
        ItemStack itemStack = new ItemStack(Material.valueOf((String) Fightmonsters.普通商人菜单.Yml("String",File+".Item")));
        itemStack.setAmount((int)Fightmonsters.普通商人菜单.Yml("int",File+".Pay.quantity"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName((String) Fightmonsters.普通商人菜单.Yml("String",File+".物品名称设置.name"));
        if (Fightmonsters.普通商人菜单.contains(File+".物品名称设置.lore")){ //设置物品lore
            itemMeta.setLore((List<String>) Fightmonsters.普通商人菜单.Yml("List",File+".物品名称设置.lore"));
        }
        if (Fightmonsters.普通商人菜单.contains(File+".Enchantment")) //设置物品的附魔属性
        {
            for (String set : Fightmonsters.普通商人菜单.获取该节点的所有子节点名字集合(File+".Enchantment"))
            {
                itemMeta.addEnchant(Enchantment.getByName(set),(int)Fightmonsters.普通商人菜单.Yml("int",File+".Enchantment."+set),true);
            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
