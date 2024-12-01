package org.werewolf;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class 监听器 implements Listener {
    @EventHandler
    public void 玩家丢东西(PlayerDropItemEvent event){
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void PlayerPickupArrowEvent(PlayerPickupArrowEvent event)
    {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void Die(PlayerDeathEvent event)
    {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world")){
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(Werewolf.getPlugin(Werewolf.class), () ->
            {
                player.teleport(主大厅.主大厅class.返回主大厅());
                player.setGameMode(GameMode.SURVIVAL);
            });
        }
    }
    @EventHandler
    public void 关闭破坏方块(BlockBreakEvent event){
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
        return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void 关闭放置方块(BlockPlaceEvent event){
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SPECTATOR)){
            player.setGameMode(GameMode.SURVIVAL);
        }
        player.sendMessage(ChatColor.BLUE+"--------------------游戏规则升级-------------------");
        player.sendMessage(ChatColor.BLUE+" 1. 10金换弓,换完弓 再拿10金换箭");
        player.sendMessage(ChatColor.BLUE+" 2. 新增加钻石,该钻石需要玩家点击钻石来购买道具");
        player.sendMessage(ChatColor.BLUE+" 3. 狼人可以拿钻石购买指南针指人");
        player.sendMessage(ChatColor.BLUE+"-------------------------------------------------");
        player.getInventory().clear();
        player.clearActivePotionEffects();
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20*99999999,1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,20*99999999,1));
        player.teleport(主大厅.主大厅class.返回主大厅());
        player.getInventory().setItem(0,主大厅.主大厅class.大厅菜单获取());
        记分板.记分板.创建服务器介绍记分板(player);
    }
    @EventHandler
    public void 玩家点击(PlayerInteractEvent event)
    {
        if (event.getItem() == null){
            return;
        }
        if (event.getItem().getType().equals(Material.COMPASS)){
            if (event.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"[右键点击]选择房间")){
                event.getPlayer().openInventory(主大厅.inventory);
            }
        }
    }
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals("地图选择")){
            event.setCancelled(true);
            if (event.getRawSlot() == 0){
                World world = Bukkit.getWorld("01");
                if (游戏管理器.GameList.containsKey(world)){
                    player.sendMessage(ChatColor.RED+"该房间正在游戏中!!!");
                    return;
                }
                List<String> list = (List<String>) Werewolf._01.Yml("List","出生位置");
                Location location = new Location(world,Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));
                player.teleport(location);
                return;
            }
            if (event.getRawSlot() == 1){
                World world = Bukkit.getWorld("02");
                if (游戏管理器.GameList.containsKey(world)){
                    player.sendMessage(ChatColor.RED+"该房间正在游戏中!!!");
                    return;
                }
                List<String> list = (List<String>) Werewolf._02.Yml("List","出生位置");
                Location location = new Location(world,Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));
                player.teleport(location);
                return;
            }
            if (event.getRawSlot() == 2){
                World world = Bukkit.getWorld("03");
                if (游戏管理器.GameList.containsKey(world)){
                    player.sendMessage(ChatColor.RED+"该房间正在游戏中!!!");
                    return;
                }
                List<String> list = (List<String>) Werewolf._03.Yml("List","出生位置");
                Location location = new Location(world,Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));
                player.teleport(location);
                return;
            }
            return;
        }
    }
}
