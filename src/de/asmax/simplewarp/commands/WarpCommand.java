package de.asmax.simplewarp.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.asmax.simplewarp.main.Main;

public class WarpCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Main.pr + "§4Just a Player can execute this command!");
				return true;
			}
			Player p = (Player)sender;
			
			if(p.hasPermission("simplewarp.warp")) {
				if(args.length == 1) {
					
					File file = new File("plugins/SimpleWarp", "Warps.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
					
					String id = args[0];

					if(cfg.getString(".Warps." + id) != null) {

					} else {
						sender.sendMessage(Main.pr + "§cThis warp didn't exists!");
						return true;
					}
					
					World world = Bukkit.getWorld(cfg.getString(".Warps" + "." + id + ".World"));
					
					double x = cfg.getDouble(".Warps" + "." + id + ".X");
					double y = cfg.getDouble(".Warps" + "." + id + ".Y");
					double z = cfg.getDouble(".Warps" + "." + id + ".Z");
					
					float yaw = (float) cfg.getDouble(".Warps" + "." + id + ".Yaw");
					float pitch = (float) cfg.getDouble(".Warps" + "." + id + ".Pitch");
					
					p.teleport(new Location(world, x, y, z, yaw, pitch));
					
					p.sendMessage(Main.pr + "§aYou have been teleported to §6" + id + "§a!");
					
				} else {
					p.sendMessage(Main.pr + "§cPlease use: §7/warp <warpname>");
				}
			} else {
				p.sendMessage(Main.pr + "§cYou do not have the permission to do that!");
			}
		return false;
	}

}
