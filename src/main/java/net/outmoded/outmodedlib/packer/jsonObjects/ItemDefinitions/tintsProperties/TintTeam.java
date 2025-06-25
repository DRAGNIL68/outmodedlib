package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintTeam extends NonWritable implements TintPropertiesInterface<TintTeam> {

    private final String type = "minecraft:potion";
    
    @JsonProperty("default")
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintTeam tintTeam;

        public Builder() {
            tintTeam = new TintTeam();
        }

        // Method to add a float value
        public Builder setDefault(int value) {
            tintTeam.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDefault(int r, int g, int b) {
            tintTeam.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }
        

        // Build method to return the final object
        public TintTeam build() {
            return tintTeam;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintConstant() {
        return new Builder();
    }




}
