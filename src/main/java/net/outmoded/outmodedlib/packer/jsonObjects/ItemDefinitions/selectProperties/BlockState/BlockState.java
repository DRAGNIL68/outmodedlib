package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.BlockState;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectPropertiesInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.HashMap;
import java.util.Map;

public class BlockState extends NonWritable implements SelectPropertiesInterface<BlockState> {
    @JsonIgnore
    private final Map<String, Object> properties = new HashMap<>();

    public void setBlockState(BlockProperties state){
        properties.put("block_state_property", state.getValue());

    }

    @Override
    public Map<String, Object> getHashMap() {
        return properties;
    }

    @Override
    public String getType() {
        return "minecraft:block_sate";
    }

    public static class Builder {
        private BlockState blockState;

        public Builder() {
            blockState = new BlockState();
        }

        // Method to add a float value
        public Builder addFloat(float value) {

            return this;  // Return the builder to allow chaining
        }

        // Method to add a flag (boolean)
        public Builder addFlag(boolean value) {

            return this;  // Return the builder to allow chaining
        }

        // Build method to return the final object
        public BlockState build() {
            return blockState;
        }
    }

    // Static method to get a new builder instance
    public static Builder blockState() {
        return new Builder();
    }

    public enum BlockProperties{
        FACE("face");

        private final String value;

        BlockProperties(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
