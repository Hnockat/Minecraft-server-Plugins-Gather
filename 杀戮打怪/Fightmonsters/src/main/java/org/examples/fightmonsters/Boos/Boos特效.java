package org.examples.fightmonsters.Boos;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.examples.fightmonsters.Fightmonsters;
public class Boos特效 {
    public static Boos特效 boos特效class = new Boos特效();
    public void 选择(String String,Entity entity){
        switch (String)
        {
            case "01":{
                boos特效_生气(entity);
                return;
            }
        }
    }
    private void boos特效_生气(Entity entity){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class),bukkitTask -> {
            if (entity.isDead())bukkitTask.cancel();
            Location location = entity.getLocation();
            location.setY(location.y()+entity.getHeight());
            entity.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, location, 3);
        },0L,20L);
    }
    /*private void boos闪电(Entity entity){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class),bukkitTask -> {
            if (entity.isDead())bukkitTask.cancel();
            Location location = entity.getLocation();
            location.setY(location.y()+entity.getHeight());
        },0L,20L);
    }*/

}
