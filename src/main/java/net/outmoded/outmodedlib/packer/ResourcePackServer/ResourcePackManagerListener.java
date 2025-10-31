package net.outmoded.outmodedlib.packer.ResourcePackServer;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.config.HostingConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import static net.outmoded.outmodedlib.packer.ResourcePackServer.ResourcePackManager.resourcePacksPaths;

public class ResourcePackManagerListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        ResourcePackManager.getInstance().applyAllResourcePacksToPlayer(event.getPlayer());

    }



}
