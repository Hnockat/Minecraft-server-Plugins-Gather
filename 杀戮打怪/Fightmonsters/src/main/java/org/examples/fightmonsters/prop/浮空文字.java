package org.examples.fightmonsters.prop;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.examples.fightmonsters.Fightmonsters;

import static org.bukkit.Material.GOLDEN_APPLE;
import static org.bukkit.entity.EntityType.ARMOR_STAND;

public class 浮空文字 {
    public static 浮空文字 classe文字 = new 浮空文字();
    public void 设置浮空文字(String 文字, Location 位置,boolean 是否可以点击){
        ArmorStand armorStand_ = (ArmorStand)位置.getWorld().spawnEntity(位置,ARMOR_STAND);
        armorStand_.setGravity(false);
        armorStand_.setInvulnerable(true);
        armorStand_.setCustomName(文字);
        armorStand_.setCustomNameVisible(true); //设置名字可见*//*
        armorStand_.setVisible(false);
        //是否可以点击
        if (是否可以点击){
            EntityEquipment st = armorStand_.getEquipment();
            ItemStack PIGLINHEAD = new ItemStack(GOLDEN_APPLE);
            st.setChestplate(PIGLINHEAD);
            st.setLeggings(PIGLINHEAD);
            st.setBoots(PIGLINHEAD);
        }
    }

}
