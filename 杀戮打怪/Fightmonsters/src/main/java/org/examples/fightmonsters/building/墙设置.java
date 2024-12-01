package org.examples.fightmonsters.building;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.examples.fightmonsters.Fightmonsters;
import org.examples.fightmonsters.Game.游戏开始;

import java.util.List;

public class 墙设置 {
    int wall_time = 6;
    public void 墙打开(int time,World world)
    {
        if (游戏开始.当前是否可以刷怪 == true){
            return;
        }
        if (Fightmonsters.左右门设置注册.contains_List_value("List",游戏开始.当前层数)){
            this.墙打开_left(time,world);
            this.墙打开_right(time,world);
        }
        else
        {
            this.墙打开_left(time,world);
        }
    }

    /***
     * 墙关闭 意味着 该层结束 生成暂停区进行暂停时间段
     * @param time
     * @param world
     */
    public void 墙关闭(int time,World world)
    {
        if (游戏开始.墙关闭 == false){
            return;
        }
        if (Fightmonsters.左右门设置注册.contains_List_value("List",游戏开始.当前层数)){
            this.墙关闭_left(time,world);
            this.墙关闭_right(time,world);
        }
        else
        {
            this.墙关闭_left(time,world);
        }
    }

    public void 墙打开_left(int time, World world)
    {
        for (int i = 1;i <= 5;i++)
        {
            if (time == (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".times"))
            {
                if (Fightmonsters.Wall_left_Lost.激活().isList(i+".x")){
                    List<Integer> list_X;
                    list_X = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",i+".x");
                    int y = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".y");
                    int z = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".z");
                    for (int a = 0;a <= list_X.size() - 1;a++){
                        Block block = world.getBlockAt(list_X.get(a),y,z);
                        block.setType(Material.AIR);
                    }
                    return;
                }
                if (Fightmonsters.Wall_left_Lost.激活().isList(i+".y")){
                    List<Integer> list_Y;
                    list_Y = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",i+".y");
                    int x = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".x");
                    int z = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".z");
                    for (int a = 0;a <= list_Y.size() - 1;a++){
                        Block block = world.getBlockAt(x,list_Y.get(a),z);
                        block.setType(Material.AIR);
                    }
                    return;
                }
                if (Fightmonsters.Wall_left_Lost.激活().isList(i+".z"))
                {
                    List<Integer> list_Z;
                    list_Z = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",i+".z");
                    int x = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".x");
                    int y = (int)Fightmonsters.Wall_left_Lost.Yml("int",i+".y");
                    for (int a = 0;a <= list_Z.size() - 1;a++){
                        Block block = world.getBlockAt(x,y,list_Z.get(a));
                        block.setType(Material.AIR);
                    }
                    return;
                }

            }
        }
    }
    public void 墙打开_right(int time,World world)
    {
        for (int i = 1;i <= 5;i++)
        {
            if (time == (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".times"))
            {
                if (Fightmonsters.Wall_right_Lost.激活().isList(i+".x")){
                    List<Integer> list_X;
                    list_X = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",i+".x");
                    int y = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".y");
                    int z = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".z");
                    for (int a = 0;a <= list_X.size() - 1;a++){
                        Block block = world.getBlockAt(list_X.get(a),y,z);
                        block.setType(Material.AIR);
                    }
                    return;
                }
                if (Fightmonsters.Wall_right_Lost.激活().isList(i+".y")){
                    List<Integer> list_Y;
                    list_Y = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",i+".y");
                    int x = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".x");
                    int z = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".z");
                    for (int a = 0;a <= list_Y.size() - 1;a++){
                        Block block = world.getBlockAt(x,list_Y.get(a),z);
                        block.setType(Material.AIR);
                    }
                    return;
                }
                if (Fightmonsters.Wall_right_Lost.激活().isList(i+".z"))
                {
                    List<Integer> list_Z;
                    list_Z = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",i+".z");
                    int x = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".x");
                    int y = (int)Fightmonsters.Wall_right_Lost.Yml("int",i+".y");
                    for (int a = 0;a <= list_Z.size() - 1;a++){
                        Block block = world.getBlockAt(x,y,list_Z.get(a));
                        block.setType(Material.AIR);
                    }
                    return;
                }

            }
        }
    }
    public void 墙关闭_left(int time,World world)
    {
        if (wall_time == 0){
            游戏开始.建筑初始化.钟生成(world);
            游戏开始.墙关闭 = false;
            游戏开始.当前主线程是否启动 =false;
            wall_time = 6;
            return;
        }wall_time--;
                if (Fightmonsters.Wall_left_Lost.激活().isList(wall_time+".x")){
                    List<Integer> list_X;
                    list_X = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",wall_time+".x");
                    int y = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".y");
                    int z = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".z");
                    for (int a = 0;a <= list_X.size() - 1;a++){
                        Block block = world.getBlockAt(list_X.get(a),y,z);
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
                if (Fightmonsters.Wall_left_Lost.激活().isList(wall_time+".y")){
                    List<Integer> list_Y;
                    list_Y = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",wall_time+".y");
                    int x = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".x");
                    int z = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".z");
                    for (int a = 0;a <= list_Y.size() - 1;a++){
                        Block block = world.getBlockAt(x,list_Y.get(a),z);
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
                if (Fightmonsters.Wall_left_Lost.激活().isList(wall_time+".z"))
                {
                    List<Integer> list_Z;
                    list_Z = (List<Integer>) Fightmonsters.Wall_left_Lost.Yml("List",wall_time+".z");
                    int x = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".x");
                    int y = (int)Fightmonsters.Wall_left_Lost.Yml("int",wall_time+".y");
                    for (int a = 0;a <= list_Z.size() - 1;a++){
                        Block block = world.getBlockAt(x,y,list_Z.get(a));
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
    }
    public void 墙关闭_right(int time,World world){
                if (Fightmonsters.Wall_right_Lost.激活().isList(wall_time+".x")){
                    List<Integer> list_X;
                    list_X = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",wall_time+".x");
                    int y = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".y");
                    int z = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".z");
                    for (int a = 0;a <= list_X.size() - 1;a++){
                        Block block = world.getBlockAt(list_X.get(a),y,z);
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
                if (Fightmonsters.Wall_right_Lost.激活().isList(wall_time+".y")){
                    List<Integer> list_Y;
                    list_Y = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",wall_time+".y");
                    int x = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".x");
                    int z = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".z");
                    for (int a = 0;a <= list_Y.size() - 1;a++){
                        Block block = world.getBlockAt(x,list_Y.get(a),z);
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
                if (Fightmonsters.Wall_right_Lost.激活().isList(wall_time+".z"))
                {
                    List<Integer> list_Z;
                    list_Z = (List<Integer>) Fightmonsters.Wall_right_Lost.Yml("List",wall_time+".z");
                    int x = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".x");
                    int y = (int)Fightmonsters.Wall_right_Lost.Yml("int",wall_time+".y");
                    for (int a = 0;a <= list_Z.size() - 1;a++){
                        Block block = world.getBlockAt(x,y,list_Z.get(a));
                        block.setType(Material.OAK_FENCE);
                    }
                    return;
                }
    }
}
