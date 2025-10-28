package net.outmoded.outmodedlib.packer.ResourcePackServer;





import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.sun.net.httpserver.HttpServer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.config.HostingConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class ResourcePackManager {
    protected final static Map<String, ResourcePackData> resourcePacksPaths = new HashMap<>();
    private static ResourcePackManager resourcePackManagerInstance;
    private HttpServer server;
    private String ip;
    private int port;

    private ResourcePackManager(){

    }

    public static ResourcePackManager getInstance() {
        if (resourcePackManagerInstance == null) {
            resourcePackManagerInstance = new ResourcePackManager();
        }
        return resourcePackManagerInstance;
    }

    public void startResourcePackServer(){
        startResourcePackServer("127.0.0.1", 49155);

    }

    public void applyAllResourcePacksToPlayer(Player player){

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


        final ResourcePackRequest request = ResourcePackRequest.resourcePackRequest()
                .packs(packs)
                .prompt(HostingConfig.getPrompt())
                .required(HostingConfig.isForce())
                .build();


        Audience audience = Audience.audience(player);
        audience.sendResourcePacks(request);


    }



    // fuck html fuck web servers and fuck papermc's logger. I wish them a happy time in HELL
    public boolean startResourcePackServer(String ip, int port) {

        this.ip = ip;
        this.port = port;





        try {
            try (ServerSocket socket = new ServerSocket(port)) {

            }catch (Exception e) {
                Outmodedlib.getInstance().getLogger().warning("port "+port+" already in use!");
                return false;
            }



            if (this.server != null)
                server.stop(0);

            this.server = HttpServer.create(new InetSocketAddress(ip,port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // runs every time a user tries to download a pack
        server.createContext("/", exchange -> {
            URI uri = exchange.getRequestURI();
            String path = uri.getPath();

            path = path.replace("/", "");

            if (!path.endsWith(".zip")) {
                exchange.sendResponseHeaders(404, -1);
            }

            String newFilename = path.substring(0, path.lastIndexOf(".zip"));

            if (resourcePacksPaths.containsKey(newFilename)) {
                ResourcePackData resourcePackData = resourcePacksPaths.get(newFilename);


                if (resourcePackData.path == null)
                    return;

                exchange.getResponseHeaders().add("Content-Type", "application/zip");
                exchange.sendResponseHeaders(200, Files.size(resourcePackData.path));

                try (OutputStream os = exchange.getResponseBody()) {
                    Files.copy(resourcePackData.path, os);
                }

            }

            exchange.sendResponseHeaders(404, -1);


        });

        // Start the server
        server.start();
        return true;
    }




    public void registerExternalResourcePack(@NotNull String resourcePackId, @NotNull String externalResourcePackUrl, @NotNull String md5Hash ,@NotNull boolean autoLoad) {
        if (resourcePacksPaths.containsKey(resourcePackId))
            return;

        ResourcePackData ResourcePackData = new ResourcePackData(resourcePackId, null, autoLoad, externalResourcePackUrl, md5Hash);
        resourcePacksPaths.put(resourcePackId, ResourcePackData);
    }

    public void registerResourcePack(@NotNull String resourcePackId, @NotNull Path resourcePackPath, @NotNull boolean autoLoad) {
        if (resourcePacksPaths.containsKey(resourcePackId))
            return;


        if (!resourcePackPath.toFile().exists())
            return;

        ResourcePackData resourcePackData = new ResourcePackData(resourcePackId, resourcePackPath, autoLoad, null, null);
        resourcePacksPaths.put(resourcePackId, resourcePackData);

    }

    public void deregisterResourcePack(String resourcePackId){
        if (resourcePacksPaths.containsKey(resourcePackId)){
            resourcePacksPaths.remove(resourcePackId);

        }


    }

    public void removeResourcePackFromPlayer(Player player ,String resourcePackId){
        if (!resourcePacksPaths.containsKey(resourcePackId))
            return;

        byte[] seedBytes = resourcePackId.getBytes(StandardCharsets.UTF_8);
        UUID generatedUuid = UUID.nameUUIDFromBytes(seedBytes);

        player.removeResourcePack(generatedUuid);
    }

    public boolean hasResourcePack(String resourcePackId){
        if (resourcePacksPaths.containsKey(resourcePackId))
            return true;

        return false;
    }


    public void addResourcePackToPlayer(Audience target, String resourcePackId){
        if (!resourcePacksPaths.containsKey(resourcePackId)){
            return;
        }

        Player player;
        byte[] seedBytes = resourcePackId.getBytes(StandardCharsets.UTF_8);
        UUID generatedUuid = UUID.nameUUIDFromBytes(seedBytes);




        final ResourcePackInfo PACK_INFO = ResourcePackInfo.resourcePackInfo()
                .uri(URI.create(resourcePacksPaths.get(resourcePackId).getUrl()))
                .hash(resourcePacksPaths.get(resourcePackId).getMd5Hash())
                .id(generatedUuid)
                .build();


        final ResourcePackRequest request = ResourcePackRequest.resourcePackRequest()
                .packs(PACK_INFO)
                .prompt(Component.text("Please download the resource pack!"))
                .required(true)
                .build();

        // Send the resource pack request to the target audience
        target.sendResourcePacks(request);

    }



    private boolean stopResourcePackServer(){
        try {
            if (server != null){
                server.stop(0);
                return true;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    protected class ResourcePackData{
        private final Path path;
        private final String resourcePackId;
        private final boolean autoLoad;

        private String staticUrl; // used for external packs
        private String staticMd5Hash; // used for external packs

        ResourcePackData(String resourcePackId, Path path, boolean autoLoad, String staticUrl, String staticMd5Hash){
            this.path = path;
            this.resourcePackId = resourcePackId;
            this.autoLoad = autoLoad;
            this.staticUrl = staticUrl;
            this.staticMd5Hash = staticMd5Hash;
        }

        public String getUrl(){
            if (staticUrl == null){
                return "http://"+ResourcePackManager.getInstance().ip+":"+ResourcePackManager.getInstance().port+"/"+resourcePacksPaths.get(resourcePackId).path.getFileName();

            }
            return staticUrl;
        }

        public String getMd5Hash(){
            if (staticMd5Hash == null){

                String checksum;

                try {
                    ByteSource byteSource = com.google.common.io.Files.asByteSource(path.toFile());
                    HashCode hc = byteSource.hash(Hashing.md5());
                    checksum = hc.toString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return checksum;

            }
            return staticMd5Hash;
        }

        public String getResourcePackId(){
            return resourcePackId;
        }

        public boolean getAutoLoad(){
            return autoLoad;
        }

    }




}

