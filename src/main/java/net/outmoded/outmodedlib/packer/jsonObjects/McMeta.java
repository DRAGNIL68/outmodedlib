package net.outmoded.outmodedlib.packer.jsonObjects;

import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext.DisplayContext;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectModelTypeProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeSelect;

import java.util.HashMap;
import java.util.Map;

public class McMeta extends Writable {

    private final Map<String, Object> pack = new HashMap<>();
    public McMeta(String description, int version){
        setFilePath("pack.mcmeta");
        pack.put("pack_format", version);
        pack.put("description", description);

        new ItemModelDefinitionTypeSelect("e", SelectModelTypeProperties.DISPLAY_CONTEXT, new DisplayContext());
    }

}
