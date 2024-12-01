package org.werewolf;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;

import static org.werewolf.职业.线程id;

public class 游戏管理器 implements Listener {
    public static 游戏管理器 游戏管理器class = new 游戏管理器();
    //游戏实现伪随机处理
    public static Map<Player,Integer> 狼人次数 = new HashMap<>();
    public static Map<Player,Integer> 好人次数 = new HashMap<>();
    public static HashMap<World,Game> GameList =new HashMap();
    public void 开启一个游戏(World world){
        if (world.getPlayers().size() >= 3){
            Game game = new Game();
            game.Game(world);
            GameList.put(world,game);
        }
    }
    public void 关闭游戏(World world){
        GameList.remove(world);
        主大厅.主大厅class.房间选择器去进行(world);
    }
    public void 旁边告诉(String say,World world){
        for (Player player:world.getPlayers())
        {
            player.sendMessage(say);
        }
    }

    public String 获取地图名字(World world)
    {
        switch (world.getName()){
            case "01":return "盒提杜";
            case "02":return "美术馆";
            case "03":return "体育棺";
        }
        return "null";
    }
    public String 获取至少人数(World world){
        switch (world.getName()){
            case "01":return (String) Werewolf._01.Yml("String","至少人数");
            case "02":return (String) Werewolf._02.Yml("String","至少人数");
            case "03":return (String) Werewolf._03.Yml("String","至少人数");
        }
        return "null";
    }
    @EventHandler
    public void 玩家切换世界(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        if (!player.getWorld().getName().equals("world")){
            if (player.getWorld().getPlayers().size() == 1) {
                System.out.println("abcaaaaaaaaaaaaaaaaaaaaa");
                记分板.记分板.创建等待记分板(event.getPlayer().getWorld());
            }
            return;
        }else{
            记分板.记分板.创建服务器介绍记分板(player);  //玩家回到大厅 给以大厅记分板
            return;
        }

    }
    @EventHandler
    public void 玩家点击(PlayerInteractEvent event) //玩家点击手上物品
    {
        //玩家点击手上物品
        if (event.getItem() == null){
            return;
        }
        Player player = event.getPlayer();
        if (!GameList.containsKey(player.getWorld())){
            return;
        }
        if (event.getItem().getType().equals(Material.DIAMOND)){
            player.openInventory(钻石商店.钻石商店容器);
            return;
        }


        if (event.getItem().getType().equals(Material.ENDER_EYE)){
            Inventory inventory = Bukkit.createInventory(null,6*9,"预言玩家");
            ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            int next = 0;
            for (Player player1:player.getWorld().getPlayers()){
                skullMeta.setOwner(player1.getName());
                itemStack.setItemMeta(skullMeta);
                inventory.setItem(next,itemStack);
                next++;
            }
            player.openInventory(inventory);
        }
        return;
    }
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) //玩家点击容器格子
    {
        Player player = (Player) event.getWhoClicked();
        if (!GameList.containsKey(player.getWorld())){
            return;
        }
        //玩家点击格子
        if (event.getView().getTitle().equals("钻石商店")){
            event.setCancelled(true);
            Inventory inventory = event.getInventory();
            if (inventory.getItem(event.getRawSlot()) != null){
                if (!Werewolf.钻石商店.contains("Item_"+event.getRawSlot())) {
                    return;
                }
                int 价格;
                ItemStack itemStack = player.getInventory().getItemInHand();
                if (itemStack.getAmount() >= (价格 = (int)Werewolf.钻石商店.Yml("int","Item_"+event.getRawSlot()+".Pay.price"))){
                   itemStack.setAmount(itemStack.getAmount() - 价格);
                    player.getInventory().addItem(钻石商店.钻石商店class.物品给以(event.getRawSlot()));
                }else{
                    player.sendMessage(ChatColor.RED+"你的钻石不够!!!");
                    player.closeInventory();
                }
            }
            return;
        }



        if (event.getView().getTitle().equals("预言玩家")){
            event.setCancelled(true);
            Inventory inventory = event.getInventory();
            if (inventory.getItem(event.getRawSlot()) != null){
                ItemStack itemStack = inventory.getItem(event.getRawSlot());
                SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                Player player_Aim = skullMeta.getOwningPlayer().getPlayer();
                if (!player_Aim.getWorld().getPlayers().contains(player_Aim)){
                    player.sendMessage(ChatColor.RED+"该玩家退出游戏了");
                    player_Aim.closeInventory();
                    return;
                }
                if (player_Aim.getGameMode().equals(GameMode.SPECTATOR)){
                    player.sendMessage(ChatColor.RED+"该玩家死亡了");
                    player_Aim.closeInventory();
                    return;
                }
                if (player_Aim.getName().equals(player.getName())){
                    player.sendMessage(ChatColor.RED+"你不能预言自己?>_<?");
                    player_Aim.closeInventory();
                    return;
                }
                Game game = GameList.get(player.getWorld());
                for (Player player1:player.getWorld().getPlayers()){
                    player1.sendTitle(net.md_5.bungee.api.ChatColor.BLUE+"预言家预言了某个玩家",ChatColor.GREEN+"该玩家职业是"+game.预言该玩家职业(player_Aim), 10, 80, 20);
                }
                player.getInventory().remove(Material.ENDER_EYE);
                player.closeInventory();
            }else{
                player.sendMessage(ChatColor.RED+"不要乱点击");
                player.closeInventory();
                return;
            }
        }
    }


    @EventHandler
    public void 受伤(EntityDamageByEntityEvent event){
        if(event.getDamager() == null){
            event.setCancelled(true);
            return;
        }
        if (event.getDamager().getType().equals(EntityType.TNT)){
            event.setDamage(9999);
            return;
        }
        if (event.getDamager().getType().equals(EntityType.ARROW)){
            event.setDamage(9999);
            return;
        }
        Player player = (Player) event.getDamager();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
            if (GameList.get(player.getWorld()).获取懒人().contains(event.getEntity()))
            {
                event.setCancelled(true);
                return;
            }
            event.setDamage(9999);
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void Die(PlayerDeathEvent event)
    {
        Player player = event.getPlayer();
        if (GameList.containsKey(player.getWorld())){
            event.setCancelled(true);
            Bukkit.getScheduler().runTask(Werewolf.getPlugin(Werewolf.class), bukkitTask -> {
                GameList.get(player.getWorld()).淘汰玩家(player);
            });
        }
    }
    @EventHandler
    public void Player退出游戏(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        if (GameList.containsKey(player.getWorld())){
            Bukkit.getScheduler().cancelTask(线程id.get(player));
            线程id.remove(player);
            GameList.get(player.getWorld()).淘汰玩家(player);
        }
    }

}
