package org.gun;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class a implements Listener {
    @EventHandler
    public static void TNT投掷(PlayerInteractEvent e){
        if (!(e.getItem() != null && e.getItem().getType().equals(Material.TNT))){
            return;
        }
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            return;
        }
        ItemStack itemStack = e.getItem();
        itemStack.setAmount(itemStack.getAmount() - 1);
        TNTPrimed tnt = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), TNTPrimed.class);
        tnt.setFuseTicks(20*3);
        tnt.setGlowing(true);
        tnt.setSource(e.getPlayer());
        @NotNull Vector vector = e.getPlayer().getLocation().getDirection();
        vector.setY(vector.getY() + 0.3);
        vector.multiply(1.1);
        tnt.setVelocity(vector);

    }
    @EventHandler
    public static void TNT爆炸(ExplosionPrimeEvent event){
        if (event.getEntityType() != EntityType.TNT){
            return;
        }
        TNTPrimed tntPrimed = (TNTPrimed)event.getEntity();
        for(LivingEntity living: tntPrimed.getWorld().getEntitiesByClass(LivingEntity.class)){
            if (living.getLocation().distance(tntPrimed.getLocation())<= event.getRadius()){
                living.damage(5);
            }
        }
        event.getEntity().getWorld().playSound(event.getEntity().getLocation(),Sound.ENTITY_GENERIC_EXPLODE,1,1);
    }


}

