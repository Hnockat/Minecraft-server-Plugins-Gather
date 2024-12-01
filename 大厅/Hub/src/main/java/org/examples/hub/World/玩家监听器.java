package org.examples.hub.World;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;import org.bukkit.event.block.BlockBreakEvent;
import org.examples.hub.Hub;
import org.examples.hub.Join.主大厅;
import org.examples.hub.ScoreboardSet.记分板;
import org.examples.hub.菜单.游戏菜单;

public class 玩家监听器 implements Listener {
    @EventHandler
    public void 玩家加入游戏(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        player.getInventory().clear();
        游戏菜单.游戏菜单class.菜单给以(player);
        主大厅.主大厅class.Join(player);
        记分板.记分板class.创建玩家记分板主大厅(player);

    }
    @EventHandler
    public void 玩家死亡事件(PlayerDeathEvent event)
    {
        event.setCancelled(true);
        Bukkit.getScheduler().runTask(Hub.getPlugin(Hub.class), bukkitTask ->
        {
            主大厅.主大厅class.Join(event.getPlayer());
        });
    }
    @EventHandler
    public void 玩家切换世界操作(PlayerChangedWorldEvent event)
    {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world")){
            游戏菜单.游戏菜单class.菜单给以(player);
            记分板.记分板class.创建玩家记分板主大厅(player);
        }
    }
    //@EventHandler
    public void 玩家退出服务器(PlayerQuitEvent event){
         //退出线程操作!
    }
    @EventHandler
    public void 取消玩家操作背包容器(InventoryClickEvent event){
        if (event.getView().getTitle().equals("Crafting"))
        {
            Player player = (Player) event.getWhoClicked();
            if (player.getGameMode().equals(GameMode.CREATIVE)){
                return;
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void 取消玩家丢东西(PlayerDropItemEvent event){
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void 取消玩家破坏方块(BlockBreakEvent event)
    {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void 取消玩家放置方块(BlockPlaceEvent event)
    {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        event.setCancelled(true);
    }
}
