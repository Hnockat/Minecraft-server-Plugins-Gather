package org.examples.hub.跑酷;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class 压力板检测 implements Listener {
    @EventHandler
    public void 压力板检测(PlayerInteractEvent event){
        if (event.getAction() != Action.PHYSICAL){
            return;
        }
        //OAK_PRESSURE_PLATE 木压力板
        //LIGHT_WEIGHTED_PRESSURE_PLATE 金压力板
        //HEAVY_WEIGHTED_PRESSURE_PLATE 铁压力板

        //踩到木制压力板  让玩家已一定的向量飞起来
        if (event.getClickedBlock().getType() == Material.OAK_PRESSURE_PLATE){
            event.getPlayer().setVelocity(new Vector(2,1.2,0));
            return;
        }

        //踩到金制 压力板 开始跑酷
        if (event.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE){

        }


    }


}
