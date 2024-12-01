package org.examples.hub.redis;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Redis {
    public static Redis rediscalss = new Redis();
    // 传送请求 string = "[玩家名字] [服务器名字]"
    public void ServerSocket(String Commands){
        try {
            Socket socket = new Socket("127.0.0.1",25598);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(Commands.getBytes());
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e+"数据请求失败!");
        }
    }
    //开发中
    public void 跨服传送(String server,String playername){
        ServerSocket("ServerTp "+playername+" "+server);
    }



}
