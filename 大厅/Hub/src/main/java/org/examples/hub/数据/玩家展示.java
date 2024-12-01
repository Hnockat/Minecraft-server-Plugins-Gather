package org.examples.hub.数据;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;

public class 玩家展示 {
    public static 玩家展示 玩家展示实例化 = new 玩家展示();

   public void 玩家展示下标(String s, Player player){
        BaseComponent[] baseComponents = new ComponentBuilder().append(s).create();
        player.sendMessage(ChatMessageType.ACTION_BAR,baseComponents);
    }

    public void 玩家展示下标(String s, Player player, ChatColor color){
        BaseComponent[] baseComponents = new ComponentBuilder().append(color + s).create();
        player.sendMessage(ChatMessageType.ACTION_BAR,baseComponents);
    }



}
