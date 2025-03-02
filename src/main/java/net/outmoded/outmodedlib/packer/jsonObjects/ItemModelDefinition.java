package net.outmoded.outmodedlib.packer.jsonObjects;

import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class ItemModelDefinition extends Writable {
    private final Map<String, Object> model = new HashMap<>();

    public ItemModelDefinition(String namespacedPathToModel, String namespacedWritePath){
        // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        String fullPath;

        if (namespacedPathToModel.contains(":")){
            fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/items/"+splitNamespaceId(namespacedWritePath)[1]+".json"; // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        }
        else{

            fullPath = "items/"+splitNamespaceId(namespacedWritePath)[1]+".json";

        }


        setFilePath(fullPath);

        model.put("type", "minecraft:model");
        model.put("model", namespacedPathToModel); // "test_namespace:swords/sword.json full path ---> test_namespace/items/swords/sword.json"

    }
}
