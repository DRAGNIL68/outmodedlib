package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext;

import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.BaseItemModelDefinition;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.CaseInterface;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.ArrayList;
import java.util.Map;

public class DisplayContextCase extends NonWritable implements CaseInterface {

    Object when;
    Map<String, Object> model;

    // itemDef.addCase(new Case(type = list, When = enum, type = BaseItemModelDefinition))
    public DisplayContextCase(When when, BaseItemModelDefinition modelDefinition){
        this.when = when.getValue();
        this.model = modelDefinition.getHashmap();

    }

    public DisplayContextCase(When[] when, BaseItemModelDefinition modelDefinition){
        ArrayList<String> whens = new ArrayList<>();
        for (When value : when) {
            whens.add(value.getValue());
        }
        this.when = whens;
        this.model = modelDefinition.getHashmap();

    }

    public enum When{
        NONE("none"),
        FIRST_PERSON_RIGHTHAND("firstperson_righthand"),
        FIRST_PERSON_LEFTHAND("firstperson_lefthand"),
        THIRD_PERSON_RIGHTHAND("thirdperson_righthand"),
        THIRD_PERSON_LEFTHAND("thirdperson_lefthand"),
        GUI("gui"),
        HEAD("head"),
        GROUND("ground"),
        FIXED("fixed");




        private final String value;

        When(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
