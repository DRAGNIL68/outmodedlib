package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintMapColor extends NonWritable implements TintPropertiesInterface<TintMapColor> {

    private final String type = "minecraft:map_color";
    
    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintMapColor tintMapColor;

        public Builder() {
            tintMapColor = new TintMapColor();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintMapColor.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintMapColor.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }
        

        // Build method to return the final object
        public TintMapColor build() {
            return tintMapColor;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintMapColor() {
        return new Builder();
    }




}
