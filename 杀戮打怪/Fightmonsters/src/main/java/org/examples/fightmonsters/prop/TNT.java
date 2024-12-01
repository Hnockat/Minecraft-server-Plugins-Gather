package org.examples.fightmonsters.prop;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;


public class TNT implements Listener {
    @EventHandler
    public void 玩家投掷tnt(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (event.getItem() == null){
            return;
        }
        if (!event.getItem().getType().equals(Material.TNT)) return;

        if (event.getAction() == Action.LEFT_CLICK_AIR)
        {
            if (!(player.getGameMode() == GameMode.CREATIVE)){
                event.getItem().setAmount(event.getItem().getAmount() -1);
            }
            TNTPrimed tnt = player.getWorld().spawn(player.getLocation().add(0,1,0),TNTPrimed.class);
            @NotNull Vector vector = player.getLocation().getDirection().multiply(1.2);
            tnt.setVelocity(vector);
            tnt.setSource(player);
            tnt.setFuseTicks(30);
            return;
        }
    }
    @EventHandler
    public void 爆炸方块操作(EntityExplodeEvent event)
    {
        if (event.getEntity().getType().equals(EntityType.TNT)){
            event.setCancelled(true);
        }
        if (event.getEntity().getType().equals(EntityType.END_CRYSTAL)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void 爆炸操作(ExplosionPrimeEvent event)
    {
        if (event.getEntityType() == EntityType.END_CRYSTAL){
            Location loc = event.getEntity().getLocation();
            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION,loc,15);
            event.getEntity().getWorld().playSound(event.getEntity().getLocation(),Sound.ENTITY_GENERIC_EXPLODE,1,1);
            return;
        }
        if (event.getEntityType() == EntityType.TNT)
        {
            Location loc = event.getEntity().getLocation();
            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION,loc,15);
            event.getEntity().getWorld().playSound(event.getEntity().getLocation(),Sound.ENTITY_GENERIC_EXPLODE,1,1);
            return;
        }
    }
    @EventHandler
    public void 玩家放置方块(BlockPlaceEvent event)
    {
        if (!event.getBlock().getType().equals(Material.TNT)){
            return;
        }
        event.setCancelled(true);
        Player player = event.getPlayer();
        if (!(player.getGameMode() == GameMode.CREATIVE)){
            player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() -1);
        }
        TNTPrimed tnt = player.getWorld().spawn(event.getBlock().getLocation(),TNTPrimed.class);
        tnt.setFuseTicks(30);
        tnt.setSource(player);
    }
}
