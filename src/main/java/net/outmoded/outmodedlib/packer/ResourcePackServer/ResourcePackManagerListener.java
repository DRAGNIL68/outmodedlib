package net.outmoded.outmodedlib.packer.ResourcePackServer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ResourcePackManagerListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        for (ResourcePackManager.ResourcePackInfo resourcePackInfo : ResourcePackManager.resourcePacksPaths.values()) {
            if (resourcePackInfo.autoLoad){
                ResourcePackManager.getInstance().addResourcePackToPlayer(event.getPlayer(), resourcePackInfo.resourcePackId);

            }
        }
    }




}
