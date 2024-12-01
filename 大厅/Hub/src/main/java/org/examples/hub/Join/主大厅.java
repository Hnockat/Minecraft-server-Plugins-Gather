package org.examples.hub.Join;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.examples.hub.Hub;

public class 主大厅 {
    public static final 主大厅 主大厅class = new 主大厅();
    public void Join(Player player){
        Location location = new Location(Bukkit.getWorld("world"),
                (double) Hub.大厅配置文件.Yml("double","主大厅.x"),
                (double) Hub.大厅配置文件.Yml("double","主大厅.y"),
                (double) Hub.大厅配置文件.Yml("double","主大厅.z"),
                (float)(double)Hub.大厅配置文件.Yml("double","主大厅.yaw"),
                (float)(double)Hub.大厅配置文件.Yml("double","主大厅.pitch"));
        player.teleport(location);
    }
}
