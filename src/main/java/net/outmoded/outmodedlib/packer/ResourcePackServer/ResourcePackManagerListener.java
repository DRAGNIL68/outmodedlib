package net.outmoded.outmodedlib.packer.ResourcePackServer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ResourcePackManagerListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        ResourcePackManager.getInstance().applyAllResourcePacksToPlayer(event.getPlayer());

    }



}
