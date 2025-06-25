package net.outmoded.outmodedlib.particles;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface BaseParticle {

    void tick();
    void spawn();
    void destroy();
    void setDestroy(boolean bool);
    default boolean getDestroy() {
        return false;
    }
    default @NotNull Integer getLivedTicks(){
        return null;
    };
    void setLivedTicks(int livedTicks);
    default @NotNull UUID getUuid(){
        return null;
    }



}
