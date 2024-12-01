package org.werewolf;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static org.werewolf.职业.线程id;

public class Game implements Listener {
    public static ItemStack 金子 = new ItemStack(Material.GOLD_INGOT);
    public static ItemStack 钻石 = new ItemStack(Material.DIAMOND);
    private int 金锭刷新速度;
    private int 钻石刷新速度;
    private boolean 巡查模式 = false;
    private int tick = 0;
    private List<String> 金锭位置 = new ArrayList<>();
    private List<String> 钻石位置 = new ArrayList<>();
    private List<Player> 好人阵营人数 = new ArrayList<>();
    private List<Player> 狼人 = new ArrayList<>();
    private List<Player> 平民 = new ArrayList<>();
    private List<Player> 侦探 = new ArrayList<>();
    private List<Player> 预言家 = new ArrayList<>();
    private World worlds;


    public List<Player> 获取懒人(){
        return 狼人;
    }
    public int GetTime(){
        return tick;
    }
    public int 获取存活人数(){
        if (!(狼人.isEmpty() && 好人阵营人数.isEmpty())) {
            return 狼人.size() + 好人阵营人数.size();
        }
        return -1;
    }
    public String 预言该玩家职业(Player player){
        if (狼人.contains(player)){
            return "§c狼人";
        }
        if (平民.contains(player)){
            return "§a平民";
        }
        if (侦探.contains(player)){
            return "§9侦探";
        }
        if (预言家.contains(player)){
            return "§b预言家";
        }
        return "null";
    }
    public void Game(World world){
        主大厅.主大厅class.选择器挂上房间正在进行(world);
        worlds = world;
        if (world.getName().equals("01")){
            金锭位置 = (List<String>) Werewolf._01.Yml("List","金锭位置");
            钻石位置 = (List<String>) Werewolf._01.Yml("List","钻石位置");

            金锭刷新速度 = (int)Werewolf._01.Yml("int","金锭刷新速度");
            钻石刷新速度 = (int)Werewolf._01.Yml("int","钻石刷新速度");
        }
        if (world.getName().equals("02"))
        {
            金锭位置 = (List<String>) Werewolf._02.Yml("List","金锭位置");
            钻石位置 = (List<String>) Werewolf._02.Yml("List","钻石位置");

            金锭刷新速度 = (int)Werewolf._02.Yml("int","金锭刷新速度");
            钻石刷新速度 = (int)Werewolf._02.Yml("int","钻石刷新速度");
        }
        if (world.getName().equals("03")){
            金锭位置 = (List<String>) Werewolf._03.Yml("List","金锭位置");
            钻石位置 = (List<String>) Werewolf._03.Yml("List","钻石位置");

            金锭刷新速度 = (int)Werewolf._03.Yml("int","金锭刷新速度");
            钻石刷新速度 = (int)Werewolf._03.Yml("int","钻石刷新速度");
        }
        //创建记分板记录



        List<Player> playerList = world.getPlayers();
        for (Player player:playerList){
            player.getInventory().clear();
            player.setHealth(20);
            player.setFoodLevel(20);
            switch (world.getName())
            {
                case "01":{
                    List<String> list = (List<String>) Werewolf._01.Yml("List","出生位置");
                    Location location = new Location(Bukkit.getWorld("01"),Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));;
                    player.teleport(location);
                    break;
                }
                case "02":{
                    List<String> list = (List<String>) Werewolf._02.Yml("List","出生位置");
                    Location location = new Location(Bukkit.getWorld("02"),Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));;
                    player.teleport(location);
                    break;
                }
                case "03":{
                    List<String> list = (List<String>) Werewolf._03.Yml("List","出生位置");
                    Location location = new Location(Bukkit.getWorld("03"),Double.parseDouble(list.get(0)),Double.parseDouble(list.get(1)),Double.parseDouble(list.get(2)));;
                    player.teleport(location);
                    break;
                }
            }
        }
        Random random = new Random(); //抽取随机数
        //删除上一次选择到的狼人

        System.out.println(random.nextInt(playerList.size()));
        //选择狼人
        if (world.getPlayers().size() >= 6){
            int index = random.nextInt(playerList.size());
            狼人.add(playerList.get(index));
            playerList.remove(playerList.get(index));
            // 2人选取
            int inde2 = random.nextInt(playerList.size());
            狼人.add(playerList.get(inde2));
            playerList.remove(playerList.get(inde2));
        }else if (world.getPlayers().size() >= 9) {
            int index = random.nextInt(playerList.size());
            狼人.add(playerList.get(index));
            playerList.remove(playerList.get(index));
            // 2人选取
            int inde2 = random.nextInt(playerList.size());
            狼人.add(playerList.get(inde2));
            playerList.remove(playerList.get(inde2));
            // 3
            int inde3 = random.nextInt(playerList.size());
            狼人.add(playerList.get(inde3));
            playerList.remove(playerList.get(inde3));
        }else{
            int index = random.nextInt(playerList.size());
            狼人.add(playerList.get(index));
            playerList.remove(playerList.get(index));
        }


        if (world.getPlayers().size() >= 4){
            int index = random.nextInt(playerList.size());
            预言家.add(playerList.get(index));
            playerList.remove(playerList.get(index));
        }

        //侦探
        int index = random.nextInt(playerList.size());
        侦探.add(playerList.get(index));
        playerList.remove(playerList.get(index));
        //将好人加入好人列表
        平民 = playerList;
        for (Player player:平民){
            好人阵营人数.add(player);
        }
        for (Player player:侦探){
            好人阵营人数.add(player);
        }
        if (!预言家.isEmpty()){
            for (Player player:预言家){
                好人阵营人数.add(player);
            }
        }

        Bukkit.getScheduler().runTaskTimer(Werewolf.getPlugin(Werewolf.class),bukkitTask ->{
            tick++;
            if (tick == 8) {
                游戏管理器.游戏管理器class.旁边告诉(ChatColor.RED+"这局游戏共狼人数量: "+ChatColor.BLUE+""+狼人.size(),world);
                游戏管理器.游戏管理器class.旁边告诉(ChatColor.RED+"狼人将在10秒内获得武器!",world);
                for (Player player1 : world.getPlayers()) {
                    记分板.记分板.游戏开始的记分板(player1);
                    if (平民.contains(player1)){
                        player1.sendTitle(ChatColor.GREEN+"你的职业是平民!!!","收集金锭保护自己", 10, 60, 20);
                        player1.getWorld().playSound(player1.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                        职业.职业class.平民(player1);
                        continue;
                    }
                    if (狼人.contains(player1)){
                        player1.sendTitle(ChatColor.RED+"你的职业是狼人!!!","杀死平民胜利", 10, 60, 20);
                        player1.getWorld().playSound(player1.getLocation(), Sound.ENTITY_VINDICATOR_CELEBRATE,1,1);
                        player1.sendMessage(ChatColor.RED+"----------------------------------");
                        player1.sendMessage(ChatColor.RED+"           狼群列表");
                        for (Player 狼人队友:狼人){
                            player1.sendMessage(ChatColor.BLUE+狼人队友.getName()+"");
                        }
                        player1.sendMessage(ChatColor.RED+"----------------------------------");
                        Bukkit.getScheduler().runTask(Werewolf.getPlugin(Werewolf.class), () ->
                        {
                            Bukkit.getScheduler().runTaskLater(Werewolf.getPlugin(Werewolf.class),bukkitTask1-> {
                                ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
                                player1.getInventory().addItem(itemStack);
                                游戏管理器.游戏管理器class.旁边告诉(ChatColor.RED+"狼人已经拿到了武器!!",world);
                                bukkitTask1.cancel();
                            },20*10);
                        });
                        职业.职业class.狼人(player1);
                        continue;
                    }
                    if (侦探.contains(player1)){
                        player1.sendTitle(ChatColor.BLUE+"你的职业是侦探!!!","保护自己和所有好人!", 10, 60, 20);
                        player1.getWorld().playSound(player1.getLocation(), Sound.UI_LOOM_SELECT_PATTERN,1,1);
                        ItemStack itemStack = new ItemStack(Material.BOW);
                        player1.getInventory().setItem(0,itemStack);
                        player1.getInventory().setItem(1,ItemStack.of(Material.ARROW));
                        职业.职业class.侦探(player1);
                        continue;
                    }
                    if (预言家.contains(player1)){
                        player1.sendTitle(ChatColor.BLUE+"你是预言家!!!","预言敌人是我的职业!", 10, 60, 20);
                        player1.getWorld().playSound(player1.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM,1,1);
                        ItemStack itemStack = new ItemStack(Material.ENDER_EYE);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.GREEN+"[右键点击]预言玩家");
                        itemStack.setItemMeta(itemMeta);
                        player1.getInventory().setItem(3,itemStack);
                        职业.职业class.预言家(player1);
                        continue;
                    }
                }
                tick = 0;
                bukkitTask.cancel();
            }
            if (tick == 1){
                for (Player player1 : world.getPlayers()) {
                    player1.sendTitle(ChatColor.GREEN+"正在抽取职业中!!!","", 10, 60, 20);
                    player1.getWorld().playSound(player1.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
                }
            }
        },20*2,20);

        Bukkit.getScheduler().runTaskTimer(Werewolf.getPlugin(Werewolf.class),bukkitTask ->{
            if ((tick % 金锭刷新速度) == 1){
                生成金子(world);
            }
            if ((tick % 钻石刷新速度) == 1){
                生存钻石(world);
            }
            if (狼人.isEmpty()){
                for (Player player: world.getPlayers()){
                    player.sendTitle(ChatColor.GREEN+"好人获胜!!","", 10, 60, 20);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR,1,1);
                }
                结束游戏(world);
                bukkitTask.cancel();
            }
            if (好人阵营人数.isEmpty()){
                for (Player player: world.getPlayers()){
                    player.sendTitle(ChatColor.GREEN+"狼人获胜","", 10, 60, 20);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE,1,1);
                }
                结束游戏(world);
                bukkitTask.cancel();
            }

            if (获取存活人数() == 2){
                if (巡查模式){
                    return;
                }巡查模式 = true;
                for (Player player:world.getPlayers()){
                    player.sendMessage(ChatColor.BLUE+"存活人数还剩2人,服务器自动打开巡查模式!!!");
                    if (player.getGameMode().equals(GameMode.SURVIVAL)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20*99999999,1));
                    }
                }
            }

            tick++;
        },20*10,20);
    }
    //参数 为 结束游戏的游戏
    public void 结束游戏(World world){
        for (Player player: world.getPlayers()){
            if (线程id.containsKey(player)){
                Bukkit.getScheduler().cancelTask(线程id.get(player));
            }
            player.setGameMode(GameMode.SPECTATOR);
            player.clearActivePotionEffects();
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,20*99999999,1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,20*99999999,1));
        }
        //主线程延迟初始化地图
        Bukkit.getScheduler().runTask(Werewolf.getPlugin(Werewolf.class), () ->
        {
            Bukkit.getScheduler().runTaskLater(Werewolf.getPlugin(Werewolf.class),bukkitTask -> {
                狼人.clear();
                好人阵营人数.clear();
                预言家.clear();
                侦探.clear();
                平民.clear();
                for (Player player: world.getPlayers()){
                    player.setGameMode(GameMode.SURVIVAL);
                    player.getInventory().clear();
                    player.getInventory().addItem(主大厅.主大厅class.大厅菜单获取());
                    player.teleport(主大厅.主大厅class.返回主大厅());
                }
                for (Entity entity:world.getEntities()){
                    if (entity.getType().equals(EntityType.ITEM)){
                        entity.remove();
                    }
                    if (entity.getType().equals(EntityType.ARROW)){
                        entity.remove();
                    }
                }
                游戏管理器.游戏管理器class.关闭游戏(world);
            },20*3);
            });
    }
    public void 生成金子(World world) {
        for (String string : 金锭位置) {
            String[] pres = string.split(" ");
            Location location = new Location(world, Double.parseDouble(pres[0]), Double.parseDouble(pres[1]), Double.parseDouble(pres[2]));
            world.dropItemNaturally(location, 金子);
        }
    }
    public void 生存钻石(World world)
    {
        for (String string : 钻石位置){
            String[] pres = string.split(" ");
            Location location = new Location(world, Double.parseDouble(pres[0]), Double.parseDouble(pres[1]), Double.parseDouble(pres[2]));
            world.dropItemNaturally(location, 钻石);
        }
    }


    public void 淘汰玩家(Player player){
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.RED+"你死了!!!");
        if (好人阵营人数.contains(player)){
            好人阵营人数.remove(player);
        }
        if (狼人.contains(player)){
            狼人.remove(player);
        }
    }
}
