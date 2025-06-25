package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintFirework extends NonWritable implements TintPropertiesInterface<TintFirework> {

    private final String type = "minecraft:firework";
    
    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintFirework tintFirework;

        public Builder() {
            tintFirework = new TintFirework();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintFirework.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintFirework.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }
        

        // Build method to return the final object
        public TintFirework build() {
            return tintFirework;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintFirework() {
        return new Builder();
    }




}
