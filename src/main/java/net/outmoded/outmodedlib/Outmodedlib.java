package net.outmoded.outmodedlib;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.GUIcontainers.ContainerListener;
import net.outmoded.outmodedlib.packer.InternalContent;
import net.outmoded.outmodedlib.particles.ParticleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;


public final class Outmodedlib extends JavaPlugin {



    @Override
    public void onEnable() {
        // version check
        // should only be one minor versions I.e. supported version 1.21.4d
        String version = Bukkit.getMinecraftVersion();
        //if (!version.equals("1.21.4-R0.1-SNAPSHOT")){
            //getServer().getPluginManager().disablePlugin(this);

        //}
        getServer().getConsoleSender().sendMessage(version);
        NBT.preloadApi(); // load nbt-api
        Bukkit.getPluginManager().registerEvents(new ContainerListener(), this); // custom gui listener
        PackGenTest.runPack();
        InternalContent.registerInternalCustomContent();


        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
                ParticleManager.tickEmitters();
                ParticleManager.tickParticles();
            }
        }, 20, 1L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Outmodedlib getInstance() {
        return getPlugin(Outmodedlib.class);
    }

}
