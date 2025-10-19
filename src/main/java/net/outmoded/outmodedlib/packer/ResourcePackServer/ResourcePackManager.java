package net.outmoded.outmodedlib.packer.ResourcePackServer;





import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.outmoded.outmodedlib.Outmodedlib;
import org.apache.logging.log4j.Level;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.eclipse.aether.spi.log.Logger;
import org.eclipse.jetty.ee10.servlet.ResourceServlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.logging.JettyLevel;
import org.eclipse.jetty.logging.JettyLogger;
import org.eclipse.jetty.logging.JettyLoggerConfiguration;
import org.eclipse.jetty.logging.JettyLoggerFactory;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.logging.Level.INFO;

public class ResourcePackManager {
    protected final static Map<String, ResourcePackData> resourcePacksPaths = new HashMap<>();
    private static ResourcePackManager resourcePackManagerInstance;
    private Server server;
    private String ip;
    private int port;
    private long idleTimeout;

    private ResourcePackManager(){

    }

    public static ResourcePackManager getInstance() {
        if (resourcePackManagerInstance == null) {
            resourcePackManagerInstance = new ResourcePackManager();
        }
        return resourcePackManagerInstance;
    }

    public void startResourcePackServer(){
        startResourcePackServer("127.0.0.1", 49155, 30000);

    }



    // I don't understand this dammed library (Jetty)
    public void startResourcePackServer(String ip, int port, long idleTimeout) {

        if (server != null && server.isRunning()) {
            try {
                server.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        this.ip = ip;
        this.port = port;
        this.idleTimeout = idleTimeout;



        server = new Server();



        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(ip);
        connector.setIdleTimeout(idleTimeout);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);


        for (String key : resourcePacksPaths.keySet()){
            ResourcePackData ResourcePackData = resourcePacksPaths.get(key);

            ServletHolder servletHolder = context.addServlet(ResourceServlet.class, "/"+ResourcePackData.path.getFileName().toString());
            servletHolder.setInitParameter("baseResource", ResourcePackData.path.getParent().toAbsolutePath().toString());
            servletHolder.setInitParameter("dirAllowed", "false");
            servletHolder.setAsyncSupported(true);

        }


        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


        ResourcePackData ResourcePackData = new ResourcePackData(resourcePackId, resourcePackPath, autoLoad, null, null);
        resourcePacksPaths.put(resourcePackId, ResourcePackData);

        reloadResourcePackServer();
    }

    public void deregisterResourcePack(String resourcePackId){
        resourcePacksPaths.remove(resourcePackId);
        reloadResourcePackServer();
    }

    public void removeResourcePackFromPlayer(Player player ,String resourcePackId){
        if (!resourcePacksPaths.containsKey(resourcePackId))
            return;

        byte[] seedBytes = resourcePackId.getBytes(StandardCharsets.UTF_8);
        UUID generatedUuid = UUID.nameUUIDFromBytes(seedBytes);

        player.removeResourcePack(generatedUuid);
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




    public void reloadResourcePackServer(){
        try {
            if (server.isRunning())
                server.stop();

            startResourcePackServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void stopResourcePackServer(){
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

