package net.outmoded.outmodedlib.packer.jsonObjects.unicode;

import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.HashMap;

public class Filter extends NonWritable {

    private HashMap<String, Object> filter = new HashMap<>();

    public Filter(){
    }

    public enum FilterType{
        UNICODE,
        JP


    }

    public void addCase(FilterType filterType, boolean value){
        if (filterType == FilterType.UNICODE){
            filter.put("uniform", value);

        }
        else{
            filter.put("jp", value);

        }


    }

}
