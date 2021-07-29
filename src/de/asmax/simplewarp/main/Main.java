package de.asmax.simplewarp.main;

import de.asmax.simplewarp.position.commands.PositionCommand;
import de.asmax.simplewarp.utils.Updater;
import org.bukkit.plugin.java.JavaPlugin;

import de.asmax.simplewarp.commands.DelWarpCommand;
import de.asmax.simplewarp.commands.SetWarpCommand;
import de.asmax.simplewarp.commands.WarpCommand;
import de.asmax.simplewarp.commands.WarpsCommand;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public static String pr = "§6[SimpleWarp] ";
	
	@Override
	public void onEnable() {
		plugin = this;
		
		getCommand("setwarp").setExecutor(new SetWarpCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("delwarp").setExecutor(new DelWarpCommand());
		getCommand("warps").setExecutor(new WarpsCommand());
		getCommand("position").setExecutor(new PositionCommand());

		Updater updater = new Updater(this, 97545, getFile(), Updater.UpdateType.DEFAULT, true);

	}

	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
