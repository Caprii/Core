package com.capri.core.staffmode;

import com.capri.core.commands.FreezeCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import com.capri.core.Core;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by Capri on 5/16/2017.
 */
public class Freeze implements Listener{
    Core Core;
    FreezeCommand FreezeCommand;
    public Freeze(Core instance){
        Core = instance;
    }
    public Freeze(FreezeCommand instance){
        FreezeCommand = instance;
    }

    String ts = "ts";
    String freezeMessage = "&f████&4█&f████\n" +
            "&f███&4█&6█&4█&f███\n" +
            "&f██&4█&6█&0█&6█&4█&f███ &l&4Do NOT Logout!\n" +
            "&f██&4█&6█&0█&6█&4█&f███ &eIf you logout you will be &lBANNED!\n" +
            "&f█&4█&6██&0█&6██&4█&f█ Please download Teamspeak and connect to:\n" +
            "&f█&4█&6█████&4█&f█ &b " + ts + "\n" +
            "&4█&6███&0█&6███&f█\n" +
            "&4█████████";





    public boolean isFrozen(Player p){
        if(FreezeCommand.frozenPlayers.contains(p.getUniqueId())){
            return true;
        }
        return false;
    }
    public void freeze(Player p){
        if(!isFrozen(p)){
            FreezeCommand.frozenPlayers.remove(p);
        }
        FreezeCommand.frozenPlayers.add(p.getUniqueId());
}

      @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
          Player p = e.getPlayer();


          if(FreezeCommand.frozenPlayers.contains(p)){
              e.setFrom(e.getFrom());

              p.sendMessage("");
              p.sendMessage(ChatColor.translateAlternateColorCodes('&', freezeMessage));
              p.sendMessage("");
          }
      }
      @EventHandler
    public void playerDropEvent(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(FreezeCommand.frozenPlayers.contains(p)){
            e.setCancelled(true);
        }
    }
    public void reminderNotice(Player p){
        if(FreezeCommand.frozenPlayers.contains(p)){
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(new Core(), new Runnable() {
                @Override
                public void run() {
                    p.sendMessage(freezeMessage);
                }
            },0, 200);
        }
    }
    public void frozenPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(!(isFrozen(p))){
            return;
        }
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName());
    }
}
