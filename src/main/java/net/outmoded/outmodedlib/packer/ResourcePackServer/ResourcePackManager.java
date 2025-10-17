package net.outmoded.outmodedlib.packer.ResourcePackServer;





import org.bukkit.entity.Player;
import org.eclipse.jetty.ee10.servlet.DefaultServlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcePackManager {
    protected final static Map<String, ResourcePackInfo> resourcePacksPaths = new HashMap<>();
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
        startResourcePackServer("0.0.0.0", 8080, 30000);

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
            ResourcePackInfo resourcePackInfo = resourcePacksPaths.get(key);

            DefaultServlet defaultServlet = new DefaultServlet();
            ServletHolder holder = new ServletHolder("default", defaultServlet);
            holder.setInitParameter("resourceBase", resourcePackInfo.path.getParent().toString());
            holder.setInitParameter("dirAllowed", "false");
            context.addServlet(holder, "/"+resourcePackInfo.path.getFileName());

        }


        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerResourcePacks(@NotNull String resourcePackId, @NotNull Path resourcePackPath, @NotNull boolean autoLoad){
        if (resourcePacksPaths.containsKey(resourcePackPath.getFileName().toString()))
            return;


        if (!resourcePackPath.toFile().exists())
            return;


        ResourcePackInfo resourcePackInfo = new ResourcePackInfo(resourcePackId, resourcePackPath, autoLoad);
        resourcePacksPaths.put(String.valueOf(resourcePackPath.getFileName()), resourcePackInfo);

        reloadResourcePackServer();
    }

    public void deregisterResourcePacks(String resourcePackId){
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

    public void addResourcePackToPlayer(Player player, String resourcePackId){
        if (!resourcePacksPaths.containsKey(resourcePackId))
            return;

        byte[] seedBytes = resourcePackId.getBytes(StandardCharsets.UTF_8);
        UUID generatedUuid = UUID.nameUUIDFromBytes(seedBytes);

        player.addResourcePack(generatedUuid, ip+":"+port+"/"+resourcePacksPaths.get(resourcePackId).path.getFileName(), null, null, true);
    }


    public void reloadResourcePackServer(){
        try {
            if (server.isRunning())
                server.stop();

            server.start();
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

    protected static class ResourcePackInfo{
        public final Path path;
        public final String resourcePackId;
        public final boolean autoLoad;

        ResourcePackInfo(String resourcePackId, Path path, boolean autoLoad){
            this.path = path;
            this.resourcePackId = resourcePackId;
            this.autoLoad = autoLoad;
        }


    }




}

