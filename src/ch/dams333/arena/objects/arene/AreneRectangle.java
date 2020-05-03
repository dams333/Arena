package ch.dams333.arena.objects.arene;

import org.bukkit.Location;

import java.util.UUID;

public class AreneRectangle {

    private UUID uuid;
    private Location loc1;
    private Location loc2;

    public AreneRectangle(Location loc1) {
        this.uuid = UUID.randomUUID();
        this.loc1 = loc1;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Location getLoc1() {

        return loc1;
    }

    public void setLoc1(Location loc1) {
        this.loc1 = loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public void setLoc2(Location loc2) {
        this.loc2 = loc2;
    }

    public AreneRectangle(UUID uuid, Location loc1, Location loc2) {

        this.uuid = uuid;

        this.loc1 = loc1;
        this.loc2 = loc2;
    }
}
