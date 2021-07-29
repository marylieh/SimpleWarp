package de.asmax.simplewarp.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.asmax.simplewarp.main.Main;

public class WarpsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Main.pr + "§4Just a Player can execute this command!");
				return true;
			}
			Player p = (Player)sender;
			
			if(p.hasPermission("simplewarp.warps")) {
				
				File file = new File("plugins/SimpleWarp", "Warps.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				
				p.sendMessage(Main.pr + cfg.getConfigurationSection(".Warps").getKeys(false));
				
			} else {
				p.sendMessage(Main.pr + "§cYou do not have the permission to do that!");
			}
		return false;
	}

}
