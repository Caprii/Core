package com.capri.core.commands;

import com.capri.core.staffmode.Freeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Capri on 5/16/2017.
 */
public class FreezeCommand implements CommandExecutor {
    public List<UUID> frozenPlayers = new ArrayList<UUID>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String stringLabel, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (cmd.getName().equalsIgnoreCase("freeze")) {
            if (!(sender.hasPermission("staff.freeze"))) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4No permission..."));
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("Command cannot be ran by console");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Specify a player!"));
                return true;
            }
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Player doesn't exit or is not online"));
            }
            if (frozenPlayers.contains(target)) {
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have been unfrozen!"));
                frozenPlayers.remove(target);
            }
            frozenPlayers.add(target.getUniqueId());
        }
        return true;
    }
}
