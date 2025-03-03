package net.outmoded.outmodedlib.particles;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface BaseEmitter {


    default @NotNull UUID getUuid(){
        return null;
    }
    default @NotNull Integer getLivedTicks(){
        return null;
    };
    void setLivedTicks();
    default @NotNull Location getLoc(){
        return null;
    };
    void setLoc(Location location);
    void setDestroy(boolean bool);
    default boolean getDestroy() {
        return false;
    }
    void destroy();
    void tick();



}
