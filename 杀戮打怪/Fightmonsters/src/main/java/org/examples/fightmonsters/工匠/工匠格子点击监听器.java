package org.examples.fightmonsters.工匠;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.fightmonsters.Shop.工匠商人设置;
import org.examples.fightmonsters.Shop.金币;

import java.util.ArrayList;
import java.util.List;

public class 工匠格子点击监听器 implements Listener {
    @EventHandler
    public void InventoryClickEvent (InventoryClickEvent event) {
        if (event.getView().getTitle().equals("工匠修复")) {
            event.setCancelled(true);

            if (event.getRawSlot() > 53) { //玩家外背包放入
                if (event.getCurrentItem() == null) {
                    return;
                }
                Player player = (Player) event.getWhoClicked();
                ItemStack itemStack = event.getCurrentItem(); //获取需要鉴定的物品
                if (event.getInventory().getItem(22) != null) {
                    ItemStack is = event.getInventory().getItem(22); //已经放入格子的物品
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().setItem(player.getInventory().firstEmpty(), is);
                    } else {
                        player.getWorld().dropItemNaturally(player.getLocation(), is);
                    }
                }
                event.getInventory().setItem(22, itemStack);
                String 获取修复物品 = 工匠修复.工匠修复class.获取该物品是否在修复名单(itemStack);
                //处理物品
                if (获取修复物品.equals("false")) {
                    player.getWorld().playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                    ItemStack noFix = new ItemStack(Material.RED_WOOL);
                    ItemMeta itemMeta = noFix.getItemMeta();
                    itemMeta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String", "确定修复按键初始化.修复失败.DispName"));
                    noFix.setItemMeta(itemMeta);
                    event.getInventory().setItem(38, noFix);
                    event.getCurrentItem().setAmount(0);
                    return;
                }
                ItemStack noFix = new ItemStack(Material.GREEN_WOOL);
                ItemMeta itemMeta = noFix.getItemMeta();
                itemMeta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String", "确定修复按键初始化.DispName"));
                List<String> listString = new ArrayList<>();
                listString.add(ChatColor.BLUE + "价格: " + 获取修复物品);

                工匠修复.修复的价格 = Integer.parseInt(获取修复物品);  //增加价格 方便进行判断

                itemMeta.setLore(listString);
                noFix.setItemMeta(itemMeta);
                event.getInventory().setItem(38, noFix);
                event.getCurrentItem().setAmount(0);
                return;
            }

            //////////////////////////////////////////////////////////////////////////////逻辑处理
            if (event.getRawSlot() == 22) //拿出物品
            {
                if (event.getCurrentItem() == null) { //点击空气无法使用
                    return;
                }
                ItemStack itemStack = event.getCurrentItem();
                Player player = (Player) event.getWhoClicked();
                if (player.getInventory().firstEmpty() == -1) {
                    player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                } else {
                    player.getInventory().setItem(player.getInventory().firstEmpty(), itemStack);
                }
                //初始化修复按钮
                工匠修复.修复的价格 = -1;
                ItemStack 启动修复按钮 = new ItemStack(Material.GREEN_WOOL);
                ItemMeta 启动修复按钮_Meta = 启动修复按钮.getItemMeta();
                启动修复按钮_Meta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String", "确定修复按键初始化.DispName"));
                启动修复按钮_Meta.setLore((List<String>) 工匠文件处理.工匠修复.Yml("List", "确定修复按键初始化.Lore"));
                启动修复按钮.setItemMeta(启动修复按钮_Meta);
                工匠修复.工匠修复_修复分解.setItem(38, 启动修复按钮);
                //放入检查

                event.getCurrentItem().setAmount(0);
                return;
            }


            if (event.getRawSlot() == 38) {//修复物品
                ItemStack itemStack = event.getInventory().getItem(22);
                if (itemStack == null) {
                    return;
                }
                if (工匠修复.修复的价格 == -1) {
                    return;
                }
                Player player = (Player) event.getWhoClicked();
                if (!金币.金币设置.检测是否购买金币是否满足(player, 工匠修复.修复的价格)) {
                    player.getWorld().playSound(player, Sound.ENTITY_VILLAGER_NO, 1, 1);
                    player.sendMessage(ChatColor.RED + "你没有金币修复该物品");
                    player.closeInventory();
                    return;
                }
                金币.金币设置.金币支付(player, 工匠修复.修复的价格);
                工匠修复.修复的价格 = -1;
                ItemStack 修复品 = 工匠修复.工匠修复class.修复该物品(itemStack);
                player.getWorld().playSound(player, Sound.ENTITY_VILLAGER_YES, 1, 1);
                new 工匠打造时刻().正在打造装备(20 * 6, player.getWorld(), 修复品); //已经进行箱子操作
                event.getInventory().setItem(22, ItemStack.of(Material.AIR));
                player.closeInventory();
                return;
            }

            if (event.getRawSlot() == 42) {//分解物品
                ItemStack itemStack = event.getInventory().getItem(22);
                if (itemStack == null) {
                    return;
                }
                Player player = (Player) event.getWhoClicked();
                String Isfixes = 工匠修复.工匠修复class.获取该物品是否可以分解(itemStack);
                if (Isfixes.equals("false")) {
                    //该物品不能被修复
                    player.sendMessage(ChatColor.RED + "该物品不支持分解!");
                    player.closeInventory();
                    event.getInventory().setItem(22, ItemStack.of(Material.AIR));
                    return;
                }
                String[] arr = Isfixes.split(" ");
                ItemStack itemStack1 = new ItemStack(Material.getMaterial(arr[0]));
                itemStack1.setAmount(Integer.parseInt(arr[1]));
                new 工匠打造时刻().正在打造装备(20 * 6, player.getWorld(), itemStack1); //已经进行箱子操作
                event.getInventory().setItem(22, ItemStack.of(Material.AIR));
                player.closeInventory();

                return;
            }

            if (event.getRawSlot() == 53) {
                Player player = (Player) event.getWhoClicked();
                player.openInventory(工匠商人设置.工匠商人设置class.工匠商人);
                return;
            }

            return;
        }
        //主菜单处理
        if (event.getView().getTitle().equals("工匠商店菜单_home")) {
            工匠商人设置.是否有人正在使用该工匠 = true;
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getRawSlot() == 20) { //工匠升级
                player.openInventory(工匠升级.工匠升级菜单);
                return;
            }
            if (event.getRawSlot() == 24) { //工匠修复
                player.openInventory(工匠修复.工匠修复_修复分解);
                return;
            }

        }

        if (event.getView().getTitle().equals("工匠升级")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getRawSlot() > 53) { //玩家外背包放入
                if (event.getCurrentItem() == null) {
                    return;
                }
                ItemStack itemStack = event.getCurrentItem(); //获取需要鉴定的物品
                if (event.getInventory().getItem(22) != null) {
                    ItemStack is = event.getInventory().getItem(22); //已经放入格子的物品
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().setItem(player.getInventory().firstEmpty(), is);
                    } else {
                        player.getWorld().dropItemNaturally(player.getLocation(), is);
                    }
                }
                event.getInventory().setItem(22, itemStack); //将玩家手中物品放入容器里





             }

                if (event.getRawSlot() == 53) { //返回菜单
                    player.openInventory(工匠商人设置.工匠商人设置class.工匠商人);
                }
                if (event.getRawSlot() == 22) {//拿出物品
                    ItemStack item = event.getCurrentItem();
                    if (item == null) return;
                    if (player.getInventory().firstEmpty() == -1) {
                        player.getWorld().dropItemNaturally(player.getLocation(), item);
                    } else {
                        player.getInventory().setItem(player.getInventory().firstEmpty(), item);
                    }
                    工匠升级.工匠升级菜单.setItem(22, ItemStack.of(Material.AIR));
                    //初始化格子栏
                    //code
                    for (int i = 38; i < 43; i++) {
                        工匠升级.工匠升级菜单.setItem(i, ItemStack.of(Material.AIR));
                    }
                    return;
                }


            }

        }
    @EventHandler
    public void 关闭容器(InventoryCloseEvent event){
        if (event.getView().getTitle().equals("工匠修复")){
            工匠商人设置.是否有人正在使用该工匠 = false;
            if (event.getInventory().getItem(22) != null){
                ItemStack itemStack = event.getInventory().getItem(22);
                //修复容器放有物品
                if (event.getPlayer().getInventory().firstEmpty() != -1){
                    event.getPlayer().getInventory().addItem(itemStack);
                }else{
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(),itemStack);
                }

                工匠修复.修复的价格 = -1;

                event.getInventory().setItem(22,ItemStack.of(Material.AIR));

                ItemStack 启动修复按钮 = new ItemStack(Material.GREEN_WOOL);
                ItemMeta 启动修复按钮_Meta =启动修复按钮.getItemMeta();
                启动修复按钮_Meta.setDisplayName((String) 工匠文件处理.工匠修复.Yml("String","确定修复按键初始化.DispName"));
                启动修复按钮_Meta.setLore((List<String>) 工匠文件处理.工匠修复.Yml("List","确定修复按键初始化.Lore"));
                启动修复按钮.setItemMeta(启动修复按钮_Meta);
                工匠修复.工匠修复class.工匠修复_修复分解.setItem(38,启动修复按钮);
                return;
            }
            return;
        }


        if (event.getView().getTitle().equals("工匠商店菜单_home")){
            工匠商人设置.是否有人正在使用该工匠 = false;
            return;
        }

        if (event.getView().getTitle().equals("工匠升级")){
            工匠商人设置.是否有人正在使用该工匠 = false;
            return;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //该方法是垃圾箱子使用方法

        if (event.getView().getTitle().equals("工匠箱子")){
            Player player = (Player) event.getPlayer();
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
            return;
        }



    }

}
