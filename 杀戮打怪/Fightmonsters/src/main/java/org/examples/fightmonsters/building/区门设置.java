package org.examples.fightmonsters.building;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Shop.普通商人商品设置;
import org.examples.fightmonsters.prop.浮空文字;

import java.util.List;
import static org.bukkit.Material.*;
import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class 区门设置 {
    public static Inventory 打开普通商店区容器 = Bukkit.createInventory(null,9*5,"解锁普通商店区");
    public static Inventory 打开工匠商店区容器 = Bukkit.createInventory(null,9*5,"解锁工匠商店区");
    public static Inventory 打开水族馆区容器 = Bukkit.createInventory(null,9*5,"解锁水族馆区");
    public static boolean 普通商店区是否打开 = false;
   public static void 加载普通商店区容器(){
        ItemStack Black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 普通商店区支付 = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = 普通商店区支付.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN+"[点击购买]普通商店区");
        普通商店区支付.setItemMeta(itemMeta);
        打开普通商店区容器.setItem(22,普通商店区支付);
        for (int i = 0; i<=8;i++)
        {
            打开普通商店区容器.setItem(i,Black_glass);
           打开普通商店区容器.setItem(i+36,Black_glass);
        }
    }
    public static void 加载工匠商店区容器(){
        ItemStack Black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 工匠商店区 = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = 工匠商店区.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN+"[点击购买]工匠商店区");
        工匠商店区.setItemMeta(itemMeta);
        打开工匠商店区容器.setItem(22,工匠商店区);
        for (int i = 0; i<=8;i++)
        {
            打开工匠商店区容器.setItem(i,Black_glass);
            打开工匠商店区容器.setItem(i+36,Black_glass);
        }
    }
    public static void 加载水族馆区容器(){
        ItemStack Black_glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack 水族馆区容器 = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = 水族馆区容器.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN+"[点击购买]水族馆区");
        水族馆区容器.setItemMeta(itemMeta);
        打开水族馆区容器.setItem(22,水族馆区容器);
        for (int i = 0; i<=8;i++)
        {
            打开水族馆区容器.setItem(i,Black_glass);
            打开水族馆区容器.setItem(i+36,Black_glass);
        }
    }
    public void 区门设置生成实体(World world){
        ItemStack PIGLINHEAD = new ItemStack(GOLDEN_APPLE);

        List<Double> list_普通商店区 = (List<Double>)Fightmonsters.区门设置.Yml("List","普通商店区.介绍.位置");
        double yam =  list_普通商店区.get(3);
        double pitch = list_普通商店区.get(4);
        Location loc = new Location(world,list_普通商店区.get(0),
                list_普通商店区.get(1),
                list_普通商店区.get(2),(float) yam,(float)pitch);
        ArmorStand armorStand = (ArmorStand)world.spawnEntity(loc,ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"));
        armorStand.setCustomNameVisible(true); //设置名字可见*//*
        armorStand.setVisible(false);
        EntityEquipment en = armorStand.getEquipment();
        en.setChestplate(PIGLINHEAD);
        en.setLeggings(PIGLINHEAD);
        en.setBoots(PIGLINHEAD);


        List<Double> list_工匠商店区 = (List<Double>)Fightmonsters.区门设置.Yml("List","工匠商店区.介绍.位置");
        double yam_工匠商店区 =  list_工匠商店区.get(3);
        double pitch_工匠商店区 = list_工匠商店区.get(4);
        Location loc_工匠商店区 = new Location(world,list_工匠商店区.get(0),
                list_工匠商店区.get(1),
                list_工匠商店区.get(2),(float) yam_工匠商店区,(float)pitch_工匠商店区);
        ArmorStand armorStand_list_工匠商店区 = (ArmorStand)world.spawnEntity(loc_工匠商店区,ARMOR_STAND);
        armorStand_list_工匠商店区.setGravity(false);
        armorStand_list_工匠商店区.setInvulnerable(true);
        armorStand_list_工匠商店区.setCustomName((String) Fightmonsters.区门设置.Yml("String","工匠商店区.介绍.name"));
        armorStand_list_工匠商店区.setCustomNameVisible(true); //设置名字可见*//*
        armorStand_list_工匠商店区.setVisible(false);
        EntityEquipment en_armorStand_list_工匠商店区 = armorStand_list_工匠商店区.getEquipment();
        en_armorStand_list_工匠商店区.setChestplate(PIGLINHEAD);
        en_armorStand_list_工匠商店区.setLeggings(PIGLINHEAD);
        en_armorStand_list_工匠商店区.setBoots(PIGLINHEAD);

        List<Double> list_水源区 = (List<Double>)Fightmonsters.区门设置.Yml("List","水源区.介绍.位置");
        double yam_水源区 =  list_水源区.get(3);
        double pitch_水源区 = list_水源区.get(4);
        Location loc_list_水源区 = new Location(world,list_水源区.get(0),
                list_水源区.get(1),
                list_水源区.get(2),(float) yam_水源区,(float)pitch_水源区);
        ArmorStand armorStand_list_水源区 = (ArmorStand)world.spawnEntity(loc_list_水源区,ARMOR_STAND);
        armorStand_list_水源区.setGravity(false);
        armorStand_list_水源区.setInvulnerable(true);
        armorStand_list_水源区.setCustomName((String) Fightmonsters.区门设置.Yml("String","水源区.介绍.name"));
        armorStand_list_水源区.setCustomNameVisible(true); //设置名字可见*//*
        armorStand_list_水源区.setVisible(false);
        EntityEquipment en_armorStand_list_水源区 = armorStand_list_水源区.getEquipment();
        en_armorStand_list_水源区.setChestplate(PIGLINHEAD);
        en_armorStand_list_水源区.setLeggings(PIGLINHEAD);
        en_armorStand_list_水源区.setBoots(PIGLINHEAD);
    }

    public void 打怪开始禁止进入商店区(World world)
    {
        List<Integer> list_y = (List<Integer>) Fightmonsters.区门设置.Yml("List","普通商店区.y");
        List<Integer> list_z = (List<Integer>) Fightmonsters.区门设置.Yml("List","普通商店区.z");
        int x = (int)Fightmonsters.区门设置.Yml("int","普通商店区.x");
        for (int y:list_y)
        {
            for (int z: list_z)
            {
                Block block = world.getBlockAt(x,y,z);
                block.setType(Material.RED_WOOL);
            }
        }
            List<Double> list_普通商店区 = (List<Double>)Fightmonsters.区门设置.Yml("List","普通商店区.介绍.位置");
            double yam =  list_普通商店区.get(3);
            double pitch = list_普通商店区.get(4);
            Location loc = new Location(world,list_普通商店区.get(0),
                    list_普通商店区.get(1),
                    list_普通商店区.get(2),(float) yam,(float)pitch);
            ArmorStand armorStand = (ArmorStand)world.spawnEntity(loc,ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setInvulnerable(true);
            armorStand.setCustomName((String) Fightmonsters.区门设置.Yml("String","打怪时设置.name"));
            armorStand.setCustomNameVisible(true); //设置名字可见*//*
            armorStand.setVisible(false);
    }


    public void 开启商店区(World world){
        List<Integer> list_y = (List<Integer>) Fightmonsters.区门设置.Yml("List","普通商店区.y");
        List<Integer> list_z = (List<Integer>) Fightmonsters.区门设置.Yml("List","普通商店区.z");
        int x = (int)Fightmonsters.区门设置.Yml("int","普通商店区.x");
        for (int y:list_y)
        {
            for (int z: list_z)
            {
                Block block = world.getBlockAt(x,y,z);
                block.setType(Material.AIR);
            }
        }
        for (Entity entity:world.getEntities())
        {
            if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"))){
                entity.remove();
            }
        }
    }
    public void 开启工匠区(World world)
    {
        List<Integer> list_x = (List<Integer>) Fightmonsters.区门设置.Yml("List","工匠商店区.x");
        List<Integer> list_y = (List<Integer>) Fightmonsters.区门设置.Yml("List","工匠商店区.y");
        int z = (int)Fightmonsters.区门设置.Yml("int","工匠商店区.z");
        for (int y:list_y)
        {
            for (int x: list_x)
            {
                Block block = world.getBlockAt(x,y,z);
                block.setType(Material.AIR);
            }
        }
        for (Entity entity:world.getEntities())
        {
            if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","工匠商店区.介绍.name"))){
                entity.remove();
            }
        }
    }
    public void 开启水源区(World world)
    {
        List<Integer> list_x = (List<Integer>) Fightmonsters.区门设置.Yml("List","水源区.x");
        List<Integer> list_y = (List<Integer>) Fightmonsters.区门设置.Yml("List","水源区.y");
        int z = (int)Fightmonsters.区门设置.Yml("int","水源区.z");
        for (int y:list_y)
        {
            for (int x: list_x)
            {
                Block block = world.getBlockAt(x,y,z);
                block.setType(Material.AIR);
            }
        }
        for (Entity entity:world.getEntities())
        {
            if (entity.getName().equals((String) Fightmonsters.区门设置.Yml("String","水源区.介绍.name"))){
                entity.remove();
            }
        }
    }


}
