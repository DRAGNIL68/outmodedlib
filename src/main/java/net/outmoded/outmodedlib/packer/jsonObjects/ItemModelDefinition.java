package net.outmoded.outmodedlib.packer.jsonObjects;

import java.util.HashMap;
import java.util.Map;

public class ItemModelDefinition extends Writable {
    private final Map<String, Object> model = new HashMap<>();

    public ItemModelDefinition(String namespace, String pathToModel, String writePath){
        super(writePath);
        model.put("type", "minecraft:model");
        model.put("model", namespace + ":" + pathToModel); // "test_namespace:swords/sword.json full path ---> test_namespace/items/swords/sword.json"

    }
}
