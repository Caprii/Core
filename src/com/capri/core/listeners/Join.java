package com.capri.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Capri on 5/16/2017.
 */
public class Join implements Listener {
    List<UUID> staff = new ArrayList<UUID>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("staff.join")){
            staff.add(p.getUniqueId());
        }
            for(UUID uuid : staff){
                Player sp = Bukkit.getPlayer(uuid);
                if(sp.hasPermission("staff.supressmsg")) return;
                sp.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aStaff&7] &1" + p.getName() + "&bHas joined the game!"));
            }
    }
}
