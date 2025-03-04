package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.TextureSize;

import java.util.ArrayList;

public class UnicodeProvider extends NonWritable{

    private String type;
    private String file;
    private int ascent;
    private int height;
    private ArrayList<String> chars;

    @JsonIgnore
    public UnicodeProvider(String type, ArrayList<String> unicodeChars, int ascent, int height, String file){
        this.type = type;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        chars = unicodeChars;

    }
}
