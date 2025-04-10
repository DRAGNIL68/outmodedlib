package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints.ModelModelTypeTintProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints.TintPropertiesInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectModelTypeProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectPropertiesInterface;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class ItemModelDefinitionTypeModel extends BaseItemModelDefinition {
    @JsonIgnore
    ArrayList<TintPropertiesInterface> tints = new ArrayList<>();


    public ItemModelDefinitionTypeModel(String namespacedPathToModel, String namespacedWritePathOfModelDef){
        super(namespacedWritePathOfModelDef);
        // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        String fullPath;


        fullPath = "assets/"+splitNamespaceId(namespacedWritePathOfModelDef)[0]+"/items/"+splitNamespaceId(namespacedWritePathOfModelDef)[1]+".json"; // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json



        setFilePath(fullPath);

        baseProperties.put("type", "minecraft:model");
        baseProperties.put("model", namespacedPathToModel); // "test_namespace:swords/sword.json full path ---> test_namespace/items/swords/sword.json"

    }

    public <T> ItemModelDefinitionTypeModel addTint(@NotNull ModelModelTypeTintProperties<T> type , @NotNull TintPropertiesInterface<T> value){
        tints.add(value);
        baseProperties.put("tints", tints);
        return this;

    }
}
