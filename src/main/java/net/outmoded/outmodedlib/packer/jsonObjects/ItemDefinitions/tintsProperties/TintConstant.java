package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties;

import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

public class TintConstant extends NonWritable implements TintPropertiesInterface<TintConstant> {

    private final String type = "minecraft:constant";
    private Object value;

    @Override
    public String getType() {
        return type;
    }


    public static class Builder {
        private TintConstant tintConstant;

        public Builder() {
            tintConstant = new TintConstant();
        }

        // Method to add a float value
        public Builder setValue(int value) {
            tintConstant.value = value;
            return this;  // Return the builder to allow chaining
        }
        public Builder setValue(int r, int g, int b) {
            tintConstant.value = new int[]{r, g, b};
            return this;  // Return the builder to allow chaining
        }

        // Build method to return the final object
        public TintConstant build() {
            return tintConstant;
        }
    }

    // Static method to get a new builder instance
    public static Builder tintConstant() {
        return new Builder();
    }




}
