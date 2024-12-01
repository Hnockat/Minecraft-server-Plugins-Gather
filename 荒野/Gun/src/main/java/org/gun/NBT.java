package org.gun;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.K;
import org.gun.Gun;

import java.util.ArrayList;
import java.util.List;

public class NBT {
    private static final NamespacedKey 机枪标签 = new NamespacedKey(Gun.getInstance(),"PEV");//枪械标记 String
    private static final NamespacedKey 弹夹标签 = new NamespacedKey(Gun.getInstance(),"BULLET");//标记 String
    private static final NamespacedKey S = new NamespacedKey(Gun.getInstance(),"ZD"); //子弹容量检测标签  INTEGER
    private static final NamespacedKey 枪配装的弹夹 = new NamespacedKey(Gun.getInstance(),"SHA"); //机枪  String
    private static final NamespacedKey K = new NamespacedKey(Gun.getInstance(),"PB");// 进度条记录数据 INT

    public static NBT NbtSet = new NBT();
    public String 获取手上的Nbt(Player player){
        ItemStack itemStack = player.getItemInHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String s;
        s = itemMeta.getPersistentDataContainer().get(机枪标签,PersistentDataType.STRING);
        s = s+"///"+itemMeta.getPersistentDataContainer().get(S,PersistentDataType.INTEGER);
        s = s+"///"+itemMeta.getPersistentDataContainer().get(K,PersistentDataType.INTEGER);
        s = s+"///"+itemMeta.getPersistentDataContainer().get(弹夹标签,PersistentDataType.STRING);
        s = s+"///"+itemMeta.getPersistentDataContainer().get(枪配装的弹夹,PersistentDataType.STRING);
        return s;
    }
    public void 该枪配装的弹夹Set(ItemStack Gun,String Type){
        ItemMeta itemMeta = Gun.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(枪配装的弹夹,PersistentDataType.STRING, Type);
        Gun.setItemMeta(itemMeta);
    }
    public String 该枪配置的弹夹Get(ItemStack Gun){
        ItemMeta itemMeta = Gun.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        if (per.has(枪配装的弹夹)){
            return per.get(枪配装的弹夹,PersistentDataType.STRING);
        }
        return "null";
    }


    public void set弹夹标签(ItemStack itemStack,String Type){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(弹夹标签,PersistentDataType.STRING, Type);
        itemStack.setItemMeta(itemMeta);
    }
    public String get弹夹标签(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        if (per.has(弹夹标签)){
            return per.get(弹夹标签,PersistentDataType.STRING);
        }
        return "null";
    }
    public void set机枪标签(ItemStack itemStack,String Type){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(机枪标签,PersistentDataType.STRING, Type);
        itemStack.setItemMeta(itemMeta);
    }
    public void 进度条记录(ItemStack itemStack,String Type){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(K,PersistentDataType.STRING,Type);
        itemStack.setItemMeta(itemMeta);
    }
    public String 获取进度条(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        if (per.has(K)){
            return per.get(K,PersistentDataType.STRING);
        }
        return "null";
    }
    public void 删除进度条(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        if (per.has(K) == false){
            return;
        }
        per.remove(K);
        itemStack.setItemMeta(itemMeta);
    }


    public boolean 检测该物品(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        return per.has(机枪标签);
    }
    public String 返回该枪的机枪标签(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        return per.get(机枪标签,PersistentDataType.STRING);
    }



    public List<String> Lores(String[] s){
        List<String> a = new ArrayList<>();
        for (int i = 0;i< s.length;i++){
            a.add(s[i]);
        }
        return a;
    }
    public void 玩家展示信息(String s, Player player){
        BaseComponent[] baseComponents = new ComponentBuilder().append(s).create();
        player.sendMessage(ChatMessageType.ACTION_BAR,baseComponents);
    }

    /**
    *
     * @param itemStack
     * @param a 初始化子弹数
     */
    public void 子弹初始化(ItemStack itemStack,int a){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        per.set(S,PersistentDataType.INTEGER,a);
        itemStack.setItemMeta(itemMeta);
    }
    public void 子弹发射减掉操作(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer per = itemMeta.getPersistentDataContainer();
        int a = per.get(S,PersistentDataType.INTEGER);
        a--;
        per.set(S,PersistentDataType.INTEGER,a);
        itemStack.setItemMeta(itemMeta);
    }
    public int 子弹容量查(ItemStack itemStack){
        PersistentDataContainer per = itemStack.getItemMeta().getPersistentDataContainer();
        return per.get(S,PersistentDataType.INTEGER);
    }



}
