package ch.dams333.arena.commands;

import ch.dams333.arena.Arena;
import ch.dams333.arena.objects.arene.AreneRectangle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreneCommand implements CommandExecutor {
    Arena main;
    public AreneCommand(Arena arena) {
        this.main = arena;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("rectangle")){
                    if(args.length >= 2){
                        if(args[1].equalsIgnoreCase("add")) {
                            if(main.currentRectangle == null){
                                main.currentRectangle = new AreneRectangle(p.getLocation());
                                p.sendMessage(ChatColor.GREEN + "Vous commencez la création d'un rectangle. Retapez cette commande dans le coin opposé");
                            }else{
                                main.currentRectangle.setLoc2(p.getLocation());
                                main.areneManager.addRectangle(main.currentRectangle);
                                main.currentRectangle = null;
                                p.sendMessage(ChatColor.GREEN + "Vous avez fin la création du rectangle. Retaper cette commande créera un nouveau rectangle");
                            }
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("remove")){
                            if(args.length == 3){
                                if(isInt(args[2])){
                                    int index = Integer.parseInt(args[2]);
                                    if(main.arene.getRectangles().size() >= index && index > 0){
                                        index = index - 1;
                                        main.areneManager.removeRectangle(index);
                                        p.sendMessage(ChatColor.GREEN + "Vous avez bien supprimé le rectangle à l'index " + args[2]);
                                        return true;
                                    }
                                }
                                p.sendMessage(ChatColor.RED + "Il n'y a pas de rectangle à l'index " + args[2]);
                                return true;
                            }
                            p.sendMessage(ChatColor.RED + "/arene rectangle remove <index>");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("list")){
                            int index = 1;
                            p.sendMessage(ChatColor.DARK_GREEN + "======= Liste des rectangle =======");
                            for(AreneRectangle rectangle : main.arene.getRectangles()){
                                p.sendMessage(ChatColor.GOLD + String.valueOf(index) + ") " + ChatColor.LIGHT_PURPLE + "X: " + Math.round(rectangle.getLoc1().getX())+ ",Y: " + Math.round(rectangle.getLoc1().getY())+ ",Z: " + Math.round(rectangle.getLoc1().getZ())+ " ---> X: " + Math.round(rectangle.getLoc2().getX())+ ",Y: " + Math.round(rectangle.getLoc2().getY())+ ",Z: " + Math.round(rectangle.getLoc2().getZ()));
                                index++;
                            }
                            return true;
                        }
                    }
                    p.sendMessage(ChatColor.RED + "/arene rectangle <add/remove/list>");
                    return true;
                }
                if(args[0].equalsIgnoreCase("pvp")){
                    if(args.length == 2) {
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.pvp = true;
                            p.sendMessage(ChatColor.GREEN + "Le PVP est maintenant activé dans l'arène");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.pvp = false;
                            p.sendMessage(ChatColor.GREEN + "Le PVP est maintenant désactivé dans l'arène");
                            return true;
                        }
                    }
                    p.sendMessage(ChatColor.RED + "/arene pvp <enable/disable>");
                    return true;
                }
            }
            p.sendMessage(ChatColor.RED + "/arene <rectangle/pvp>");
            return true;
        }
        System.out.println(ChatColor.RED + "Vous devez être connecté sur le serveur");
        return false;
    }

    private boolean isInt(String strNum) {
        boolean ret = true;
        try {

            Integer.parseInt(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

}
