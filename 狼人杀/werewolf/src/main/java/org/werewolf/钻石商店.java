package org.werewolf;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class 钻石商店 {
    public static 钻石商店 钻石商店class = new 钻石商店();
    public static Inventory 钻石商店容器 = Bukkit.createInventory(null,9*6,"钻石商店");

    public void 初始化商店容器(){
        List<String> list = new ArrayList<>();

        for (int i = 0;Werewolf.钻石商店.contains("Item_"+i);i++){
            ItemStack it_0 = new ItemStack(Material.valueOf((String) Werewolf.钻石商店.Yml("String","Item_"+i+".Item")));
            ItemMeta itemMeta = it_0.getItemMeta();
            if (Werewolf.钻石商店.contains("Item_"+i+".name")){
                itemMeta.setDisplayName((String) Werewolf.钻石商店.Yml("String","Item_"+i+".name"));
            }
            if (Werewolf.钻石商店.contains("Item_"+i+".lore")){
                list = (List<String>) Werewolf.钻石商店.Yml("List","Item_"+i+".lore");
            }
            list.add(ChatColor.GREEN+"需要钻石: "+Werewolf.钻石商店.Yml("int","Item_"+i+".Pay.price"));
            itemMeta.setLore(list);

            list.clear();
            it_0.setAmount((int)Werewolf.钻石商店.Yml("int","Item_"+i+".Pay.quantity"));

            if (Werewolf.钻石商店.contains("Item_"+i+".药水")){
                PotionMeta potionMeta = (PotionMeta) itemMeta;
                List<String> 药水效果 = (List<String>) Werewolf.钻石商店.Yml("List","Item_"+i+".药水.效果");
                for (String str : 药水效果) {
                    String[] spl = str.split(" ");
                    potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(spl[0]),Integer.parseInt(spl[1])*20,
                                    Integer.parseInt(spl[2])), true);
                }
                List<Integer> 颜色 = (List<Integer>)Werewolf.钻石商店.Yml("List","Item_"+i+".药水.颜色");
                potionMeta.setColor(Color.fromBGR(颜色.get(0),颜色.get(1),颜色.get(2)));
                it_0.setItemMeta(potionMeta);
            }
            it_0.setItemMeta(itemMeta);
            钻石商店容器.setItem(i,it_0);
        }
    }
    public ItemStack 物品给以(int i){
        ItemStack it_0 = new ItemStack(Material.valueOf((String) Werewolf.钻石商店.Yml("String","Item_"+i+".Item")));
        ItemMeta itemMeta = it_0.getItemMeta();
        if (Werewolf.钻石商店.contains("Item_"+i+".name")){
            itemMeta.setDisplayName((String) Werewolf.钻石商店.Yml("String","Item_"+i+".name"));
        }
        if (Werewolf.钻石商店.contains("Item_"+i+".lore")){
            List<String> list = new ArrayList<>();
            list = (List<String>) Werewolf.钻石商店.Yml("List","Item_"+i+".lore");
            itemMeta.setLore(list);
        }
        it_0.setAmount((int)Werewolf.钻石商店.Yml("int","Item_"+i+".Pay.quantity"));
        if (Werewolf.钻石商店.contains("Item_"+i+".药水")){
            PotionMeta potionMeta = (PotionMeta) itemMeta;
            List<String> 药水效果 = (List<String>) Werewolf.钻石商店.Yml("List","Item_"+i+".药水.效果");
            for (String str : 药水效果) {
                String[] spl = str.split(" ");
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(spl[0]),Integer.parseInt(spl[1])*20,
                        Integer.parseInt(spl[2])), true);
            }
            List<Integer> 颜色 = (List<Integer>)Werewolf.钻石商店.Yml("List","Item_"+i+".药水.颜色");
            potionMeta.setColor(Color.fromBGR(颜色.get(0),颜色.get(1),颜色.get(2)));
            it_0.setItemMeta(potionMeta);
        }
        it_0.setItemMeta(itemMeta);
        return it_0;
    }






}
