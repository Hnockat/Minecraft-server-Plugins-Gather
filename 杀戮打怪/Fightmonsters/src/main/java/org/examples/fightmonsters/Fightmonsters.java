package org.examples.fightmonsters;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.examples.fightmonsters.Game.游戏开始;
import org.examples.fightmonsters.Game.设置保护枚举;

import org.examples.fightmonsters.Shop.金币;
import org.examples.fightmonsters.prop.TNT;
import org.examples.fightmonsters.工匠.工匠升级;
import org.examples.fightmonsters.工匠.工匠格子点击监听器;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Fightmonsters extends JavaPlugin {
    public static GetYml 初始化列表 = new GetYml("初始化列表.yml");
    public static GetYml 商人 = new GetYml("商人.yml");
    public static GetYml 左右门设置注册 = new GetYml("monster\\左右门设置注册.yml");
    public static GetYml 刷怪位置 = new GetYml("monster\\刷怪位置.yml");
    public static GetYml 各层关卡数 = new GetYml("monster\\各层关卡数.yml");
    public static GetYml Wall_left_Lost = new GetYml("Wall_settings\\leftWall.yml");
    public static GetYml Wall_right_Lost = new GetYml("Wall_settings\\rightWall.yml");
    public static GetYml 区门设置 = new GetYml("Wall_settings\\区门设置.yml");
    public static GetYml 垃圾处理箱 = new GetYml("垃圾处理箱.yml");
    private static Fightmonsters plugin = null;
    public static Fightmonsters getInstance(){
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;
        this.saveResource("初始化列表.yml", false);
        this.saveResource("商人.yml", false);
        this.saveResource("垃圾处理箱.yml", false);
        初始化工匠文件设置();
        new 游戏初始化();
        getServer().getPluginManager().registerEvents(new 监听器(), this);
        getServer().getPluginManager().registerEvents(new TNT(), this);
        getServer().getPluginManager().registerEvents(new 工匠格子点击监听器(),this);
        创建怪物列表("monster");
        墙创建列表("Wall_settings");
        Shop("shop");
        Bukkit.getWorld("world").setPVP(false);
        // Plugin startup logic
    }
    public void 初始化工匠文件设置()
    {
        this.saveResource("工匠\\工匠商店.yml",false);

        this.saveResource("工匠\\工匠升级\\工匠升级.yml",false);

        this.saveResource("工匠\\工匠修复\\工匠修复.yml",false);
    }
    public static Location 返回游戏开始处()
    {
        double yaw = (double) Fightmonsters.初始化列表.Yml("double", "游戏开始传送位置.yaw");
        double pitch = (double) Fightmonsters.初始化列表.Yml("double", "游戏开始传送位置.pitch");
        Location loc = new Location(
                Bukkit.getWorld("world"),
                (double) Fightmonsters.初始化列表.Yml("double", "游戏开始传送位置.x"),
                (double) Fightmonsters.初始化列表.Yml("double", "游戏开始传送位置.y"),
                (double) Fightmonsters.初始化列表.Yml("double", "游戏开始传送位置.z"),
                (float) yaw,
                (float) pitch
        );
        return loc;
    }
    /***
     *  此方法专用来拷贝resources 的文件夹里面的文件到plugins文件夹中
     * @param filename 文件夹name
     */
    public @Nullable void 创建怪物列表(@NotNull String filename) {
        this.saveResource(filename + "\\左右门设置注册.yml", false);
        this.saveResource(filename + "\\各层关卡数.yml", false);
        /*for (int i = 1; i <= 3; i++) {
            this.saveResource(filename + "\\1\\" + i + ".txt", false);
        }*/

       List<Integer> list =  (List<Integer>) Fightmonsters.各层关卡数.Yml("List","List");
       for (int i : list)
        {
            List<Integer> list_ = (List<Integer>) Fightmonsters.各层关卡数.Yml("List",i+"");
            for (int 层:list_) {
                for (int a = 1; a <= i; a++) {
                    if (!Fightmonsters.左右门设置注册.contains_List_value("List",层)){
                        this.saveResource(filename + "\\" + 层 + "\\" + a + ".txt", false);
                    }else{
                        this.saveResource(filename + "\\" + 层 + "\\left\\" +a+".txt", false);
                        this.saveResource(filename + "\\" + 层 + "\\right\\" +a+".txt", false);
                    }
                }
            }
        }
    }
    public static GetYml 普通商人菜单 = new GetYml("shop\\普通商人菜单.yml");
    public static GetYml 附魔商人菜单 = new GetYml("shop\\附魔商人菜单.yml");
    public void Shop(String filename)
    {
        this.saveResource(filename+"\\普通商人菜单.yml",false);
        this.saveResource(filename+"\\附魔商人菜单.yml",false);
    }

    public void 墙创建列表(String filename) {
        this.saveResource(filename + "\\leftWall.yml", false);
        this.saveResource(filename + "\\rightWall.yml", false);
        this.saveResource(filename + "\\区门设置.yml", false);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            if (command.getName().equals("测试")){
                Bukkit.getScheduler().runTask(this,bukkitTask -> {
                    Bukkit.unloadWorld("world", true);
                    Bukkit.createWorld(WorldCreator.name("world"));
                });
                System.out.println("卸载ok");
            }


            return false;
        }
        Player player = (Player)sender;
        if (!player.isOp()){
            player.sendMessage(ChatColor.RED+"你没有权限使用这个命令!!!!!");
            return false;
        }

        switch (command.getName()){
            case "杀死敌人" ->{
                for (Entity ent : player.getWorld().getEntities()) {
                    if (!设置保护枚举.isProtectedEntityType(ent.getType().name()))
                        ent.remove();
                }
            }
            case "set金币" ->{
                金币.金币设置.set金币(Bukkit.getPlayer(args[0]),Integer.parseInt(args[1]));
                player.sendMessage("已经给玩家"+args[0]+"发放金币: "+args[1]);
            }
            case "关卡" ->{
                游戏开始.当前层数 = Integer.parseInt(args[0]);
                游戏开始.当前层数的副本数 = Integer.parseInt(args[1]);
            }
            case "怪物属性开放" ->{
                游戏开始.当前是否可以刷怪 = true;
            }
            case "测试" -> {
                ItemStack itemStack = player.getItemInHand();
                工匠升级.set属性(itemStack,Attribute.GENERIC_MAX_HEALTH,20);
            }
        }
        return false;
    }

    @Override
    public void onDisable() {

    }

}
