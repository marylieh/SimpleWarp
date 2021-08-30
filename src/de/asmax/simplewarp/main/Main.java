package de.asmax.simplewarp.main;

import de.asmax.simplewarp.position.commands.PositionCommand;
import de.asmax.simplewarp.utils.Updater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.asmax.simplewarp.commands.DelWarpCommand;
import de.asmax.simplewarp.commands.SetWarpCommand;
import de.asmax.simplewarp.commands.WarpCommand;
import de.asmax.simplewarp.commands.WarpsCommand;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public static String pr = "§6[SimpleWarp] ";
	
	@Override
	public void onEnable() {
		plugin = this;

		genFilesPosition();
		
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

	private void genFilesPosition() {
		File file = new File("plugins/SimpleWarp", "config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		if(!cfg.getBoolean("FirstSetup")) {
			return;
		}
		cfg.set("FirstSetup", false);
		cfg.set("PositionSystem", true);

		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
