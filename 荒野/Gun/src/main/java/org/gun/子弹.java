package org.gun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class 子弹 {
    public static 子弹 子弹class = new 子弹();
    //弹夹类型  key.key 子弹口径.枪的类型
    List<String> 玻璃系列破坏 = new ArrayList<>();
    public void 初始化子弹破坏方块列表(){
        玻璃系列破坏 = (List<String>)Gun.GetYml_子弹破坏方块列表.Yml("List","GLASS.Type");
    }

    public ItemStack 子弹给以(String 弹夹类型){
        ItemStack itemStack = new ItemStack(Material.valueOf((String) Gun.GetYml_子弹.Yml("String",弹夹类型+".Item")));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW+(String) Gun.GetYml_子弹.Yml("String",弹夹类型+".name"));
        List<String> list_Lore = (List<String>) Gun.GetYml_子弹.Yml("List",弹夹类型+".Lore");
        list_Lore.add(ChatColor.BLUE+"容量: "+(int)Gun.GetYml_子弹.Yml("int",弹夹类型+".容量"));
        itemMeta.setLore(list_Lore);
        NBT.NbtSet.set弹夹标签(itemStack,弹夹类型);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
