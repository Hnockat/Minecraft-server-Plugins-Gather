package org.werewolf;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Werewolf extends JavaPlugin {
    public static GetYml _01 = new GetYml("01.yml");
    public static GetYml _02 = new GetYml("02.yml");
    public static GetYml _03 = new GetYml("03.yml");
    public static GetYml Main_Hub = new GetYml("Main_Hub.yml");
    public static GetYml 钻石商店 = new GetYml("钻石商店.yml");
    public static GetYml 记分板 = new GetYml("记分板.yml");
    @Override
    public void onEnable() {
        主大厅.主大厅class.初始化选择器();
        this.saveResource("01.yml",false);
        this.saveResource("02.yml",false);
        this.saveResource("03.yml",false);
        this.saveResource("Main_Hub.yml",false);
        this.saveResource("钻石商店.yml",false);
        this.saveResource("记分板.yml",false);
        org.werewolf.钻石商店.钻石商店class.初始化商店容器();
        // Plugin startup logic
       //World world01 = Bukkit.createWorld(new WorldCreator("01"));
       World world02 = Bukkit.createWorld(new WorldCreator("02"));
       World world01 = Bukkit.createWorld(new WorldCreator("01"));
       World world03 = Bukkit.createWorld(new WorldCreator("03"));

       for (Entity entity:world02.getEntities()){
           if (entity.getType().equals(EntityType.ITEM)){
               entity.remove();
           }
           if (entity.getType().equals(EntityType.ARROW)){
               entity.remove();
           }
       }
        for (Entity entity:world01.getEntities()){
            if (entity.getType().equals(EntityType.ITEM)){
                entity.remove();
            }
            if (entity.getType().equals(EntityType.ARROW)){
                entity.remove();
            }
        }
        for (Entity entity:world03.getEntities()){
            if (entity.getType().equals(EntityType.ITEM)){
                entity.remove();
            }
            if (entity.getType().equals(EntityType.ARROW)){
                entity.remove();
            }
        }
        ///////////////////////////////////////////刷新钻石 金子初始化
        ItemMeta itemMeta_dm = Game.钻石.getItemMeta();
        itemMeta_dm.setDisplayName(ChatColor.BLUE+ "钻石[右键点击购买道具][拿在手上才算你的钻石数量]");
        Game.钻石.setItemMeta(itemMeta_dm);

        ItemMeta itemMeta_gr = Game.金子.getItemMeta();
        itemMeta_gr.setDisplayName(ChatColor.BLUE + "金锭[物品堆为10自动换取武器!]");
        Game.金子.setItemMeta(itemMeta_gr);


        ///////////////////////////////////////////////
        getServer().getPluginManager().registerEvents(游戏管理器.游戏管理器class,this);
        getServer().getPluginManager().registerEvents(new 监听器(), this);
        getServer().getPluginManager().registerEvents(new TNT(), this);
        Bukkit.getWorld("world").setPVP(false);

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player)sender;
        if (!player.isOp()){
            player.sendMessage("你没有权限使用这个指令!");
            return false;
        }
        if (command.getName().equalsIgnoreCase("go")){
            游戏管理器.游戏管理器class.开启一个游戏(player.getWorld());
            Bukkit.getScheduler().cancelTask(org.werewolf.记分板.GameId.get(player.getWorld()));
            return true;
        }
        if (command.getName().equalsIgnoreCase("task")){
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }


}
