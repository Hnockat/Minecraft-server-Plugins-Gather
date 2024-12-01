package org.gun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Gun extends JavaPlugin {

    public static GetYml GetYml_机枪类型 = new GetYml("机枪.yml");
    public static GetYml GetYml_子弹 = new GetYml("子弹.yml");
    public static GetYml GetYml_投掷物 = new GetYml("投掷物.yml");
    public static GetYml GetYml_子弹破坏方块列表 = new GetYml("子弹破坏方块列表.yml");
    private static Gun plugin = null;
    public static Gun getInstance(){
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        this.saveResource("机枪.yml", false);
        this.saveResource("子弹.yml", false);
        this.saveResource("投掷物.yml", false);
        this.saveResource("子弹破坏方块列表.yml", false);
        getServer().getPluginManager().registerEvents(new 机枪监听器(),this);
        getServer().getPluginManager().registerEvents(new a(),this);

        子弹.子弹class.初始化子弹破坏方块列表();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("测试")){
            Player player = (Player) sender;
            player.getInventory().addItem(new give枪().giveGun("Ak47"));
            player.getInventory().addItem(子弹.子弹class.子弹给以("7/62x39mm.步枪"));
        }
        if (command.getName().equalsIgnoreCase("测试2")){
            Player player = (Player) sender;
            player.getInventory().addItem(new give枪().giveGun("Mossberg/500"));
            player.getInventory().addItem(子弹.子弹class.子弹给以("12号口径.散弹枪"));
        }
        if (command.getName().equalsIgnoreCase("测试3")){
            Player player = (Player) sender;
            System.out.println(NBT.NbtSet.该枪配置的弹夹Get(player.getItemInHand()));
        }
        if (command.getName().equalsIgnoreCase("测试4")){

        }
        return false;
    }
}
