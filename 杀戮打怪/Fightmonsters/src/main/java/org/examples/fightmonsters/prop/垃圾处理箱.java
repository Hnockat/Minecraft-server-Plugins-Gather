package org.examples.fightmonsters.prop;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.examples.fightmonsters.Fightmonsters;

import java.awt.geom.QuadCurve2D;
import java.util.List;
import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class 垃圾处理箱 {
    public static 垃圾处理箱 垃圾处理箱_class= new 垃圾处理箱();
    public static Inventory 垃圾处理箱_home =Bukkit.createInventory(null,9*5,"垃圾处理箱_购买");
    public static Inventory 垃圾处理箱_01 =Bukkit.createInventory(null,9*6,"垃圾处理箱_01");
    public static Inventory 垃圾处理箱_02 =Bukkit.createInventory(null,9*6,"垃圾处理箱_02");
    public static final NamespacedKey Chest_Home = new NamespacedKey(Fightmonsters.getPlugin(Fightmonsters.class),"Chest_Home");
    public static boolean 是否已经购买 = false;
    public void 招牌(World world)
    {
        List<Double> 垃圾处理箱list = (List<Double>) Fightmonsters.垃圾处理箱.Yml("List","箱子设置.介绍牌位置");
        Location loc = new Location(world,垃圾处理箱list.get(0),
                垃圾处理箱list.get(1),
                垃圾处理箱list.get(2));
        ArmorStand armorStand = (ArmorStand)world.spawnEntity(loc,ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.name"));
        armorStand.setCustomNameVisible(true); //设置名字可见*//*
        armorStand.setVisible(false);
    }
    public void 箱子满了招牌(World world){
        List<Double> 垃圾处理箱list = (List<Double>) Fightmonsters.垃圾处理箱.Yml("List","箱子设置.介绍牌位置");
        Location loc = new Location(world,垃圾处理箱list.get(0),
                垃圾处理箱list.get(1),
                垃圾处理箱list.get(2));
        ArmorStand armorStand = (ArmorStand)world.spawnEntity(loc,ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.箱子满name"));
        armorStand.setCustomNameVisible(true); //设置名字可见*//*
        armorStand.setVisible(false);
    }

    public void 垃圾处理箱初始化(World world)
    {
        招牌(world);
        //加载箱子 购买
        ItemStack Black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 垃圾箱购买 = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = 垃圾箱购买.getItemMeta();
        itemMeta.setDisplayName((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.购买介绍.name"));
        List<String> 支付list = (List<String>)Fightmonsters.垃圾处理箱.Yml("List","箱子设置.购买介绍.lore");
        支付list.add(ChatColor.BLUE +"你需要支付: "+ChatColor.GREEN+Fightmonsters.垃圾处理箱.Yml("int","箱子设置.Pay.Price"));
        itemMeta.setLore(支付list);
        垃圾箱购买.setItemMeta(itemMeta);
        垃圾处理箱_home.setItem(22,垃圾箱购买);
        for (int i = 0; i<=8;i++)
        {
            垃圾处理箱_home.setItem(i,Black_glass);
            垃圾处理箱_home.setItem(i+36,Black_glass);
        }
        for (int i = 1;i<=7;i++){
            垃圾处理箱.垃圾处理箱_01.setItem(i+45,Black_glass);
            垃圾处理箱.垃圾处理箱_02.setItem(i+45,Black_glass);
        }
        //容器set
        ItemStack it_01 = new ItemStack(Material.CHEST);
        ItemMeta itemMeta1 = it_01.getItemMeta();
        itemMeta1.setDisplayName(ChatColor.BLUE+"点击下一页"+ChatColor.GREEN+" [1/2]");
        it_01.setItemMeta(itemMeta1);
        垃圾处理箱.垃圾处理箱_01.setItem(53,it_01);
        //容器set
        ItemStack it_02 = new ItemStack(Material.SHULKER_SHELL);
        ItemMeta itemMeta2 = it_02.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.BLUE+"点击上一页"+ChatColor.GREEN+" [2/2]");
        it_02.setItemMeta(itemMeta2);
        垃圾处理箱.垃圾处理箱_02.setItem(53,it_02);
        //容器设置
        ItemStack 清除物品 = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta itemMeta清除物品= 清除物品.getItemMeta();
        itemMeta清除物品.setDisplayName(ChatColor.RED+"清除垃圾箱里面§m所有物品");
        清除物品.setItemMeta(itemMeta清除物品);

        垃圾处理箱.垃圾处理箱_01.setItem(45,清除物品);
        垃圾处理箱.垃圾处理箱_02.setItem(45,清除物品);
    }
    public void 清除垃圾箱(World world)
    {
        if (this.容器是否已经满了()){
            for (Entity entity : world.getEntities()){
                if (entity.getName().equals((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.箱子满name")))
                {
                    entity.remove();
                }
            }
            招牌(world);
        }
        ItemStack Black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        垃圾处理箱_01.clear();
        垃圾处理箱_02.clear();
        for (int i = 1;i<=7;i++){
            垃圾处理箱.垃圾处理箱_01.setItem(i+45,Black_glass);
            垃圾处理箱.垃圾处理箱_02.setItem(i+45,Black_glass);
        }
        //容器set
        ItemStack it_01 = new ItemStack(Material.CHEST);
        ItemMeta itemMeta1 = it_01.getItemMeta();
        itemMeta1.setDisplayName(ChatColor.BLUE+"点击下一页"+ChatColor.GREEN+" [1/2]");
        it_01.setItemMeta(itemMeta1);
        垃圾处理箱.垃圾处理箱_01.setItem(53,it_01);
        //容器set
        ItemStack it_02 = new ItemStack(Material.SHULKER_SHELL);
        ItemMeta itemMeta2 = it_02.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.BLUE+"点击上一页"+ChatColor.GREEN+" [2/2]");
        it_02.setItemMeta(itemMeta2);
        垃圾处理箱.垃圾处理箱_02.setItem(53,it_02);
        //容器设置
        ItemStack 清除物品 = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta itemMeta清除物品= 清除物品.getItemMeta();
        itemMeta清除物品.setDisplayName(ChatColor.RED+"清除垃圾箱里面§m所有物品");
        清除物品.setItemMeta(itemMeta清除物品);

        垃圾处理箱.垃圾处理箱_01.setItem(45,清除物品);
        垃圾处理箱.垃圾处理箱_02.setItem(45,清除物品);
    }
    /***
     *
     * @param list_ItemStack 怪物死亡掉落的物品集合
     * @return 返回类型代表 集体容器是否已经满了
     */
    public boolean 垃圾箱添加垃圾(List<ItemStack> list_ItemStack,World world)
    {
            for (ItemStack itemStack: list_ItemStack)
            {
                if (垃圾处理箱_01.firstEmpty() != -1)
                {
                    垃圾处理箱_01.addItem(itemStack);
                }else{
                    if (垃圾处理箱_02.firstEmpty() != -1){
                        垃圾处理箱_02.addItem(itemStack);
                    }else{
                        for (Entity entity:world.getEntities()){
                            if (entity.getName().equals((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.箱子满name")))
                            {
                                return false;
                            }
                            if (entity.getName().equals((String) Fightmonsters.垃圾处理箱.Yml("String","箱子设置.name"))){
                                this.箱子满了招牌(world);
                                entity.remove();
                                return false;
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
    }
    public boolean 容器是否已经满了(){
        if (垃圾处理箱_01.firstEmpty() == -1){
            if (垃圾处理箱_02.firstEmpty() == -1 ){
                return true;
            }
        }
        return false;
    }
}
