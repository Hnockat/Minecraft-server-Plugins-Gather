package org.examples.hub;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.examples.hub.Join.主大厅;
import org.examples.hub.World.玩家监听器;
import org.examples.hub.redis.Redis;
import org.examples.hub.菜单.游戏菜单;
import org.examples.hub.菜单.菜单监听器;
import org.examples.hub.跑酷.压力板检测;
import org.jetbrains.annotations.NotNull;

public final class Hub extends JavaPlugin {
    public static CommandSender commandSender = Bukkit.getConsoleSender();
    public static GetYml 游戏菜单配置文件 = new GetYml("游戏菜单.yml");
    public static GetYml 记分板 = new GetYml("Scoreboard\\记分板.yml");
    public static GetYml 大厅配置文件 = new GetYml("大厅.yml");
    private static Hub plugin = null;
    public static Hub getInstance(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        this.saveResource("游戏菜单.yml", false);
        游戏菜单.游戏菜单class.初始化菜单();
        getServer().getPluginManager().registerEvents(new 菜单监听器(), this);
        getServer().getPluginManager().registerEvents(new 玩家监听器(), this);
        getServer().getPluginManager().registerEvents(new 压力板检测(), this);
        this.创建yml();
    }
    public void 创建yml()
    {
     this.saveResource("Scoreboard\\记分板.yml",false);
     this.saveResource("大厅.yml",false);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("hub")){
            主大厅.主大厅class.Join((Player) sender);
            return true;
        }


        return false;
    }
}
