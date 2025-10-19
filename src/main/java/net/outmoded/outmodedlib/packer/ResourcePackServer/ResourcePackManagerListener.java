package net.outmoded.outmodedlib.packer.ResourcePackServer;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.outmoded.outmodedlib.Outmodedlib;
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

        ArrayList<ResourcePackInfo> packs = new ArrayList<>();

        for (String key: resourcePacksPaths.keySet()) {
            ResourcePackManager.ResourcePackData resourcePackData = resourcePacksPaths.get(key);
            if (resourcePackData.getAutoLoad()){

                byte[] seedBytes = key.getBytes(StandardCharsets.UTF_8);
                UUID generatedUuid = UUID.nameUUIDFromBytes(seedBytes);

                final ResourcePackInfo PACK_INFO = ResourcePackInfo.resourcePackInfo()
                        .uri(URI.create(resourcePackData.getUrl()))
                        .hash(resourcePacksPaths.get(key).getMd5Hash())
                        .id(generatedUuid)
                        .build();

                packs.add(PACK_INFO);

            }
        }

        Audience audience = Audience.audience(event.getPlayer());
        final ResourcePackRequest request = ResourcePackRequest.resourcePackRequest()
                .packs(packs)
                .prompt(Component.text("Please download the resource pack!"))
                .required(true)
                .build();

        audience.sendResourcePacks(request);


    }



}
