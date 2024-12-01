# Velocity开发

## 事件监听:

```
@Subscribe 监听注解
```

```
ConnectionHandshakeEvent  当客户端和代理之间建立握手时，将触发此事件。 //玩家加入服务器执行该监听器
```

```
@Subscribe //向玩家发送消息!
public void Playerset(PlayerChatEvent event)
{
    event.getPlayer().sendPlainMessage(""); 
}
```

```
CommandExecuteEvent  当有人执行命令时，将触发此事件。Velocity 将等待此事件完成 在尝试处理命令和/或将其转发到服务器之前触发。
```

```
DisconnectEvent 当玩家断开与代理的连接时，将触发此事件
```





## 服务器跨服传送

```
跨服端 传入指令
ServerTp <玩家名字> <服务器名字>  //跨服传送
ServerMapoverload <复制物> <复制到的地址>  //可以重新加载地图所用 比如杀戮打怪的地图重载

ServerLogin <玩家名字>     //发送玩家已经成功登录账号处理



```

```java
@Subscribe //这是一个简单的玩家跨服传送代码
public void Playerset(PlayerChatEvent event)
{
    Optional<RegisteredServer> registeredServer = proxy.getServer("Fightmonsters");
    ConnectionRequestBuilder a = event.getPlayer().createConnectionRequest(registeredServer.get());
    a.fireAndForget();//启动与远程服务器的连接，而无需等待结果。
}
```

```java
@Subscribe //这是第二种传送玩家的方法
public void Playerset(PlayerChatEvent event)
{
      InetSocketAddress add=new InetSocketAddress("127.0.0.1",30001);
      ServerInfo info = new ServerInfo("",add);
      RegisteredServer registeredServer = proxy.createRawRegisteredServer(info);
      ConnectionRequestBuilder a = event.getPlayer().createConnectionRequest(registeredServer);
      a.fireAndForget();
}
```

# 服务器端口

velocity跨服端 25598

