package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class UnicodeSpriteSheetProvider extends NonWritable{

    private String type;
    private String file;
    private int ascent;
    private int height;
    private String chars;

    @JsonIgnore
    public UnicodeSpriteSheetProvider(String type, String unicodeChars, int ascent, int height, String file){
        this.type = type;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        chars = unicodeChars;

    }
}
