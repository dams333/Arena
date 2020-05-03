package ch.dams333.arena.commands;

import ch.dams333.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PVPCommand implements CommandExecutor {
    Arena main;
    public PVPCommand(Arena arena) {
        this.main = arena;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("enable")){
                main.globalPvp = true;
                sender.sendMessage(ChatColor.GREEN + "Le PVP est maintenant activé sur toute la map (le PVP dans l'arène reste désactivé si il l'est)");
                return true;
            }
            if(args[0].equalsIgnoreCase("disable")){
                main.globalPvp = false;
                sender.sendMessage(ChatColor.GREEN + "Le PVP est maintenant désactivé sur toute la map (le PVP dans l'arène reste activé si il l'est)");
                return true;
            }
        }
        sender.sendMessage(ChatColor.RED + "/pvp <enable/disable>");
        return false;
    }
}
