package net.outmoded.outmodedlib.hook;

import static org.bukkit.Bukkit.getServer;

public class PlaceholderAPIHook {
    public static boolean isPlaceholderAPIEnabled(){
        return getServer().getPluginManager().getPlugin("placeholderapi") != null;
    }
}
