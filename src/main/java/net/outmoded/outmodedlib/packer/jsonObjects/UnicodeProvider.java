package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UnicodeProvider extends NonWritable{

    private String type;
    private String file;
    private int ascent;
    private int height;
    private String[] chars ;

    @JsonIgnore
    public UnicodeProvider(String type, String unicodeChar, int ascent, int height, String file){
        this.type = type;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        chars = new String[]{unicodeChar};
    }
}
