package org.entitySet;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.boss.wither.EntityWither;
import org.bukkit.craftbukkit.v1_21_R1.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public final class EntitySet extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);
        Player player;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void 铁傀儡主动攻击玩家(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();
        if (entity instanceof IronGolem pig) {
            EntityIronGolem entityPig = ((CraftIronGolem) pig).getHandle();
            PathfinderGoalSelector targetGoalSelector = entityPig.bW;
            Set<PathfinderGoalWrapped> pathfinderGoalWrappeds = targetGoalSelector.b();
            pathfinderGoalWrappeds.add(new PathfinderGoalWrapped(1,new PathfinderGoalNearestAttackableTarget<>(entityPig, EntityPlayer.class,false)));
            return;
        }
        if (entity instanceof Wither wither){
            EntityWither entityWither = ((CraftWither) wither).getHandle();
            PathfinderGoalSelector targetGoalSeletctor = entityWither.bW;
            int speed = 40;
            if (wither.getName().equals("§5凋零风暴")){
                speed = 6;
            }
            Set<PathfinderGoalWrapped> pathfinderGoalWrappeds = targetGoalSeletctor.b();
            pathfinderGoalWrappeds.add(new PathfinderGoalWrapped(1,new PathfinderGoalArrowAttack(entityWither,1,6,20.0F)));
            return;
        }
    }
    //受伤反馈
    @EventHandler
    public void 受伤处理(EntityDamageByEntityEvent event){
        if (event.getDamager().getName().equals("Wither Skull")){
            LivingEntity entity = (LivingEntity)event.getEntity();
            Collection<PotionEffect> Potions = new ArrayList<>();
            Potions.add(new PotionEffect(PotionEffectType.WITHER,5*20,1,false,false));
            Potions.add(new PotionEffect(PotionEffectType.NAUSEA,2*20,1,false,false));
            Potions.add(new PotionEffect(PotionEffectType.SLOWNESS,5*20,1,false,false));
            Potions.add(new PotionEffect(PotionEffectType.WEAKNESS,3*20,1,false,false));
            entity.addPotionEffects(Potions);
            event.setDamage(8);
        }
    }

}
