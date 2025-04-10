package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintCustomModelData extends NonWritable implements TintPropertiesInterface<TintCustomModelData> {

    private final String type = "minecraft:custom_model_data";

    private int index;

    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintCustomModelData tintCustomModelData;

        public Builder() {
            tintCustomModelData = new TintCustomModelData();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintCustomModelData.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintCustomModelData.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }

        public Builder setIndex(int index) {
            tintCustomModelData.index = index;
            return this;  // Return the builder to allow chaining
        }

        // Build method to return the final object
        public TintCustomModelData build() {
            return tintCustomModelData;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintCustomModelData() {
        return new Builder();
    }




}
