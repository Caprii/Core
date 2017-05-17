package com.capri.core;

import com.capri.core.commands.FreezeCommand;
import com.capri.core.commands.StaffCommand;
import com.capri.core.listeners.Join;
import com.capri.core.listeners.Leave;
import com.capri.core.staffmode.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Capri on 5/16/2017.
 */
public class Core extends JavaPlugin{
    public void onEnable(){
        final FileConfiguration config = this.getConfig();
        config.addDefault("ts", "ts.myhcf.net");
        registerCommands();
        registerEvents();
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getConsoleSender().sendMessage("\nCore enabled\n");
    }
    public void onDisable(){
        Bukkit.getServer().getConsoleSender().sendMessage("\nCore disabled\n");
    }
    public void registerCommands(){
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("staff").setExecutor(new StaffCommand());
    }
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new Leave(), this);
        pm.registerEvents(new Freeze(this), this);
    }
}
