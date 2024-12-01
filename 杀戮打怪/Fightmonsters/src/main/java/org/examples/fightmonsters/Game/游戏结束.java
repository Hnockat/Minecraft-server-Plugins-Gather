package org.examples.fightmonsters.Game;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.examples.fightmonsters.Fightmonsters;

public class 游戏结束 {
    public void 游戏结束(World world)
    {
        for (Player p: world.getPlayers()){
            p.sendTitle(ChatColor.GREEN + "游戏结束", ChatColor.GOLD+"感谢您游玩!", 10, 100, 20);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
        }
        Bukkit.getScheduler().cancelTasks(Fightmonsters.getPlugin(Fightmonsters.class));
    }
}
