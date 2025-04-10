package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectPropertiesInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.HashMap;
import java.util.Map;

public class DisplayContext extends NonWritable implements SelectPropertiesInterface<DisplayContext> {
    @JsonIgnore
    private final Map<String, Object> properties = new HashMap<>();
    
    @Override
    public Map<String, Object> getHashMap() {
        return properties;
    }

    @Override
    public String getType() {
        return "minecraft:display_context";
    }


}
