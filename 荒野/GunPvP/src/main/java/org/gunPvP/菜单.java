package org.gunPvP;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class 菜单 implements Listener {
    public static Inventory 游戏菜单 = Bukkit.createInventory(null,5*9,"游戏菜单");
    public void 初始化菜单(){
        for (String Pare :GunPvP.地图Yml.获取该节点的所有子节点名字集合("WorldName")){
            ItemStack itemStack = new ItemStack(Material.RED_WOOL);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GREEN +(String) GunPvP.地图Yml.Yml("String","WorldName."+Pare+".name"));
            List<String> list_Lore = new ArrayList<>();
            list_Lore.add(ChatColor.BLUE+"需要人数: "+GunPvP.地图Yml.Yml("int","WorldName."+Pare+".Player")+"人");
            itemMeta.setLore(list_Lore);
            itemStack.setItemMeta(itemMeta);
        }
    }


}
