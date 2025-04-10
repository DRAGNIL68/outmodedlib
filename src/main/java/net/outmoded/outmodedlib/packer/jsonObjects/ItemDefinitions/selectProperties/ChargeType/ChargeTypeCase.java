package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.ChargeType;

import net.outmoded.outmodedlib.packer.PackerUtils;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.BaseItemModelDefinition;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.CaseInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.ArrayList;
import java.util.Map;

public class ChargeTypeCase extends NonWritable implements CaseInterface {

    Object when;
    Map<String, Object> model;

    // itemDef.addCase(new Case(type = list, When = enum, type = BaseItemModelDefinition))
    public ChargeTypeCase(When when, BaseItemModelDefinition modelDefinition){
        this.when = when.getValue();
        this.model = modelDefinition.getHashmap();
    }

    public ChargeTypeCase(When[] when, BaseItemModelDefinition modelDefinition){
        ArrayList<String> whens = new ArrayList<>();
        for (When value : when) {
            whens.add(value.getValue());
        }
        this.when = whens;
        this.model = modelDefinition.getHashmap();

    }

    public enum When{
        NONE("none"),
        ARROW("arrow"),
        ROCKET("rocket");




        private final String value;

        When(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
