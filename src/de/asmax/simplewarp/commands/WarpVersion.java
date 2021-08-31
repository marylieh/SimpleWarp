package de.asmax.simplewarp.commands;

import de.asmax.simplewarp.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpVersion implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String VERSION = Main.getVersion();

        sender.sendMessage(Main.pr + ChatColor.GREEN + VERSION);
        return false;
    }
}
