package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.ChargeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectPropertiesInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.HashMap;
import java.util.Map;

public class ChargeType extends NonWritable implements SelectPropertiesInterface<ChargeType> {
    @JsonIgnore
    private final Map<String, Object> properties = new HashMap<>();

    public ChargeType(){


    }


    
    @Override
    public Map<String, Object> getHashMap() {
        return properties;
    }

    @Override
    public String getType() {
        return "minecraft:charge_type";
    }

}
