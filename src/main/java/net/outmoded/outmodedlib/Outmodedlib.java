package net.outmoded.outmodedlib;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.GUIcontainers.ContainerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;


public final class Outmodedlib extends JavaPlugin {



    @Override
    public void onEnable() {
        if (!NBT.preloadApi()){
            getLogger().info("Outmodedlib's Shaded NBT-API Has Broken Really Bad Things Will Happen.......");
        }
        PackGenTest.runPack();
        Bukkit.getPluginManager().registerEvents(new ContainerListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Outmodedlib getInstance() {
        return getPlugin(Outmodedlib.class);
    }

}
