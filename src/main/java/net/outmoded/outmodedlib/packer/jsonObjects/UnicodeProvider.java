package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.TextureSize;
import net.outmoded.outmodedlib.packer.UnicodeFileRegister;

import java.util.ArrayList;

public class UnicodeProvider extends NonWritable{

    @JsonIgnore
    public final String namespacedFontId;

    public final UnicodeFileRegister.UnicodeType type;
    public final String file;
    public final int ascent;
    public final int height;
    public final ArrayList<String> chars;

    @JsonIgnore
    public UnicodeProvider(String namespacedFontId, UnicodeFileRegister.UnicodeType type, ArrayList<String> unicodeChars, int ascent, int height, String file){
        this.namespacedFontId = namespacedFontId;
        this.type = type;
        this.file = file;
        this.ascent = ascent;
        this.height = height;
        chars = unicodeChars;

    }
}
