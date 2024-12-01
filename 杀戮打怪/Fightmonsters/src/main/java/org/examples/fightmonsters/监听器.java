package org.examples.fightmonsters;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BellRingEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;
import org.examples.fightmonsters.Boos.Boos特效;
import org.examples.fightmonsters.Game.游戏开始;
import org.examples.fightmonsters.Setdata.记分板;
import org.examples.fightmonsters.Shop.工匠商人设置;
import org.examples.fightmonsters.Shop.普通商人商品设置;
import org.examples.fightmonsters.Shop.金币;
import org.examples.fightmonsters.Shop.附魔商人商品设置;
import org.examples.fightmonsters.building.区门设置;
import org.examples.fightmonsters.prop.垃圾处理箱;
import org.examples.fightmonsters.工匠.工匠修复;
import org.examples.fightmonsters.工匠.工匠升级;
import org.examples.fightmonsters.工匠.工匠打造箱子;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.bukkit.Sound.ENTITY_VILLAGER_NO;
import static org.bukkit.Sound.ENTITY_VILLAGER_YES;
public class 监听器 implements Listener {

    @EventHandler
    public void 方块点击操作(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getClickedBlock().getType().equals(Material.CHEST)){
                Block block = event.getClickedBlock();
                Chest chest = (Chest) block.getState();
                //箱子检测
                if(chest.getCustomName().equals("垃圾箱")) {
                    event.setCancelled(true);
                    Player player = event.getPlayer();

                    if (垃圾处理箱.是否已经购买 == true) {
                        player.openInventory(垃圾处理箱.垃圾处理箱_01);
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                        chest.open(); //打开箱子
                    }
                    if (垃圾处理箱.是否已经购买 == false) {
                        player.openInventory(垃圾处理箱.垃圾处理箱_home);
                        player.playSound(player.getLocation(),Sound.BLOCK_CHEST_OPEN,1,1);
                        chest.open(); //打开箱子
                    }
                    return;
                }
                if (chest.getCustomName().equals("工匠箱子")){
                    event.setCancelled(true);
                    Player player = event.getPlayer();
                    player.openInventory(工匠打造箱子.打造箱子);
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                    chest.open(); //打开箱子
                    return;
                }

            }
            return;
        }
    }
    @EventHandler
    public void 玩家左右键点击(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (event.getItem() == null){
            return;
        }
        if (event.getItem().getType() == Material.WIND_CHARGE){
            if (游戏开始.当前主线程是否启动 == false){
                if (player.getGameMode().equals(GameMode.CREATIVE)){
                    return;
                }
                player.sendMessage(ChatColor.RED+"禁止在游戏暂停状态使用该物品[想跑酷作弊没有门!!!]");
                event.setCancelled(true);
            }
            return;
        }





    }
    @EventHandler
    public void EntityDeathEvent_击杀怪物(EntityDeathEvent entityDeathEvent) { //击杀怪物
        if (entityDeathEvent.getEntity().getKiller() == null) {
            return;
        }

        if (!entityDeathEvent.getEntity().getScoreboardTags().isEmpty()){
            if (entityDeathEvent.getEntity().getScoreboardTags().size() == 1){
                    金币.金币设置.add金币(entityDeathEvent.getEntity().getKiller(), Integer.parseInt(entityDeathEvent.getEntity().getScoreboardTags().iterator().next()));
                    return;
            }else{
                Iterator<String> iterator = entityDeathEvent.getEntity().getScoreboardTags().iterator();
                while (iterator.hasNext()){
                    String are = iterator.next();
                    if (isNumeric(are)){
                        金币.金币设置.add金币(entityDeathEvent.getEntity().getKiller(),Integer.parseInt(are));
                        return;
                    }
                }
            }
        }
    }
    @EventHandler
    public void 实体死亡调用(EntityDeathEvent event)
    {
        if (event.getEntity().getType() == EntityType.PLAYER){
            return;
        }
        if (垃圾处理箱.是否已经购买){
            if (垃圾处理箱.垃圾处理箱_class.垃圾箱添加垃圾(event.getDrops(),event.getEntity().getWorld())){
                event.setCancelled(true);
                event.getEntity().remove();
                ExperienceOrb exp =  event.getEntity().getWorld().spawn(event.getEntity().getLocation(), ExperienceOrb.class);
                exp.setExperience(event.getDroppedExp());
            }
        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getPlayer();
        playerDeathEvent.setCancelled(true);
        playerDeathEvent.getPlayer().sendMessage(ChatColor.RED + "你死了,请等待15秒复活");
        //设置死亡触发器
        Bukkit.getScheduler().runTask(Fightmonsters.getPlugin(Fightmonsters.class), () ->
        {
            player.setGameMode(GameMode.SPECTATOR);
            player.teleport(Fightmonsters.返回游戏开始处());
        });
        Bukkit.getScheduler().runTaskAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class), () ->
        {
            Bukkit.getScheduler().runTaskLater(Fightmonsters.getPlugin(Fightmonsters.class), () ->
            {
                player.setGameMode(GameMode.SURVIVAL);
                player.teleport(Fightmonsters.返回游戏开始处());
            }, 300);
        });
    }
    @EventHandler
    public void 点击盔甲架(PlayerArmorStandManipulateEvent arm) {
        arm.setCancelled(true);
        if (arm.getRightClicked().getName().equals((String) Fightmonsters.区门设置.Yml("String","普通商店区.介绍.name"))){
            Player player = arm.getPlayer();
            if (游戏开始.当前主线程是否启动 == true){
                player.sendMessage(ChatColor.RED+"打怪时间禁止进入商店区!");
                return;
            }
            player.openInventory(区门设置.打开普通商店区容器);
            return;
        }
        if (arm.getRightClicked().getName().equals((String) Fightmonsters.区门设置.Yml("String","工匠商店区.介绍.name"))){
            arm.getPlayer().openInventory(区门设置.打开工匠商店区容器);
            return;
        }
        if (arm.getRightClicked().getName().equals((String) Fightmonsters.区门设置.Yml("String","水源区.介绍.name"))){
            arm.getPlayer().openInventory(区门设置.打开水族馆区容器);
            return;
        }

    }
    @EventHandler
    public void 玩家点击实体监听器(PlayerInteractEntityEvent event)
    {
        //游戏开始入口_1
        if(event.getRightClicked().getName().equals(Fightmonsters.初始化列表.Yml("String","游戏开始npc.name")))
        {
           new 游戏开始();
        }
        if (event.getRightClicked().getName().equals(Fightmonsters.商人.Yml("String","普通商店设置.name"))){
            event.getPlayer().openInventory(普通商人商品设置.普通商人菜单_home);
        }
        if (event.getRightClicked().getName().equals(Fightmonsters.商人.Yml("String","附魔商店设置.name"))){
            event.getPlayer().openInventory(附魔商人商品设置.附魔商人菜单_home);
        }
        if (event.getRightClicked().getName().equals(Fightmonsters.商人.Yml("String","工匠商人设置.name"))){
            if (工匠商人设置.是否有人正在使用该工匠 == false){
                event.getPlayer().openInventory(工匠商人设置.工匠商人设置class.工匠商人);
                工匠商人设置.是否有人正在使用该工匠 = true;
            }else{
                event.getPlayer().sendMessage(ChatColor.RED+"有玩家正在使用工匠商人,请排队等待!");
            }
        }

    }
    @EventHandler
    public void 玩家加入游戏监听器(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (游戏开始.游戏是否开始 == false){
            金币.金币设置.创建玩家金币档案(player); //创建玩家档案
            记分板.记分板.创建服务器介绍记分板(player);
            player.sendMessage(ChatColor.RED + "----------------------------");
            player.sendMessage("   欢迎来到[杀戮闯关]");
            player.sendMessage("   游戏规则是活下去! 打造nb的装备! 挑战Boos");
            player.sendMessage("   公告: 更新新的boos和怪物 增加层数 增加了新的道具");
            player.sendMessage("   公告: 玩法升级 [工匠系统装备开发完成!!!]");
            player.sendMessage(ChatColor.RED + "----------------------------");
            double yaw = (double)Fightmonsters.初始化列表.Yml("double","大厅位置.yaw");
            double pitch = (double)Fightmonsters.初始化列表.Yml("double","大厅位置.pitch");
            Location loc = new Location(Bukkit.getWorld("world"),
                    (double)Fightmonsters.初始化列表.Yml("double","大厅位置.x"),
                    (double)Fightmonsters.初始化列表.Yml("double","大厅位置.y"),
                    (double)Fightmonsters.初始化列表.Yml("double","大厅位置.z"),
                    (float)yaw,(float)pitch);
            player.teleport(loc);
        }
        else
        {
            记分板.记分板.创建游戏记分板(player);
            if (金币.金币设置.金币.containsKey(player.getName()))
            {
                event.getPlayer().sendMessage("检测到你已经加入过了服务器! 请等待15秒复活!!!");
                Bukkit.getScheduler().runTask(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                {
                    player.teleport(Fightmonsters.返回游戏开始处());
                    player.setGameMode(GameMode.SPECTATOR);
                });
                Bukkit.getScheduler().runTaskAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                {
                    Bukkit.getScheduler().runTaskLater(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                    {
                        player.teleport(Fightmonsters.返回游戏开始处());
                        player.setGameMode(GameMode.SURVIVAL);
                    }, 300);
                });

            }else{
                event.getPlayer().sendMessage("你是初次加入游戏 请等15秒复活");
                金币.金币设置.创建玩家金币档案(player); //创建玩家档案
                player.setHealth(20);
                player.setFoodLevel(20);
                player.getInventory().clear();
                player.setLevel(0);
                player.getActivePotionEffects();
                if (!player.getActivePotionEffects().isEmpty()) { //移除玩家药水效果
                    for (PotionEffect potionEffect : player.getActivePotionEffects()) {
                        player.removePotionEffect(potionEffect.getType());
                    }
                }
                Bukkit.getScheduler().runTask(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                {
                    player.teleport(Fightmonsters.返回游戏开始处());
                    player.setGameMode(GameMode.SPECTATOR);
                });
                Bukkit.getScheduler().runTaskAsynchronously(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                {
                    Bukkit.getScheduler().runTaskLater(Fightmonsters.getPlugin(Fightmonsters.class), () ->
                    {
                        player.teleport(Fightmonsters.返回游戏开始处());
                        player.setGameMode(GameMode.SURVIVAL);
                    }, 300);
                });
            }
        }
    }
    @EventHandler
    public void 玩家退出服务器操作(PlayerQuitEvent event)
    {
        if (游戏开始.游戏是否开始 == true){
           记分板.记分板.消除该玩家线程(event.getPlayer());
        }
        if (event.getPlayer().getWorld().getPlayers().size() == 0){
            Fightmonsters.getPlugin(Fightmonsters.class).getServer().shutdown();
        }
    }
    //怪物刷新 生成
    @EventHandler
    public void 怪物刷新(CreatureSpawnEvent event) {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND)){
            if (游戏开始.当前是否可以刷怪 == true)
            {
                boolean 是否可以弹跳 = true;
                if (!event.getEntity().getScoreboardTags().isEmpty()){
                    Iterator<String> iterator = event.getEntity().getScoreboardTags().iterator();
                    while (iterator.hasNext()){
                        String AttributeandBase = iterator.next();
                        if (AttributeandBase.equalsIgnoreCase("flynull")){ //检测是否取消向量飞
                            是否可以弹跳 = false;
                            continue;
                        }
                        if (AttributeandBase.equalsIgnoreCase("r")){ //检测是否开启 右向量飞
                            是否可以弹跳 = false;
                            Vector vector = event.getEntity().getVelocity();
                            vector.setY(vector.getY() + 0.4);
                            vector.setZ(vector.getZ() + 1);
                            vector.multiply(1.3);
                            event.getEntity().setVelocity(vector);
                            continue;
                        }
                        if (isNumeric(AttributeandBase)){ //检测如果字符串是数字 就进行循环体
                            continue;
                        }
                        if (AttributeandBase.contains(">")){ //检测是否开启
                            AttributeandBase = AttributeandBase.replace(">","");
                            String[] parts_ = AttributeandBase.split(",");
                            PotionEffect potionEffect = new PotionEffect(PotionEffectType.getByName(parts_[0]),Integer.parseInt(parts_[2])*20,Integer.parseInt(parts_[1]),false,false);
                            event.getEntity().addPotionEffect(potionEffect);
                            continue;
                        }
                        if (AttributeandBase.contains("^"))
                        {
                            AttributeandBase = AttributeandBase.replace("^","");
                            String[] parts_ = AttributeandBase.split(",");
                            EntityEquipment entityEquipment = event.getEntity().getEquipment();
                            switch (parts_[0]){
                                case "InHand":{ //主手拿的物品
                                    if (entityEquipment.getItemInHand().isEmpty()){System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getItemInHand();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setItemInHand(stack);
                                    break;
                                }
                                case "OffHand":{ //副手拿物品
                                    if (entityEquipment.getItemInOffHand().isEmpty()) {System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getItemInOffHand();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setItemInOffHand(stack);
                                    break;
                                }
                                case "Helmet":{
                                    if (entityEquipment.getHelmet().isEmpty()){System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getHelmet();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setHelmet(stack);
                                    break;
                                }
                                case "Chestplate":{
                                    if (entityEquipment.getChestplate().isEmpty()){System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getChestplate();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setChestplate(stack);
                                    break;
                                }
                                case "Leggings":{
                                    if (entityEquipment.getLeggings().isEmpty()){System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getLeggings();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setLeggings(stack);
                                    break;
                                }
                                case "Boots":{
                                    if (entityEquipment.getBoots().isEmpty()){System.out.println("[服务器插件异常]nbt指向的实体容器是null的"); return;}
                                    ItemStack stack = entityEquipment.getBoots();
                                    ItemMeta itemMeta = stack.getItemMeta();
                                    itemMeta.addEnchant(Enchantment.getByName(parts_[1]),Integer.parseInt(parts_[2]),true);
                                    stack.setItemMeta(itemMeta);
                                    entityEquipment.setBoots(stack);
                                    break;
                                }
                                default: {
                                    System.out.println("[服务器插件异常]不正确的nbt数据!");
                                    return;
                                }
                            }
                         continue;
                        }
                        if (AttributeandBase.contains("creeper")){
                            String[] pars_ = AttributeandBase.split(",");
                            Creeper creeper = (Creeper) event.getEntity();
                            creeper.setPowered(Boolean.getBoolean(pars_[1]));
                            creeper.setExplosionRadius(Integer.parseInt(pars_[2]));
                            creeper.setMaxFuseTicks(Integer.parseInt(pars_[3]));
                            continue;
                        }
                        if (AttributeandBase.contains("%")){
                            AttributeandBase = AttributeandBase.replace("%","");
                            for (Player player:event.getEntity().getWorld().getPlayers()){
                                player.playSound(player,Sound.valueOf(AttributeandBase),1,1);
                            }
                            continue;
                        }
                        if (AttributeandBase.contains("@")){
                            AttributeandBase = AttributeandBase.replace("@ ","");
                            Boos特效.boos特效class.选择(AttributeandBase,event.getEntity());
                            continue;
                        }

                        String[] parts = AttributeandBase.split(",");
                        AttributeInstance arrt = event.getEntity().getAttribute(Attribute.valueOf(parts[0]));
                        arrt.setBaseValue(Double.parseDouble(parts[1]));
                    }
                    event.getEntity().setHealth(event.getEntity().getMaxHealth());
                }
                if (是否可以弹跳 == true){
                    Vector vector = event.getEntity().getVelocity();
                    vector.setY(vector.getY() + 0.4);
                    vector.setZ(vector.getZ() - 1);
                    vector.multiply(1.3);
                    event.getEntity().setVelocity(vector);
                }
            }
        }
    }
    public static List<String> 点击人数 = new ArrayList<>();
    @EventHandler
    public void 钟的点击处理(BellRingEvent event)
    {
        if (游戏开始.游戏是否开始 == true&&游戏开始.当前主线程是否启动 == false){
            if (!event.getEntity().getType().equals(EntityType.PLAYER)){
                return;
            }
            if (event.getEntity().getWorld().getPlayers().size()>=2){
                    if (点击人数.contains(event.getEntity().getName())){ //检测玩家是否重复点击钟
                        event.getEntity().sendMessage(ChatColor.RED+"你已经点击游戏钟,请你邀请其他人点击钟,至少2人点击钟开始下一把");
                        return;
                    }else{
                        if (!(点击人数.size()>=1)) {
                            点击人数.add(event.getEntity().getName());
                            Bukkit.broadcastMessage(ChatColor.RED + "玩家" + event.getEntity().getName() + "点击了[游戏开始下一关]钟" + ",该钟至少2人开启,有愿开始下一把的玩家点击钟");
                            return;
                        }
                    }
                点击人数.clear();
            }


            游戏开始.当前层数++;
            游戏开始.当前层数的副本数 = 1;
            for (Player p: event.getEntity().getWorld().getPlayers()){
                p.sendTitle(net.md_5.bungee.api.ChatColor.GREEN + ""+游戏开始.当前层数+"."+游戏开始.当前层数的副本数, net.md_5.bungee.api.ChatColor.GOLD+"决斗场正在蓄力", 10, 100, 20);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                p.teleport(Fightmonsters.返回游戏开始处());
            }
            游戏开始.times = 0;
            游戏开始.建筑初始化.钟消失(event.getBlock().getWorld());
            游戏开始.当前主线程是否启动 =true;
        }
    }
    @EventHandler
    public void 禁止破坏方块(BlockBreakEvent event)
    {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void 禁止放置方块(BlockPlaceEvent event){
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void 点击格子(InventoryClickEvent event)
    {
        /////////////////////////////// 垃圾处理箱 ////////////////////////////////////////////
        if (event.getView().getTitle().equals("垃圾处理箱_购买")){
            event.setCancelled(true);
            Player player = (Player)event.getWhoClicked();
            if (垃圾处理箱.是否已经购买 ==false  &&  event.getRawSlot() == 22){
                if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.垃圾处理箱.Yml("int","箱子设置.Pay.Price"))){
                    金币.金币设置.金币支付(player,(int)Fightmonsters.垃圾处理箱.Yml("int","箱子设置.Pay.Price"));
                    垃圾处理箱.是否已经购买 = true;
                    player.openInventory(垃圾处理箱.垃圾处理箱_01);
                    player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else{
                    player.sendMessage(ChatColor.RED + "你需要更多金币购买该商品!");
                    player.closeInventory();
                }
            }
            return;
        }
        if (event.getView().getTitle().equals("垃圾处理箱_01")){
            Player player = (Player) event.getWhoClicked();
            int RawSlot = event.getRawSlot();
            for (int a = 46;a <=52;a++){
                if (RawSlot == a){event.setCancelled(true);return;}
            }
            if (RawSlot == 53){
                player.playSound(player.getLocation(),Sound.UI_BUTTON_CLICK,1,1);
                player.openInventory(垃圾处理箱.垃圾处理箱_02);
                event.setCancelled(true);
            }
            if (RawSlot == 45){
                player.playSound(player.getLocation(),Sound.BLOCK_FIRE_EXTINGUISH,1,1);
                player.sendMessage(ChatColor.RED+"已经清除垃圾!");
                垃圾处理箱.垃圾处理箱_class.清除垃圾箱(player.getWorld());
                event.setCancelled(true);
            }
            return;
        }
        if (event.getView().getTitle().equals("垃圾处理箱_02")){
            Player player = (Player) event.getWhoClicked();
            int RawSlot = event.getRawSlot();
            if (event.getRawSlot() == 53){
                player.playSound(player.getLocation(),Sound.UI_BUTTON_CLICK,1,1);
                player.openInventory(垃圾处理箱.垃圾处理箱_01);
            }
            for (int a = 46;a <=52;a++){
                if (RawSlot == a){event.setCancelled(true);return;}
            }
            if (RawSlot == 53){
                player.playSound(player.getLocation(),Sound.UI_BUTTON_CLICK,1,1);
                player.openInventory(垃圾处理箱.垃圾处理箱_01);
                event.setCancelled(true);
            }
            if (RawSlot == 45){
                player.playSound(player.getLocation(),Sound.BLOCK_FIRE_EXTINGUISH,1,1);
                player.sendMessage(ChatColor.RED+"已经清除垃圾!");
                垃圾处理箱.垃圾处理箱_class.清除垃圾箱(player.getWorld());
                event.setCancelled(true);
            }
            return;
        }
        //////////////////////////////  ---  ///////////////////////////////////////////////
        /////////////////////////////// 解锁区菜单 ////////////////////////////////////////////
        if (event.getView().getTitle().equals("解锁普通商店区")){
            Player player = (Player) event.getWhoClicked();
            int RawSlot = event.getRawSlot();
            event.setCancelled(true);
            if (RawSlot == 22){
                //开启商店!
                if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.区门设置.Yml("int","普通商店区.介绍.开启金币")))
                {
                    金币.金币设置.金币支付(player,(int)Fightmonsters.区门设置.Yml("int","普通商店区.介绍.开启金币"));
                    游戏开始.区门设置.开启商店区(player.getWorld());
                    区门设置.普通商店区是否打开 = true;
                    /////////////////////初始化商人
                    普通商人商品设置.普通商人class.初始化商人(player.getWorld());
                    附魔商人商品设置.附魔商人商品设置class.初始化商人(player.getWorld());
                    player.closeInventory();
                }
                else
                {
                    player.sendMessage(ChatColor.RED+"你没有金币购买商店");
                    player.closeInventory();
                }
            }
            return;
        }
        if (event.getView().getTitle().equals("解锁工匠商店区")){
            Player player = (Player) event.getWhoClicked();
            int RawSlot = event.getRawSlot();
            event.setCancelled(true);
            if (RawSlot == 22){
                //开启商店!
                if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.区门设置.Yml("int","工匠商店区.介绍.开启金币")))
                {
                    金币.金币设置.金币支付(player,(int)Fightmonsters.区门设置.Yml("int","工匠商店区.介绍.开启金币"));
                    游戏开始.区门设置.开启工匠区(player.getWorld());

                    工匠商人设置.工匠商人设置class.初始化商人主菜单(player.getWorld());
                    工匠商人设置.工匠商人设置class.初始化商人(player.getWorld());
                    工匠商人设置.工匠商人设置class.初始化箱子标签(player.getWorld());
                    //工匠修复初始化
                    工匠修复.工匠修复class.容器初始化();
                    //工匠升级初始化
                    工匠升级.工匠升级class.容器初始化();


                    player.closeInventory();
                }
                else
                {
                    player.sendMessage(ChatColor.RED+"你没有金币购买");
                    player.closeInventory();
                }
            }
            return;
        }
        if (event.getView().getTitle().equals("解锁水族馆区")){
            Player player = (Player) event.getWhoClicked();
            int RawSlot = event.getRawSlot();
            event.setCancelled(true);
            if (RawSlot == 22){
                //开启商店!
                if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.区门设置.Yml("int","水源区.介绍.开启金币")))
                {
                    金币.金币设置.金币支付(player,(int)Fightmonsters.区门设置.Yml("int","水源区.介绍.开启金币"));
                    游戏开始.区门设置.开启水源区(player.getWorld());
                }
                else
                {
                    player.sendMessage(ChatColor.RED+"你没有金币购买");
                    player.closeInventory();
                }
            }
            return;
        }
        //////////////////////////////////// 普通商人菜单 ////////////////////////////////////////////
        if (event.getView().getTitle().equals("普通商人菜单_home"))
        {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            for (int a = 0;;a++)
            {
                if (Fightmonsters.普通商人菜单.contains("Item_"+a+".RawSlot"))
                {
                    if ((int)Fightmonsters.普通商人菜单.Yml("int","Item_"+a+".RawSlot") == event.getRawSlot()){
                        switch (a)
                        {
                            case 0:{
                                player.openInventory(普通商人商品设置.普通商人菜单_00);
                                return;
                            }
                            case 1:{
                                player.openInventory(普通商人商品设置.普通商人菜单_01);
                                return;
                            }
                            case 2:{
                                player.openInventory(普通商人商品设置.普通商人菜单_02);
                                return;
                            }
                            case 3:{
                                player.openInventory(普通商人商品设置.普通商人菜单_03);
                                return;
                            }
                            case 4:{
                                player.openInventory(普通商人商品设置.普通商人菜单_04);
                                return;
                            }
                        }

                    }
                }else{
                    return;
                }
            }
        }
        for (int i = 0;Fightmonsters.普通商人菜单.contains("Item_"+i);i++)
        {
            if (event.getView().getTitle().equals("普通商人菜单_0"+i)){
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);
                if (event.getRawSlot() == 53)
                {
                    if (event.getClick().equals(ClickType.LEFT)){
                        player.openInventory(普通商人商品设置.普通商人菜单_home);
                        return;
                    }
                    return;
                }

                if (Fightmonsters.普通商人菜单.contains("Item_"+i+".Get."+event.getRawSlot())){
                    if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.普通商人菜单.Yml("int","Item_"+i+".Get."+event.getRawSlot()+".Pay.price"))){
                        金币.金币设置.金币支付(player,(int)Fightmonsters.普通商人菜单.Yml("int","Item_"+i+".Get."+event.getRawSlot()+".Pay.price"));
                        player.playSound(player.getLocation(), ENTITY_VILLAGER_YES, 1, 1);
                        player.getInventory().addItem(普通商人商品设置.普通商人class.物品给以("Item_"+i+".Get."+event.getRawSlot()));
                    }else{
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED+"你的金币不够!!!");
                        player.playSound(player.getLocation(), ENTITY_VILLAGER_NO, 1, 1);
                    }
                    return;
                }else {
                    return;
                }
            }
        }
        //////////////////////////////////// 附魔商人菜单 ////////////////////////////////////////////
        if (event.getView().getTitle().equals("附魔商人菜单_home"))
        {
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            for (int a = 0;;a++)
            {
                if (Fightmonsters.附魔商人菜单.contains("Function_"+a+".RawSlot"))
                {
                    if ((int)Fightmonsters.附魔商人菜单.Yml("int","Function_"+a+".RawSlot") == event.getRawSlot()){
                        switch (a)
                        {
                            case 0:{
                                player.openInventory(附魔商人商品设置.附魔商人菜单_00);
                                return;
                            }
                        }
                    }
                }else{
                    return;
                }
            }
        }
        for (int i = 0;Fightmonsters.附魔商人菜单.contains("Function_"+i);i++)
        {
            if (event.getView().getTitle().equals("附魔商人菜单_0"+i)){
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);
                if (event.getRawSlot() == 53)
                {
                    if (event.getClick().equals(ClickType.LEFT)){
                        player.openInventory(附魔商人商品设置.附魔商人菜单_home);
                        return;
                    }
                    return;
                }

                if (Fightmonsters.附魔商人菜单.contains("Function_"+i+".Get."+event.getRawSlot())){
                    if (金币.金币设置.检测是否购买金币是否满足(player,(int)Fightmonsters.附魔商人菜单.Yml("int","Function_"+i+".Get."+event.getRawSlot()+".Pay.price"))){
                        金币.金币设置.金币支付(player,(int)Fightmonsters.附魔商人菜单.Yml("int","Function_"+i+".Get."+event.getRawSlot()+".Pay.price"));
                        player.playSound(player.getLocation(), ENTITY_VILLAGER_YES, 1, 1);
                        if (event.getRawSlot() == 0){
                            player.setLevel(player.getLevel() + 1);
                        }
                    }else{
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED+"你的金币不够!!!");
                        player.playSound(player.getLocation(), ENTITY_VILLAGER_NO, 1, 1);
                    }
                    return;
                }else {
                    return;
                }
            }
        }
    }
}
