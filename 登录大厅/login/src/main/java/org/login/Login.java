package org.login;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Login extends JavaPlugin {
    public static GetYml 登录大厅 = new GetYml("登录大厅.yml");
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new 监听器(),this);
        this.saveResource("登录大厅.yml",false);
        this.getCommand("reg").setExecutor(new 指令());
        this.getCommand("l").setExecutor(new 指令());
        this.getCommand("login").setExecutor(new 指令());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
