package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints.ModelModelTypeTintProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints.TintPropertiesInterface;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class ItemModelDefinitionTypeEmpty extends BaseItemModelDefinition {
    @JsonIgnore


    public ItemModelDefinitionTypeEmpty(String namespacedWritePathOfModelDef){
        super(namespacedWritePathOfModelDef);
        // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        String fullPath;


        fullPath = "assets/"+splitNamespaceId(namespacedWritePathOfModelDef)[0]+"/items/"+splitNamespaceId(namespacedWritePathOfModelDef)[1]+".json"; // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json



        setFilePath(fullPath);

        baseProperties.put("type", "minecraft:empty");

    }

}
