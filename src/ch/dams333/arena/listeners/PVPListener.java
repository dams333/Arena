package ch.dams333.arena.listeners;

import ch.dams333.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPListener implements Listener {
    Arena main;
    public PVPListener(Arena arena) {
        this.main = arena;
    }

    @EventHandler
    public void pvp(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            if(main.areneManager.isInArene(e.getEntity().getLocation()) && main.areneManager.isInArene(e.getDamager().getLocation())){
                if(!main.pvp){
                    e.setCancelled(true);
                }
            }else{
                if(!main.globalPvp){
                    e.setCancelled(true);
                }
            }
        }
    }

}
