package net.outmoded.outmodedlib;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.hook.PlaceholderAPIHook;
import net.outmoded.outmodedlib.outmodedlibGUIContainer.ContainerListener;
import net.outmoded.outmodedlib.outmodedlibGUIContainer.ContainerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Outmodedlib extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!NBT.preloadApi()){
            getLogger().info("Outmodedlib's Shaded NBT-API Has Broken Really Bad Things Will Happen.......");
        }

        ContainerManager containerManager = new ContainerManager();

        ContainerListener containerListener = new ContainerListener(containerManager);
        Bukkit.getPluginManager().registerEvents(containerListener, this);


        if (PlaceholderAPIHook.isPlaceholderAPIEnabled()){
            getLogger().info("Outmodedlib Loaded And PlaceholderApi Hooked");
        }
        else{
            getLogger().info("Outmodedlib Loaded And PlaceholderApi Is Not Installed Bad Things Will Happen.....");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Outmodedlib getInstance() {
        return getPlugin(Outmodedlib.class);
    }
}
