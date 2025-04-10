package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.CaseInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext.DisplayContextCase;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectPropertiesInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectModelTypeProperties;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemModelDefinitionTypeSelect extends BaseItemModelDefinition{
    @JsonIgnore
    private final ArrayList<CaseInterface> displayContextCases = new ArrayList<>();
    // ItemModelDefinitionTypeSelect("something", "something", Property.BLOCK_STATE, new BlockState().set(3))
    // itemDef.addCase(new Case(type = list, When = enum, type = BaseItemModelDefinition))

    public <T> ItemModelDefinitionTypeSelect(String namespacedWritePathOfModelDef, @NotNull SelectModelTypeProperties<T> type , @NotNull SelectPropertiesInterface<T> value) {
        super(namespacedWritePathOfModelDef);
        baseProperties.put("type", "minecraft:select");
        baseProperties.put("property", value.getType());
        baseProperties.putAll(value.getHashMap());


    }



    public ItemModelDefinitionTypeSelect addCase(CaseInterface c){
        displayContextCases.add(c);
        baseProperties.put("cases", displayContextCases);
        return this;
    }
}


