package com.capri.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Capri on 5/16/2017.
 */
public class Leave extends Join{
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("staff.join")){
            staff.remove(p.getUniqueId());
        }
    }
}
