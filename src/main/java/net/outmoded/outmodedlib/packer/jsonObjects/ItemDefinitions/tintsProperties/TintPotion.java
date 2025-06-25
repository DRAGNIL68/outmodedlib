package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintPotion extends NonWritable implements TintPropertiesInterface<TintPotion> {

    private final String type = "minecraft:potion";
    
    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintPotion tintPotion;

        public Builder() {
            tintPotion = new TintPotion();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintPotion.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintPotion.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }
        

        // Build method to return the final object
        public TintPotion build() {
            return tintPotion;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintPotion() {
        return new Builder();
    }




}
