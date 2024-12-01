package org.velocity;

import com.google.inject.Inject;
import com.google.inject.spi.Message;
import com.velocitypowered.api.event.connection.ConnectionHandshakeEvent;
import com.velocitypowered.api.event.player.GameProfileRequestEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.util.GameProfile;
import net.kyori.adventure.text.Component;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Plugin(
        id = "velocity",
        name = "Velocity",
        version = "1.0-SNAPSHOT"
)
public class Velocity {

    private final ProxyServer proxy;
    @Inject
    public Velocity(ProxyServer proxy) {
        this.proxy = proxy;
    }

    public static Set<Player> Playeronlinei =new HashSet<>(); //玩家是否已经登录
    public static Set<String> 管理员 = new HashSet<>();

    public void 初始化管理员() {
        File FR = new File("ServerPlayerManager/administrator");
        File[] files = FR.listFiles();
        if (files != null) {
            for (File file : files) {
                管理员.add(file.getName().replace(".txt",""));
            }
        } else {
            System.out.println("没有查找到管理员!");
        }
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        //初始化管理员
        初始化管理员();
        //开启服务器监听!
        this.ServerTp();
    }
    public void ServerTp(){
        System.out.println("已经打开本地服务器Server连接,port:25598");
        proxy.getScheduler()
                .buildTask(this, () -> {
                    while(true) {
                        try {
                            ServerSocket ss = new ServerSocket(25598);
                            Socket socket = ss.accept();
                            InputStream is = socket.getInputStream();
                            int a;
                            String AllString = "";
                            while ((a = is.read()) != -1) {
                                AllString += (char)a;
                            }
                            socket.close();
                            ss.close();
                            if (AllString.contains("ServerTp")){ //跨服传送
                                AllString = AllString.replace("ServerTp ","");
                                String[] pass = AllString.split(" ");
                                跨服传送(pass[0],pass[1]);
                                continue;
                            }
                            if (AllString.contains("ServerLogin")){
                                AllString = AllString.replace("ServerLogin ","");
                                Playeronlinei.add(proxy.getPlayer(AllString).get());
                                continue;
                            }

                            System.out.println("未知指令"+AllString);
                        } catch (IOException e) {
                            throw new RuntimeException(e+"玩家跨服异常");
                        }
                    }
                }).schedule();
    }

    /*public void ServerSocket(String string) throws IOException {
        Socket socket = new Socket("127.0.0.1",25597);
        OutputStream outputStream = socket.getOutputStream();
        String pris = string;
        outputStream.write(pris.getBytes());
        outputStream.close();
        socket.close();
    }*/


    @Subscribe
    public void KickedFromServerEvent(KickedFromServerEvent event)
    {
        if (Playeronlinei.contains(event.getPlayer()))
        {
            跨服传送(event.getPlayer().getUsername(),"HUB");
        }
    }


    @Subscribe
    public void PlayerOut(DisconnectEvent event){
        if (Playeronlinei.contains(event.getPlayer())){
            Playeronlinei.remove(event.getPlayer());
        }
    }
    @Subscribe
    public void 玩家加入服务器验证(ConnectionHandshakeEvent event){


    }


    @Subscribe
    public void 跨服传送(String Player,String Server){
        Optional<Player> player= proxy.getPlayer(Player);
        Optional<RegisteredServer> registeredServer = proxy.getServer(Server);
        ConnectionRequestBuilder conn = player.get().createConnectionRequest(registeredServer.get());
        conn.fireAndForget();//启动与远程服务器的连接，而无需等待结果。
    }
    //监听服务器发出指令传送玩家到指定服务器
    //指令监听器 监听玩家输入的指令!
    @Subscribe
    public void Playerset(CommandExecuteEvent event)
    {
        String command = event.getCommand();
        Player player = (Player)event.getCommandSource();
        if (command.contains("server")){
            player.disconnect(Component.text("禁止使用该指令!!!"));
            return;
        }
        if (!Playeronlinei.contains(player)){
            return;
        }
        String[] arr = command.split(" ");
        if (arr[0].equalsIgnoreCase("hub")){
            if (player.getCurrentServer().get().getServer().getServerInfo().getName().equals("HUB")){
                return;
            }
            player.sendPlainMessage(Color.green+"正在把你传送到大厅HUB");

            Optional<RegisteredServer> registeredServer = proxy.getServer("HUB");
            ConnectionRequestBuilder a = player.createConnectionRequest(registeredServer.get());
            a.fireAndForget();//启动与远程服务器的连接，而无需等待结果。
            return;
        }
        ////////管理员使用
        if (arr[0].equalsIgnoreCase("/GL")){
            if (!管理员.contains(player.getUsername())){
                return;
            }
            if (event.getCommand().equals("/GL")){
                player.sendPlainMessage("§a____________________________________________________");
                player.sendPlainMessage("§a管理员指令使用 红色文字代表服务器会记录操作");
                player.sendPlainMessage("§c//ban <玩家名字> <time单位天> <封禁原因>  §b[封禁该玩家多少天,time=999代表永久封禁]");
                player.sendPlainMessage("§c//banip <玩家名字> <time单位天> <封禁原因>  §b[封禁该玩家所有ip,防止用小号,time=999代表永久封禁]");
                player.sendPlainMessage("§c//gm 1 [切换创造模式]");
                player.sendPlainMessage("§a//gm 0 [切换生存模式]");
                player.sendPlainMessage("§a//gm 2 [切换观赏模式]");
                player.sendPlainMessage("§a//cout <文字> [通知登录服务器里面的全部玩家]");
                player.sendPlainMessage("§a____________________________________________________");
                return;
            }
        }

        //向服务器所有已经登录的玩家发送消息
        if (arr[0].equalsIgnoreCase("/cout")){
            if (!管理员.contains(player.getUsername())){
                return;
            }
            player.sendPlainMessage("§a已经发出全服公告!");
            String[] str_arr = event.getCommand().split(" ");
            for(Player player1 : Playeronlinei){
                player1.sendPlainMessage(str_arr[1]);
            }
            return;
        }

        if (arr[0].equalsIgnoreCase("/ban")){
            if (!管理员.contains(player.getUsername())) {
                return;
            }
            try {
                Player player1 = proxy.getPlayer(arr[1]).get();
                int Time = Integer.parseInt(arr[2]);
                String 原因 = arr[3];
                    player.sendPlainMessage("§4成功封禁["+player1.getUsername()+"],时间Time={"+Time+"},§a原因:"+原因);
                String str = "§a你被管理员踢出了服务器\n封🈲时间: "+Time+"天\n§e原因: "+原因+"\n\n\n§n§b如果有问题请加群:958333627";
                //封禁调用code
                封禁.classBan.封禁该玩家(player1,Time,原因);
                //踢出服务器!
                player1.disconnect(Component.text(str));
            }catch (Exception e){
                player.sendPlainMessage("指令格式错误!!!!");
            }
            return;
        }

    }

}
