package ch.dams333.arena.objects.arene;

import ch.dams333.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

public class AreneManager {
    Arena main;
    public AreneManager(Arena arena) {
        this.main = arena;
    }

    public void serializeArene(){
        for(String key : main.getConfig().getKeys(false)){
            main.getConfig().set(key, null);
        }
        main.getConfig().set("PVP", main.pvp);
        if(main.arene != null){
            for(AreneRectangle rectangle : main.arene.getRectangles()){
                if(rectangle.getLoc1() != null && rectangle.getLoc2() != null){
                    ConfigurationSection sec = main.getConfig().createSection(rectangle.getUuid().toString());
                    ConfigurationSection loc1 = sec.createSection("Location 1");
                    ConfigurationSection loc2 = sec.createSection("Location 2");

                    loc1.set("World", rectangle.getLoc1().getWorld().getName());
                    loc1.set("X", rectangle.getLoc1().getX());
                    loc1.set("Y", rectangle.getLoc1().getY());
                    loc1.set("Z", rectangle.getLoc1().getZ());

                    loc2.set("World", rectangle.getLoc2().getWorld().getName());
                    loc2.set("X", rectangle.getLoc2().getX());
                    loc2.set("Y", rectangle.getLoc2().getY());
                    loc2.set("Z", rectangle.getLoc2().getZ());

                }
            }
        }
        main.saveConfig();
    }

    public void deserializeArene(){
        main.arene = new SdchachazeArene();
        main.pvp = main.getConfig().getBoolean("PVP");
        if(main.getConfig().getKeys(false).size() > 1){
            for(String uuid : main.getConfig().getKeys(false)){
                if(!uuid.equals("PVP")) {
                    ConfigurationSection loc1 = main.getConfig().getConfigurationSection(uuid).getConfigurationSection("Location 1");
                    ConfigurationSection loc2 = main.getConfig().getConfigurationSection(uuid).getConfigurationSection("Location 2");

                    World world1 = Bukkit.getWorld(loc1.getString("World"));
                    Double x1 = loc1.getDouble("X");
                    Double y1 = loc1.getDouble("Y");
                    Double z1 = loc1.getDouble("Z");
                    Location location1 = new Location(world1, x1, y1, z1);

                    World world2 = Bukkit.getWorld(loc2.getString("World"));
                    Double x2 = loc2.getDouble("X");
                    Double y2 = loc2.getDouble("Y");
                    Double z2 = loc2.getDouble("Z");
                    Location location2 = new Location(world2, x2, y2, z2);

                    AreneRectangle areneRectangle = new AreneRectangle(UUID.fromString(uuid), location1, location2);
                    addRectangle(areneRectangle);
                }
            }
        }
    }

    public void addRectangle(AreneRectangle rectangle){
        SdchachazeArene arene = main.arene;
        arene.addRectangle(rectangle);
        main.arene = arene;
    }

    public void removeRectangle(int index){
        SdchachazeArene arene = main.arene;
        arene.removeRectangle(index);
        main.arene = arene;
    }

    public boolean isInArene(Location loc){
        for(AreneRectangle rectangle : main.arene.getRectangles()){
            if(rectangle.getLoc1().getWorld().getName().equals(loc.getWorld().getName())) {
                if (Math.min(rectangle.getLoc1().getX(), rectangle.getLoc2().getX()) <= loc.getX() && loc.getX() <= Math.max(rectangle.getLoc1().getX(), rectangle.getLoc2().getX())) {
                    if (Math.min(rectangle.getLoc1().getY(), rectangle.getLoc2().getY()) <= loc.getY() && loc.getY() <= Math.max(rectangle.getLoc1().getY(), rectangle.getLoc2().getY())) {
                        if (Math.min(rectangle.getLoc1().getZ(), rectangle.getLoc2().getZ()) <= loc.getZ() && loc.getZ() <= Math.max(rectangle.getLoc1().getZ(), rectangle.getLoc2().getZ())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
