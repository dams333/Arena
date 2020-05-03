package ch.dams333.arena;

import ch.dams333.arena.commands.AreneCommand;
import ch.dams333.arena.commands.PVPCommand;
import ch.dams333.arena.listeners.PVPListener;
import ch.dams333.arena.objects.arene.AreneManager;
import ch.dams333.arena.objects.arene.AreneRectangle;
import ch.dams333.arena.objects.arene.SdchachazeArene;
import org.bukkit.plugin.java.JavaPlugin;

public class Arena extends JavaPlugin {

    public AreneManager areneManager;

    public SdchachazeArene arene;

    public AreneRectangle currentRectangle;

    public boolean pvp = false;
    public boolean globalPvp = false;

    @Override
    public void onEnable(){

        getCommand("arene").setExecutor(new AreneCommand(this));
        getCommand("pvp").setExecutor(new PVPCommand(this));

        areneManager = new AreneManager(this);

        areneManager.deserializeArene();

        getServer().getPluginManager().registerEvents(new PVPListener(this), this);

    }

    @Override
    public void onDisable(){
        areneManager.serializeArene();
    }

}
