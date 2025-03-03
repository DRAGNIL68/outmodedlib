package net.outmoded.outmodedlib.particles;

import net.outmoded.outmodedlib.items.CustomItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParticleManager {

    private final static Map<UUID, BaseParticle> particleRegistry = new HashMap<>();
    private final static Map<UUID, BaseEmitter> emitterRegistry = new HashMap<>();


    public static void registerParticle(BaseParticle particle){
        if (!particleRegistry.containsKey(particle.getUuid())){
            particleRegistry.put(particle.getUuid(), particle);

        }
    }

    public static void registerEmitter(BaseEmitter emitter){
        if (!emitterRegistry.containsKey(emitter.getUuid())){
            emitterRegistry.put(emitter.getUuid(), emitter);

        }
    }
    public static void tickParticles(){
        for (BaseParticle particle : particleRegistry.values()) {
            if (particle.getDestroy()){
                particle.destroy();
                emitterRegistry.remove(particle.getUuid());
                continue;
            }
            particle.tick();
        }
    }

    public static void tickEmitters(){
        for (BaseEmitter emitter : emitterRegistry.values()) {
            if (emitter.getDestroy()){
                emitter.destroy();
                emitterRegistry.remove(emitter.getUuid());
                continue;
            }
            emitter.tick();
        }
    }

}
