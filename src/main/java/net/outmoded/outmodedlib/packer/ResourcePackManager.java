package net.outmoded.outmodedlib.packer;





import java.util.HashMap;
import java.util.Map;

public class ResourcePackManager {
    private final static Map<String, ResourcePack> loadedResourcePacks = new HashMap<>();
    private ResourcePackManager(){


    }

    public static void registerResourcePacks(ResourcePack resourcePack){
        if (!loadedResourcePacks.containsKey(resourcePack.getName())){
            loadedResourcePacks.put(resourcePack.getName(), resourcePack);
        };

    }

    public static void deregisterResourcePacks(String resourcePackName){
        loadedResourcePacks.remove(resourcePackName);
    }

    public static void clearResourcePackFromPlayer(String resourcePackName){
        // put code here
    }

    public static void sendResourcePackTosPlayer(String resourcePackName){
        // put code here
    }

    public static void CompileResourcePacks(String resourcePackName){
        // put code here
    }

}

