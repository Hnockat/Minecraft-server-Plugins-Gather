package org.examples.fightmonsters.工匠;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.attribute.Attribute;
import org.examples.fightmonsters.Fightmonsters;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.getMaterial;

public class 工匠升级 implements Listener {
    public static 工匠升级 工匠升级class = new 工匠升级();
    public static Inventory 工匠升级菜单 = Bukkit.createInventory(null,9*6,"工匠升级");


    public void 容器初始化(){
        ItemStack 玻璃板 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for (int i = 0; i < 9*6;i++){
            工匠升级菜单.setItem(i,玻璃板);
        }
        工匠升级菜单.setItem(22, ItemStack.of(Material.AIR));

        ItemStack 返回 = new ItemStack(Material.BAMBOO_CHEST_RAFT);
        ItemMeta 返回Meta = 返回.getItemMeta();
        返回Meta.setDisplayName(ChatColor.BLUE+"返回到主页面");
        返回.setItemMeta(返回Meta);
        工匠升级菜单.setItem(53,返回);
    }

    // # 等级方案: Level_1 <> <>
    public String 返回该物品升级方案(ItemStack itemStack){
        List<String> 升级参数 = new ArrayList<>();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> Lore = itemMeta.getLore();
        if (工匠文件处理.工匠升级.contains(itemStack.getType().name()+"")){
            for (int i = 0;i <= 5;i++){ //获取升级列表
                if (工匠文件处理.工匠升级.contains(itemStack.getType().name()+".升级."+i+"等级")){

                }
                else if (工匠文件处理.工匠升级.contains(itemStack.getType().name()+".升级."+i))
                {
                    String arr = (String) 工匠文件处理.工匠升级.Yml("String",itemStack.getType().name()+".升级."+i+"type");
                    if (Lore.contains(arr)){
                        continue;
                    }
                    升级参数.add(arr);
                }else
                {
                    break; //退出循环
                }
            }

        }else{
            return "false";
        }
        return "false";
    }
    /**
     * 标签Set
     * 该物品的标签 该物品的数值
     *
     *
     *
     */

    /**
     *  主菜单: { 工匠修复 , 工匠升级}
     *  工匠升级:{ //备注:需要给玩家提示有什么装备可以升级
     *  1.
     *      - - - - - - - - -
     *      放入物品检测[/]
     *      - - - - - - - - -
     *  2.
     *     -------------------
     *      1.xxx升级  2.xxx改良
     *      //玩家点击 1/2 来决定升级
     *     -------------------
     *  3. 玩家点击升级 工匠打造该物品 等待 1分钟
     *  4.打造成功 放入打造箱子
     *  }
     *
     *
     *
     *  {触发规则: 监听器  NBT容器存取数据  计数器}
     *
     *   铁器:
     *   1.铁剑   "轻量化" ->  {攻击cd 少     价格:60  [触发: 一直存在]}
     *           "伤害改良" -> {伤害+2       价格:80  [触发: 一直存在]}
     *   2.斧头   "重击"   ->  {伤害增加 +1.6  价格 50  [触发: 暴击触发]}
     *
     *   钻器/下届合金器:
     *   1.钻套
     *
     *   头盔:
     *   :"救急" ->  {[金♥+6血"伤害吸收"],[生命回复3秒]  cd冷却3分钟
     *   备注:[] 才能升级} [玩家血量低到只有6血量触发] 价格: 230 ;
     *
     *   胸甲:
     *   :"血容量" ->  {[最高+20血] [触发一直存在]}
     *   -> {1级: +2血 价格: 80}
     *   -> {2级: +2血 价格: 120}
     *   -> {3级: +2血 价格: 160}
     *   -> {4级: +2血 价格: 180}
     *   -> {5级: +2血 价格: 240}
     *   -> {6级: +2血 价格: 280}
     *   -> {7级: +2血 价格: 300}
     *   -> {8级: +2血 价格: 350}
     *   -> {9级: +2血 价格: 430}
     *   -> {10级:+2血 价格: 500};
     *
     *
     *   2.钻剑
     *   : "轻量化" ->{cd减少} 价格: 200 [触发:一直触发];
     *   : "伤害飞溅" ->{横扫范围增加} 价格: 180 [触发:cd蓄力满触发];
     *
     *   3.钻斧
     *   : "重击" ->
     *   -> 1级: [暴击伤害增加 +1.0] 价格: 150 [触发: 暴击触发]
     *   -> 2级: [暴击伤害增加 +0.9] 价格: 170 [触发: 暴击触发]
     *   -> 3级: [暴击伤害增加 +0.7] 价格: 220 [触发: 暴击触发];
     *   :{ "轻量化" 1级 -> {cd减少到原来的 %20} 价格: 200
     *      "轻量化" 2级 -> {cd减少到原来的 %48} 价格: 480};
     *
     *
     *   其他武器:
     *   1.三叉戟 -> "流血":{触发:投掷刺中怪物}{功能: 怪物持续流血 1秒《-2♥》 持续6秒} 价格:180
     *
     *   2.重锤 ->   "眩晕":{触发:怪物被高空击打触发}{功能: 怪物在1.5秒内不能攻击玩家} 价格:200
     *
     *
     */



