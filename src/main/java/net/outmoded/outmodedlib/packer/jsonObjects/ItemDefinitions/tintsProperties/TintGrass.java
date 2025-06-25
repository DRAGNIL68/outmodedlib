package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintGrass extends NonWritable implements TintPropertiesInterface<TintGrass> {

    private final String type = "minecraft:grass";
    

    private float temperature;
    private float downfall;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintGrass tintGrass;

        public Builder() {
            tintGrass = new TintGrass();
        }

        // Method to add a float value
        public Builder setTemperature(float value) {
            tintGrass.temperature = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setDownfall(float value) {
            tintGrass.downfall = value;
            return this;  // Return the builder to allow chaining
        }
        

        // Build method to return the final object
        public TintGrass build() {
            return tintGrass;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintGrass() {
        return new Builder();
    }




}
