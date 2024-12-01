package org.gunPvP;

import org.bukkit.plugin.java.JavaPlugin;

public final class GunPvP extends JavaPlugin {
    public static GetYml 地图Yml = new GetYml("地图.yml");
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveResource("地图.yml",false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
