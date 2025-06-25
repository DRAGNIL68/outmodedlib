package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;

import java.util.ArrayList;

public class TtfUnicodeProvider extends BaseUnicodeProvider {

    public final int size;
    public final int  oversample;
    public final String file;
    public final ArrayList<String> chars;
    public final ArrayList<String> skip = new ArrayList<String>();
    public ArrayList<Integer> shift;

    @JsonIgnore
    public TtfUnicodeProvider(char unicodeChar, String namespacedTexturePath ,int size, int oversample){
        super(UnicodeType.TTF);
        this.size = size;
        this. oversample =  oversample;
        this.file = namespacedTexturePath;
        chars = new ArrayList<String>();
        chars.add(String.valueOf(chars));

    }


    public TtfUnicodeProvider addSkipCase(String value){
        skip.add(value);
        return this;
    }

    public void setShift(int int1, int int2){

        this.shift = new ArrayList<Integer>();
        this.shift.add(int1);
        this.shift.add(int2);

    }

    @Override
    public String getStringType() {
        return "ttf";
    }
}
