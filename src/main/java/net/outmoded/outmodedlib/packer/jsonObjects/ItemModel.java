package net.outmoded.outmodedlib.packer.jsonObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemModel extends Writable {
    private final Map<String, Object> model = new HashMap<>();

    public ItemModel(String modelNamespaceId){
        super(modelNamespaceId.substring(modelNamespaceId.lastIndexOf('/') + 1) + ".json");
        model.put("type", "minecraft:model");
        model.put("model", modelNamespaceId);

    }
}
