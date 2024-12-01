package org.gun;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class 机枪类 {
      public static 机枪类 GetThis = new 机枪类();
      public static List<Player> 正在安装弹夹的玩家 = new ArrayList<>();
      public void 玩家点击发射键(Player player){
          if (正在安装弹夹的玩家.contains(player)){
              NBT.NbtSet.玩家展示信息(ChatColor.RED+"你正在安装弹夹中",player);
              return;
          }

          ItemStack Gun_This = player.getItemInHand();
          Location location = player.getLocation();
          switch ((String) Gun.GetYml_机枪类型.Yml("String","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(Gun_This)+".发射类型")){
              case "ZD": {
                  if (NBT.NbtSet.子弹容量查(Gun_This) <= 0){
                    NBT.NbtSet.玩家展示信息(ChatColor.RED+"该枪没有子弹",player);
                    player.playSound(player,Sound.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON,1,1);
                    机枪lore更新(Gun_This);
                    return;
                  }
                  NBT.NbtSet.子弹发射减掉操作(Gun_This);
                  location.setY(location.getY() + 1.2);
                  Snowball snowball = player.getWorld().spawn(location, Snowball.class);
                  snowball.setCustomName(NBT.NbtSet.返回该枪的机枪标签(player.getItemInHand())+" & "+NBT.NbtSet.该枪配置的弹夹Get(Gun_This));
                  Vector vector = location.getDirection();

                  vector.multiply((double) Gun.GetYml_机枪类型.Yml("double","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(player.getItemInHand())+".冲量"));
                  player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,1,1);
                  snowball.setVelocity(vector);
                  NBT.NbtSet.玩家展示信息(ChatColor.GREEN+""+NBT.NbtSet.子弹容量查(Gun_This),player);
                  机枪lore更新(Gun_This);
                  return;
              }
              case "SD":{
                  Random 随机数生成 = new Random(); //生成 后坐力的偏移量
                  if (NBT.NbtSet.子弹容量查(Gun_This) <= 0){
                      NBT.NbtSet.玩家展示信息(ChatColor.RED+"该枪没有子弹",player);
                      player.playSound(player,Sound.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON,1,1);
                      机枪lore更新(Gun_This);
                      return;
                  }
                  NBT.NbtSet.子弹发射减掉操作(Gun_This);
                  location.setY(location.getY() + 1.2);
                  for (int i = 0;i < (int) Gun.GetYml_子弹.Yml("int",NBT.NbtSet.该枪配置的弹夹Get(Gun_This)+".散弹枪一次喷射子弹");i++){
                      Snowball snowball = player.getWorld().spawn(location, Snowball.class);
                      snowball.setCustomName(NBT.NbtSet.返回该枪的机枪标签(player.getItemInHand())+" & "+NBT.NbtSet.该枪配置的弹夹Get(Gun_This));
                      Vector vector = location.getDirection();
                      vector.setY(vector.getY() + (-0.1 + (0.1 -(-0.1) * 随机数生成.nextDouble())));
                      vector.setX(vector.getX() + (-0.1 + (0.1 -(-0.1) * 随机数生成.nextDouble())));
                      vector.setZ(vector.getZ() + (-0.1 + (0.1 -(-0.1) * 随机数生成.nextDouble())));
                      vector.multiply((double) Gun.GetYml_机枪类型.Yml("double","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(player.getItemInHand())+".冲量"));
                      snowball.setVelocity(vector);
                  }
                  player.playSound(player, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,1,1);
                  NBT.NbtSet.玩家展示信息(ChatColor.GREEN+""+NBT.NbtSet.子弹容量查(Gun_This),player);
                  机枪lore更新(Gun_This);
                  return;
              }
          }
          player.sendMessage(ChatColor.RED+"未知的发送类型,请联系管理?");
      }
      public void 玩家点击装弹(Player player){
          if (正在安装弹夹的玩家.contains(player)){
              return;
          }
          ItemStack HandGun = player.getInventory().getItemInMainHand();
          for (String part:(List<String>)Gun.GetYml_机枪类型.Yml("List","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(player.getItemInHand())+".子弹")){
                ItemStack itemStack = 子弹.子弹class.子弹给以(part);
                if (player.getInventory().containsAtLeast(itemStack,1)){////////
                    int Time_Tick = (int)Gun.GetYml_子弹.Yml("int",part+".装弹时间");
                    if (!(boolean)Gun.GetYml_机枪类型.Yml("boolean","是否无限子弹")) { //判断游戏设置是否为无限弹药
                        for (ItemStack get : player.getInventory().getContents()) {
                            if (get != null) {
                                if (get.getType().equals(itemStack.getType())) {
                                    if (NBT.NbtSet.get弹夹标签(get).equals(NBT.NbtSet.get弹夹标签(itemStack))) {
                                        get.setAmount(get.getAmount() - 1);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    正在安装弹夹的玩家.add(player);
                    Bukkit.getScheduler().runTaskTimerAsynchronously(Gun.getInstance(),bukkitTask -> {
                        if (!player.isOnline()){
                            正在安装弹夹的玩家.remove(player);
                            Bukkit.getScheduler().runTask(Gun.getInstance(),bukkitTask1 ->{
                                player.getWorld().dropItemNaturally(player.getLocation(),itemStack);
                            });
                            bukkitTask.cancel();
                        }
                        if (!(player.getInventory().getItemInMainHand().equals(HandGun))){
                            正在安装弹夹的玩家.remove(player);
                            NBT.NbtSet.玩家展示信息(ChatColor.RED+"已停止装弹!",player);
                            if (player.getInventory().firstEmpty() != -1){
                                player.getInventory().addItem(itemStack);
                            }else{
                                Bukkit.getScheduler().runTask(Gun.getInstance(),bukkitTask1 ->{
                                    player.getWorld().dropItemNaturally(player.getLocation(),itemStack);
                                });
                            }
                            if (player.getInventory().contains(HandGun)){
                                NBT.NbtSet.进度条记录(HandGun, "null");
                            }
                            bukkitTask.cancel();
                        }
                        if (装弹进度条(Time_Tick,HandGun,player)){
                            正在安装弹夹的玩家.remove(player);
                            NBT.NbtSet.玩家展示信息(ChatColor.BLUE+"你配装了弹夹: "+itemStack.getItemMeta().getDisplayName(),player);
                            NBT.NbtSet.该枪配装的弹夹Set(HandGun,part); //弹夹设置标签 好分辨弹头类型
                            NBT.NbtSet.子弹初始化(HandGun,(int) Gun.GetYml_子弹.Yml("int",part+".容量"));
                            NBT.NbtSet.进度条记录(HandGun,"null");
                            机枪lore更新(HandGun);
                            bukkitTask.cancel();
                        }

                    },0,10); //表示0.5秒的装弹时间刻
                    return;
                }//////////////
          }
          //没有该弹夹
          NBT.NbtSet.玩家展示信息(ChatColor.RED+"你的背包没有该类型的弹夹!",player);
      }
      private boolean 装弹进度条(int Time_Tick,ItemStack Gun,Player player){
          if (NBT.NbtSet.获取进度条(Gun).equals("null")){
              NBT.NbtSet.进度条记录(Gun,Time_Tick+"");
              return false;
          }
          //获取当前Tick 进行时间
          String newTime_Tick = NBT.NbtSet.获取进度条(Gun);
          int newTime_Tick_GetInt = Integer.parseInt(newTime_Tick);

          //装弹时间告诉玩家 ||表示0.5秒   Time_Tick总共花费时间  newTime_Tick_GetInt现在花费时间
          String pb = ""; //进度条
          for (int i = 0;i <= Time_Tick/10;i++){
              if (i >= newTime_Tick_GetInt/10){
                  pb += "§a|";
                  continue;
              }
              pb += "§c|";
          }
          NBT.NbtSet.玩家展示信息(pb,player);

          if (newTime_Tick_GetInt <= 0){
              return true;
          }
          newTime_Tick_GetInt -= 10;
          NBT.NbtSet.进度条记录(Gun,newTime_Tick_GetInt+"");
          return false;
      }
    private void 机枪lore更新(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        list.add(ChatColor.BLUE+"弹夹容量: "+ChatColor.GREEN+NBT.NbtSet.子弹容量查(itemStack));
        list.add(ChatColor.GREEN+"该枪支射击类型为: "+ChatColor.GREEN+give枪.ThisGet.射击类型((String) Gun.GetYml_机枪类型.Yml("String","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(itemStack)+".发射类型")));
        list.add(ChatColor.BLUE+"该枪支持的装配弹夹列表: ");
        for (String bulle: (List<String>)Gun.GetYml_机枪类型.Yml("List","机枪类型."+NBT.NbtSet.返回该枪的机枪标签(itemStack)+".子弹")){
            list.add(ChatColor.GREEN+bulle+"弹夹");
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
    }
}
