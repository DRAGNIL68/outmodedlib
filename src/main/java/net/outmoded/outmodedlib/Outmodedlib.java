package net.outmoded.outmodedlib;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.GUIcontainers.ContainerListener;
import net.outmoded.outmodedlib.items.listener.*;
import net.outmoded.outmodedlib.packer.InternalContent;
import net.outmoded.outmodedlib.particles.ParticleManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;


public final class Outmodedlib extends JavaPlugin {

    @Override
    public void onEnable() {
        // version check
        // should only be one minor versions I.e. supported version 1.21.4d
        String version = Bukkit.getMinecraftVersion();
        Bukkit.getPluginManager().getPlugin("outmodedlib").getPluginMeta().getVersion();
        if (!version.equals("1.21.4")){
            getServer().getConsoleSender().sendMessage(version);
            Outmodedlib.getInstance().getLogger().warning("you are running a unsupported version: supported version = 1.21.4");
        }

        int pluginId = 25745;
        Metrics metrics = new Metrics(this, pluginId);


        NBT.preloadApi(); // load nbt-api

        Bukkit.getPluginManager().registerEvents(new ContainerListener(), this); // custom gui listener
        Bukkit.getPluginManager().registerEvents(new VanillaCraftingPrevention(), this); // crafting listener
        Bukkit.getPluginManager().registerEvents(new VanillaAnvilPrevention(), this); // anvil listener
        Bukkit.getPluginManager().registerEvents(new VanillaTradePrevention(), this); // trade listener
        Bukkit.getPluginManager().registerEvents(new VanillaSmithingPrevention(), this); // smithing listener
        Bukkit.getPluginManager().registerEvents(new VanillaCampfirePrevention(), this); // campfire listener



        InternalContent.registerInternalCustomContent();
        //PackGenTest.runPack();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
                ParticleManager.getInstance().tickEmitters();
                ParticleManager.getInstance().tickParticles();
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
