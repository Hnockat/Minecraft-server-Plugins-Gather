package org.examples.hub.菜单;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.examples.hub.Hub;
import org.examples.hub.redis.Redis;

import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class 菜单监听器 implements Listener {

    @EventHandler
    public void 玩家右键打开菜单(PlayerInteractEvent event){
       if (event.getItem() == null){
           return;
       }
       //打开游戏菜单
        if (event.getAction().equals(RIGHT_CLICK_AIR)||event.getAction().equals(RIGHT_CLICK_BLOCK))
        {
            if (event.getItem().getType().equals(Material.valueOf((String) Hub.游戏菜单配置文件.Yml("String","菜单属性.Item"))))
            {
                if (event.getItem().getItemMeta().getDisplayName().equals((String) Hub.游戏菜单配置文件.Yml("String","菜单属性.name")))
                {
                    event.getPlayer().openInventory(游戏菜单.游戏菜单);
                }
            }
        }
    }
    @EventHandler
    public void 点击格子(InventoryClickEvent event)
    {
        if (event.getView().getTitle().equals("游戏菜单"))
        {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            for (int a=0;;a++)
            {
                if (Hub.游戏菜单配置文件.contains("Item_"+a+".RawSlot")){
                    if ((int)Hub.游戏菜单配置文件.Yml("int","Item_"+a+".RawSlot") == event.getRawSlot())
                    {
                        //获得server传送
                        Redis.rediscalss.跨服传送((String)Hub.游戏菜单配置文件.Yml("String","Item_"+a+".Server"),player.getName());
                    }
                }else{
                    return;
                }

            }

        }
    }
}
