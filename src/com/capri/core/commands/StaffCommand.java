package com.capri.core.commands;

import com.capri.core.staffmode.StaffMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Capri on 5/16/2017.
 */
public class StaffCommand extends StaffMode implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String stringLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("staff")){
            if(!(sender.hasPermission("staff.staffmode"))){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4No permission..."));
                return true;
            }
            if(!(sender instanceof Player)){
                sender.sendMessage("Command cannot be ran by console!");
            }
            Player p = (Player) sender;
            staffMode(p);
        }
        return false;
    }
}
