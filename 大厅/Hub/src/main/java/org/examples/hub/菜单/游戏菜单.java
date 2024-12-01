package org.examples.hub.菜单;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.hub.Hub;

import java.util.List;

public class 游戏菜单 {
    public static 游戏菜单 游戏菜单class = new 游戏菜单();
    public static Inventory 游戏菜单 = Bukkit.createInventory(null,9*6,"游戏菜单");
    public void 初始化菜单()
    {
        for (int a = 0;;a++)
        {
            if (Hub.游戏菜单配置文件.contains("Item_"+a))
            {
                ItemStack it_ = new ItemStack(Material.valueOf((String) Hub.游戏菜单配置文件.Yml("String","Item_"+a+".Item")));
                ItemMeta im_ = it_.getItemMeta();
                if (Hub.游戏菜单配置文件.contains("Item_"+a+".title"))
                    im_.setDisplayName((String) Hub.游戏菜单配置文件.Yml("String","Item_"+a+".title"));
                if (Hub.游戏菜单配置文件.contains("Item_"+a+".Lore"))
                    im_.setLore((List<String>) Hub.游戏菜单配置文件.Yml("List","Item_"+a+".Lore"));
                it_.setItemMeta(im_);
                游戏菜单.setItem((int) Hub.游戏菜单配置文件.Yml("int","Item_"+a+".RawSlot"),it_);
            }else{
                return;
            }
        }
    }
    public void 菜单给以(Player player){
        ItemStack itemStack = new ItemStack(Material.valueOf((String) Hub.游戏菜单配置文件.Yml("String","菜单属性.Item")));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName((String) Hub.游戏菜单配置文件.Yml("String","菜单属性.name"));
        itemStack.setItemMeta(itemMeta);
        player.getInventory().addItem(itemStack);
    }



}
