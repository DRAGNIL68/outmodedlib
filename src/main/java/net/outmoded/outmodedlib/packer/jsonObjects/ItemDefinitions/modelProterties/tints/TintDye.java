package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.modelProterties.tints;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintDye extends NonWritable implements TintPropertiesInterface<TintDye> {

    private final String type = "minecraft:dye";

    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintDye tintDye;

        public Builder() {
            tintDye = new TintDye();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintDye.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintDye.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }


        // Build method to return the final object
        public TintDye build() {
            return tintDye;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintDye() {
        return new Builder();
    }




}
