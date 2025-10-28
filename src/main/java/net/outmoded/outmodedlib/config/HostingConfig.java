package net.outmoded.outmodedlib.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.outmoded.outmodedlib.Outmodedlib;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class HostingConfig {

    private static boolean enabled = true;
    private static boolean force = true;
    private static int port = 8090;
    private static String ip = "127.0.0.1";
    private static Component prompt = MiniMessage.miniMessage().deserialize("<green> Please Download The Resource Pack!");

    private static YamlConfiguration configYml;
    private static File configFile;

    public static void load(){

        configFile = new File(Outmodedlib.getInstance().getDataFolder(), "pack-hosting.yml");

        if (!configFile.exists())
            Outmodedlib.getInstance().saveResource("pack-hosting.yml", false);


        // /outmodedlib reload
        try{
            configYml = new YamlConfiguration();
            configYml.options().parseComments(true);
            configYml.load(configFile);

            if (configYml.getString("enabled") != null)
                enabled = Boolean.valueOf(configYml.getString("enabled"));

            if (configYml.getString("force-pack") != null)
                force = Boolean.valueOf(configYml.getString("force-pack"));

            if (configYml.getString("port") != null)
                port = Integer.valueOf(configYml.getString("port"));

            if (configYml.getString("server-ip") != null)
                ip = String.valueOf(configYml.getString("server-ip"));

            if (configYml.getString("prompt") != null)
                prompt = MiniMessage.miniMessage().deserialize(String.valueOf(configYml.getString("prompt")));

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean isForce() {
        return force;
    }

    public static int getPort() {
        return port;
    }

    public static String getIp() {
        return ip;
    }

    public static Component getPrompt() {
        return prompt;
    }
}
