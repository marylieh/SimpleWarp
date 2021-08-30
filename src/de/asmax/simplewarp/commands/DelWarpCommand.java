package de.asmax.simplewarp.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.asmax.simplewarp.main.Main;

public class DelWarpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Main.pr + "§4Just a Player can execute this command!");
				return true;
			}
			Player p = (Player)sender;
			
			if(p.hasPermission("simplewarp.delwarp")) {
				if(args.length == 1) {
					
					File file = new File("plugins/SimpleWarp", "Warps.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
					
					String id = args[0];
					
					cfg.set(".Warps" + "." + id, null);
					
					p.sendMessage(Main.pr + "§aThe Warp §6" + id + "§a was successfully deleted!");
					
					try {
						cfg.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					p.sendMessage(Main.pr + "§cPlease use: §7/warp <warpname>");
				}
			} else {
				p.sendMessage(Main.pr + "§cYou do not have the permission to do that!");
			}
		return false;
	}

}