    public static boolean 武器升级 (Player player, int 商品)
    {//工匠文件处理.工匠武器升级 YML
        ItemStack itemStack = 工匠升级菜单.getItem(22);
        if (itemStack == null){  //检测容器是否为空
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> Lore = itemMeta.getLore();

        if (工匠文件处理.工匠升级.contains("可升级列表."+itemStack.getType()+".升级."+商品+".等级")){
            //等级升级

        }else
         //单一升级
        {
            String type = (String) 工匠文件处理.工匠升级.Yml("String","可升级列表."+itemStack.getType()+".升级."+商品+".type");
            String[] arr = type.split(" ");
            switch (arr[0]){
                case "轻量化" :
                {
                    /*set属性(itemMeta,Attribute.GENERIC_ATTACK_SPEED,Double.parseDouble(arr[1]));*/
                }
                case "伤害改良" :
                {
                    //set属性(itemMeta,Attribute.GENERIC_ATTACK_DAMAGE,Double.parseDouble(arr[1]));
                }
                case "重击" :
                {
                   //打标签

                }
                case "救急" :
                {
                    //打标签

                }
                case "血容量" :
                {
                    //set属性(itemMeta,Attribute.GENERIC_MAX_HEALTH,Double.parseDouble(arr[1]));
                }
                case "伤害飞溅" :
                {
                    //打标签

                }
                case "流血" :
                {
                    //打标签

                }
                case "眩晕" :
                {
                    //打标签

                }
            }
        }
        return false;
    }


    public static void set属性 (ItemStack 物品,Attribute 属性, double 值)
    {
        ItemMeta itemMeta = 物品.getItemMeta();
        NamespacedKey key = new NamespacedKey(Fightmonsters.getInstance(), "key"); // plugin 是你的 Plugin 对象
        itemMeta.addAttributeModifier(属性, new AttributeModifier(key,值,AttributeModifier.Operation.ADD_NUMBER,getEquipmentSlot(物品)));
        物品.setItemMeta(itemMeta);
    }
    private static EquipmentSlotGroup getEquipmentSlot(ItemStack item) {
        Material material = item.getType();
        if (material == null) {
            return null;
        }
        switch (material) {
            case LEATHER_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:
            case TURTLE_HELMET:
            case NETHERITE_HELMET:
                return EquipmentSlotGroup.HEAD;
            case LEATHER_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
                return EquipmentSlotGroup.CHEST;
            case LEATHER_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case NETHERITE_LEGGINGS:
                return EquipmentSlotGroup.LEGS;
            case LEATHER_BOOTS:
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
            case NETHERITE_BOOTS:
                return EquipmentSlotGroup.FEET;
            default:
                return null; // 如果不是盔甲，则返回null
        }
    }

}
