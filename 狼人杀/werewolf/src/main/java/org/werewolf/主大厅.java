package org.werewolf;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class 主大厅 {
    public static Inventory inventory = Bukkit.createInventory(null,9*5,"地图选择");
    public static 主大厅 主大厅class = new 主大厅();
    public ItemStack 大厅菜单获取(){
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN+"[右键点击]选择房间");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Location  返回主大厅(){
        World world = Bukkit.getWorld("world");
        List<Double> loc_list = (List<Double>) Werewolf.Main_Hub.Yml("List","位置");
        double x = loc_list.get(0);
        double y = loc_list.get(1);
        double z = loc_list.get(2);
        Location location = new Location(world, x, y, z);
        return location;
    }
    public void 初始化选择器(){
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED+"[地图]盒提杜(最少6人)");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(0,itemStack);

        ItemStack item_2 = new ItemStack(Material.GREEN_WOOL);
        ItemMeta item_2_itemMeta = item_2.getItemMeta();
        item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]美术馆(最少3人)");
        item_2.setItemMeta(item_2_itemMeta);
        inventory.setItem(1,item_2);

        ItemStack item_3 = new ItemStack(Material.GREEN_WOOL);
        ItemMeta item_3_itemMeta = item_3.getItemMeta();
        item_3_itemMeta.setDisplayName(ChatColor.RED+"[地图]体育馆(最少12人)");
        item_3.setItemMeta(item_3_itemMeta);
        inventory.setItem(2,item_3);
    }

    /***
     * 传入正在游戏的房间
     * @param world
     */
    public void 选择器挂上房间正在进行(World world){
        List<String> list = new ArrayList<>();
        list.add(ChatColor.RED+"该房间正在游戏中!");
        switch (world.getName()){
            case "01":{
                ItemStack item_1 = new ItemStack(Material.RED_WOOL);
                ItemMeta item_1_itemMeta = item_1.getItemMeta();
                item_1_itemMeta.setDisplayName(ChatColor.RED+"[地图]盒提杜");
                item_1_itemMeta.setLore(list);
                item_1.setItemMeta(item_1_itemMeta);
                inventory.setItem(0,item_1);
                return;
            }
            case "02":{
                ItemStack item_2 = new ItemStack(Material.RED_WOOL);
                ItemMeta item_2_itemMeta = item_2.getItemMeta();
                item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]美术馆");
                item_2_itemMeta.setLore(list);
                item_2.setItemMeta(item_2_itemMeta);
                inventory.setItem(1,item_2);
                return;
            }
            case "03":{
                ItemStack item_2 = new ItemStack(Material.RED_WOOL);
                ItemMeta item_2_itemMeta = item_2.getItemMeta();
                item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]体育馆");
                item_2_itemMeta.setLore(list);
                item_2.setItemMeta(item_2_itemMeta);
                inventory.setItem(2,item_2);
                return;
            }
        }
    }
    public void 房间选择器去进行(World world){
        switch (world.getName()){
            case "01":{
                ItemStack item_2 = new ItemStack(Material.GREEN_WOOL);
                ItemMeta item_2_itemMeta = item_2.getItemMeta();
                item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]盒提杜(最少6人)");
                item_2.setItemMeta(item_2_itemMeta);
                inventory.setItem(0,item_2);
                return;
            }
            case "02":{
                ItemStack item_2 = new ItemStack(Material.GREEN_WOOL);
                ItemMeta item_2_itemMeta = item_2.getItemMeta();
                item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]美术馆(最少3人)");
                item_2.setItemMeta(item_2_itemMeta);
                inventory.setItem(1,item_2);
                return;
            }
            case "03":{
                ItemStack item_2 = new ItemStack(Material.GREEN_WOOL);
                ItemMeta item_2_itemMeta = item_2.getItemMeta();
                item_2_itemMeta.setDisplayName(ChatColor.RED+"[地图]体育馆(最少12人)");
                item_2.setItemMeta(item_2_itemMeta);
                inventory.setItem(2,item_2);
                return;
            }
        }
    }

}
