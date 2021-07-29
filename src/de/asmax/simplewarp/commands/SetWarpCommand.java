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

public class SetWarpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Main.pr + "§4Just a Player can execute this command!");
				return true;
			}
			Player p = (Player)sender;
			if(p.hasPermission("simplewarp.setwarp")) {
				if(args.length == 1) {
					
					File file = new File("plugins/SimpleWarp", "Warps.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
					
					String id = args[0];
					
					String world = p.getWorld().getName();
					
					double x = p.getLocation().getX();
					double y = p.getLocation().getY();
					double z = p.getLocation().getZ();
					
					double yaw = p.getLocation().getYaw();
					double pitch = p.getLocation().getPitch();
					
					cfg.set(".Warps" + "." + id + ".World", world);
					cfg.set(".Warps" + "." + id + ".X", x);
					cfg.set(".Warps" + "." + id + ".Y", y);
					cfg.set(".Warps" + "." + id + ".Z", z);
					cfg.set(".Warps" + "." + id + ".Yaw", yaw);
					cfg.set(".Warps" + "." + id + ".Pitch", pitch);
					
					p.sendMessage(Main.pr + "§aYou succesfully set the Warp §6" + id + "§a!");
					
					try {
						cfg.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					p.sendMessage(Main.pr + "§cPlease use: §7/setwarp <warpname>");
				}
			} else {
				p.sendMessage(Main.pr + "§cYou do not have the permission to do that!");
			}
		return false;
	}

}
