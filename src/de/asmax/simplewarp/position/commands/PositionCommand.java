package de.asmax.simplewarp.position.commands;

import de.asmax.simplewarp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.pr + "§cJust a player can execute this command!");
            return true;
        }
        Player player = (Player)sender;

        if(player.hasPermission("simplewarp.position")) {

            File file = new File("plugins/SimpleWarp", "positions.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            if(args.length != 0) {
                if(args[0].equalsIgnoreCase("list")) {
                    player.sendMessage("§8[§6Position§8] §7Available §9positions: §b" + cfg.getConfigurationSection(".Positions").getKeys(false));
                } else if(args[0].equalsIgnoreCase("del")) {

                    String id = args[1];

                    if(cfg.getString(".Positions." + id) != null) {

                        cfg.set(".Positions." + id, null);
                        player.sendMessage(Main.pr + "The Position §a" + args[1] + " §6has been successfully §cdeleted!");

                    } else {
                        player.sendMessage(Main.pr + "§cThis position didnt exists.");
                        return true;
                    }

                } else {
                    String id = args[0];

                    if(cfg.getString(".Positions." + id) != null) {

                        String world = cfg.getString(".Positions" + "." + id + ".World");

                        int x = cfg.getInt(".Positions" + "." + id + ".X");
                        int y = cfg.getInt(".Positions" + "." + id + ".Y");
                        int z = cfg.getInt(".Positions" + "." + id + ".Z");

                        player.sendMessage("§8[§6Position§8] §9" + id + " §8[§6" + x + "§8, §6" + y + "§8, §6" + z + "§8, §6" + world + "§8]");
                        return true;
                    }

                    String world = player.getWorld().getName();

                    int x = player.getLocation().getBlockX();
                    int y = player.getLocation().getBlockY();
                    int z = player.getLocation().getBlockZ();

                    cfg.set(".Positions" + "." + id + ".World", world);
                    cfg.set(".Positions" + "." + id + ".X", x);
                    cfg.set(".Positions" + "." + id + ".Y", y);
                    cfg.set(".Positions" + "." + id + ".Z", z);

                    Bukkit.broadcastMessage("§8[§6Position§8] §a" + id + " §7from §a" + player.getName() + " §8[§6" + x + "§8, §6" + y + " §8,§6 " + z + " §8,§6 " + world + "§8]");

                    try {
                        cfg.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                player.sendMessage(Main.pr + "Invalid Argument, please use one of the following arguments: §c/position <list | position");
            }

        } else {
            player.sendMessage(Main.pr + "§cYou do not have the Permission to do that!");
        }
        return false;
    }
}
