package org.gun;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class 机枪监听器 implements Listener {
    @EventHandler
    public static void 玩家丢弃枪(PlayerDropItemEvent event){
        if (机枪类.正在安装弹夹的玩家.contains(event.getPlayer())) {
            if (NBT.NbtSet.检测该物品(event.getItemDrop().getItemStack())){
                NBT.NbtSet.进度条记录(event.getItemDrop().getItemStack(), "null");
            }
            return;
        }
    }
    @EventHandler
    public static void 玩家射击动作(PlayerInteractEvent event){
        if (event.getItem() == null){
            return;
        }
        if (!NBT.NbtSet.检测该物品(event.getItem())){
            return;
        }
        Player player = event.getPlayer();
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
            机枪类.GetThis.玩家点击发射键(player);
        }
        if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            机枪类.GetThis.玩家点击装弹(player);
        }

    }
   @EventHandler
    public static void players(EntityDamageByEntityEvent event){
        if (!event.getDamager().getName().contains("&")){
            return;
        }
        String str[] = event.getDamager().getName().split(" ");
       if (Gun.GetYml_机枪类型.获取该节点的所有子节点名字集合("机枪类型").contains(str[0])){
           LivingEntity living = (LivingEntity) event.getEntity();
           if (living.getMaximumNoDamageTicks() != 0){
               living.setMaximumNoDamageTicks(0);
           }
           event.setDamage((double) Gun.GetYml_机枪类型.Yml("double", "机枪类型." + str[0] + ".单发伤害持续")+(double) Gun.GetYml_子弹.Yml("double",str[2]+".初始伤害"));
       }
    }

    @EventHandler
    public void 子弹取消僵直时间(ProjectileHitEvent event) {
        if (!event.getEntity().getName().contains("&")){
            return;
        }
        if (Gun.GetYml_机枪类型.获取该节点的所有子节点名字集合("机枪类型").contains(event.getEntity().getName().split(" ").clone()[0])){
            if (event.getHitBlock() != null) {
                if (子弹.子弹class.玻璃系列破坏.contains(event.getHitBlock().getType().name())){
                    event.getHitBlock().setType(Material.AIR);
                    event.getHitBlock().getWorld().playSound(event.getHitBlock().getLocation(), Sound.valueOf((String) Gun.GetYml_子弹破坏方块列表.Yml("String","GLASS.Sound")), 1, 1);
                    return;
                }
                return;
            }//block break
        }
    }

}
