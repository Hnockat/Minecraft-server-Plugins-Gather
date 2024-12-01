package org.velocity;
import com.velocitypowered.api.proxy.Player;
import java.io.FileWriter;
public class 封禁 {
      public static 封禁 classBan = new 封禁();
      public void 封禁该玩家(Player player,int Time,String 原因){
                  try {
                      //将封禁玩家写入服务器
                      FileWriter writer = new FileWriter("ServerPlayerManager\\BanPlayer\\"+player.getUsername()+".txt",true);
                      writer.write("Time: "+ Time+"\n");
                      writer.write("Cause: "+原因);
                      writer.close();
                  }catch (Exception e){
                      System.out.println(e+"封禁失败?:"+player.getUsername()+",原因->"+原因);
                  }
      }





}
