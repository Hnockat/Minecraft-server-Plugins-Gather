
package org.examples.fightmonsters.工匠;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.fightmonsters.Fightmonsters;

import java.util.List;

public class 工匠修复 {
    public static Inventory 工匠修复_修复分解 = Bukkit.createInventory(null,9*6,"工匠修复");
    public static 工匠修复 工匠修复class = new 工匠修复();
    public static int 修复的价格 = -1;
    public void 容器初始化()
    {
        ItemStack 玻璃板 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
       for (int i = 0; i < 9*6;i++){
            工匠修复_修复分解.setItem(i,玻璃板);
        }
        工匠修复_修复分解.setItem(22, ItemStack.of(Material.AIR));
        //修复按钮
        ItemStack 启动修复按钮 = new ItemStack(Material.GREEN_WOOL);
        ItemMeta 启动修复按钮_Meta =启动修复按钮.getItemMeta();
        启动修复按钮_Meta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String","确定修复按键初始化.DispName"));
        启动修复按钮_Meta.setLore((List<String>) 工匠文件处理.工匠修复.Yml("List","确定修复按键初始化.Lore"));
        启动修复按钮.setItemMeta(启动修复按钮_Meta);
        工匠修复_修复分解.setItem(38,启动修复按钮);
        //分解按钮
        ItemStack 分解按钮 = new ItemStack(Material.STONECUTTER);
        ItemMeta 分解按钮_Meta = 分解按钮.getItemMeta();
        分解按钮_Meta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String","分解按钮.DispName"));
        分解按钮_Meta.setLore((List<String>) 工匠文件处理.工匠修复.Yml("List","分解按钮.Lore"));
        分解按钮.setItemMeta(分解按钮_Meta);
        工匠修复_修复分解.setItem(42,分解按钮);
        //返回到主页面
        ItemStack 返回到主页面 = new ItemStack(Material.BAMBOO_CHEST_RAFT);
        ItemMeta 返回到主页面_Meta = 返回到主页面.getItemMeta();
        返回到主页面_Meta.setDisplayName(ChatColor.BLUE+"返回到主页面");
        返回到主页面.setItemMeta(返回到主页面_Meta);
        工匠修复_修复分解.setItem(53,返回到主页面);
    }
    public ItemStack 修复该物品(ItemStack itemStack){
        Damageable damageable = (Damageable) itemStack.getItemMeta();
        damageable.setDamage(0);
        itemStack.setItemMeta(damageable);
        return itemStack;
    }
    // return 返回分解物品  {分解物品枚举 , 分解物品数量 }
    // itemStack 分解目标物品
    public String 获取该物品是否可以分解(ItemStack itemStack){
        for (int i = 1;;i++){
            if (工匠文件处理.工匠修复.contains("可修复列表.Item_"+i)){
                if (!工匠文件处理.工匠修复.contains("可修复列表.Item_"+i+".分解")){ //看是否支持修复
                    continue;
                }
                if (工匠文件处理.工匠修复.激活().isList("可修复列表.Item_"+i+".ItemList")){
                    List<String> list = (List<String>)工匠文件处理.工匠修复.Yml("List","可修复列表.Item_"+i+".ItemList");
                    if (list.contains(itemStack.getType().name())){
                            return 工匠文件处理.工匠修复.Yml("String", "可修复列表.Item_" + i + ".分解.Item") + " " + 工匠文件处理.工匠修复.Yml("int", "可修复列表.Item_" + i + ".分解.size");
                    }
                }else{
                    String str= (String)工匠文件处理.工匠修复.Yml("String","可修复列表.Item_"+i+".ItemList");
                    if (str.equals(itemStack.getType().name())){
                        return 工匠文件处理.工匠修复.Yml("String","可修复列表.Item_"+i+".分解.Item")+" "+工匠文件处理.工匠修复.Yml("int","可修复列表.Item_"+i+".分解.size");
                    }
                }
            }else{
                return "false"; //不能分解
            }
        }

    }



    public String 获取该物品是否在修复名单(ItemStack itemStack)
    {
        for (int i = 1;;i++){
            if (工匠文件处理.工匠修复.contains("可修复列表.Item_"+i)){
                if (工匠文件处理.工匠修复.激活().isList("可修复列表.Item_"+i+".ItemList")){
                   List<String> list = (List<String>)工匠文件处理.工匠修复.Yml("List","可修复列表.Item_"+i+".ItemList");
                   if (list.contains(itemStack.getType().name())){
                       return (int)工匠文件处理.工匠修复.Yml("int","可修复列表.Item_"+i+".价格")+""; //ok
                   }
                }else{
                    String str= (String)工匠文件处理.工匠修复.Yml("String","可修复列表.Item_"+i+".ItemList");
                    if (str.equals(itemStack.getType().name())){
                        return (int)工匠文件处理.工匠修复.Yml("int","可修复列表.Item_"+i+".价格")+""; //ok
                    }
                }
            }else{
                return "false"; //未发现有该物品
            }
        }
    }
}

