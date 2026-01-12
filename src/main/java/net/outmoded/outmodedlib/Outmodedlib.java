package net.outmoded.outmodedlib;

import net.outmoded.outmodedlib.GUIcontainers.ContainerListener;
import net.outmoded.outmodedlib.commands.OutmodedCommand;
import net.outmoded.outmodedlib.commands.OutmodedCommandTabComplete;
import net.outmoded.outmodedlib.config.HostingConfig;
import net.outmoded.outmodedlib.items.listener.*;
import net.outmoded.outmodedlib.packer.InternalPackContent;
import net.outmoded.outmodedlib.packer.ResourcePackServer.ResourcePackManager;
import net.outmoded.outmodedlib.packer.ResourcePackServer.ResourcePackManagerListener;
import net.outmoded.outmodedlib.particles.ParticleManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Outmodedlib extends JavaPlugin {

    @Override
    public void onEnable() {

        // should only be one minor versions I.e. supported version 1.21.11
        String version = Bukkit.getMinecraftVersion();
        Bukkit.getPluginManager().getPlugin("outmodedlib").getPluginMeta().getVersion();
        if (!version.equals("1.21.11")){
            getServer().getConsoleSender().sendMessage(version);
            Outmodedlib.getInstance().getLogger().warning("you are running a unsupported version: supported version = 1.21.11");
        }

        int pluginId = 25745;
        Metrics metrics = new Metrics(this, pluginId);

        HostingConfig.load();

        getCommand("outmodedlib").setExecutor(new OutmodedCommand());
        getCommand("outmodedlib").setTabCompleter(new OutmodedCommandTabComplete());

        if (HostingConfig.isEnabled())
            ResourcePackManager.getInstance().startResourcePackServer("0.0.0.0", HostingConfig.getPort()); // this should be 0.0.0.0

        Bukkit.getPluginManager().registerEvents(new ResourcePackManagerListener(), this); // resource pack server listener

        Bukkit.getPluginManager().registerEvents(new ContainerListener(), this); // custom gui listener
        Bukkit.getPluginManager().registerEvents(new VanillaCraftingPrevention(), this); // crafting listener
        Bukkit.getPluginManager().registerEvents(new VanillaAnvilPrevention(), this); // anvil listener
        Bukkit.getPluginManager().registerEvents(new VanillaTradePrevention(), this); // trade listener
        Bukkit.getPluginManager().registerEvents(new VanillaSmithingPrevention(), this); // smithing listener
        Bukkit.getPluginManager().registerEvents(new VanillaCampfirePrevention(), this); // campfire listener





        InternalPackContent.registerInternalCustomContent();
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
