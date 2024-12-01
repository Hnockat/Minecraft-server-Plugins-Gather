package org.gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class give枪 {
    public static give枪 ThisGet = new give枪();
    //string 机枪类型.key
    public ItemStack giveGun(String string){
        ItemStack itemStack = new ItemStack(Material.getMaterial((String) Gun.GetYml_机枪类型.Yml("String","机枪类型."+string+".Material")));
        NBT.NbtSet.set机枪标签(itemStack,string);
        NBT.NbtSet.子弹初始化(itemStack,0);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName((String) Gun.GetYml_机枪类型.Yml("String","机枪类型."+string+".Name"));
        List<String> list = new ArrayList<>();
        list.add(ChatColor.RED+"该枪需要安装弹夹!");
        list.add(ChatColor.GREEN+"该枪支射击类型为: "+ChatColor.GREEN+射击类型((String) Gun.GetYml_机枪类型.Yml("String","机枪类型."+string+".发射类型")));
        list.add(ChatColor.BLUE+"该枪支持的装配弹夹列表: ");
        for (String bulle: (List<String>)Gun.GetYml_机枪类型.Yml("List","机枪类型."+string+".子弹")){
            list.add(ChatColor.GREEN+bulle+"弹夹");
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public String 射击类型(String string) {
        switch (string) {
            case "ZD":
                return "全自动";
            case "DF":
                return "半自动";
            case "SD":
                return "半自动散弹枪";
        }
        return "null";
    }
}
