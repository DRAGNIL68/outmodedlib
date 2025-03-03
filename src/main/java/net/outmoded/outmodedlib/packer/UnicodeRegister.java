package net.outmoded.outmodedlib.packer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.UnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;

public class UnicodeRegister extends Writable {
    private final ArrayList <UnicodeProvider> providers;

    @JsonIgnore
    private int currentUnicodeValue = 0xF0000;



    @JsonIgnore
    public UnicodeRegister() {
        setFilePath("assets/minecraft/font/default.json");
        providers = new ArrayList<UnicodeProvider>();
    }

    //UnicodeProvider(String type, String unicodeChar, int ascent, int height, String file
    @JsonIgnore
    public char addUnicodeChar(unicodeType type, int ascent, int height, String namespacedTexturePath){
        if (currentUnicodeValue > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)"); // this will never be exceeded lmao

        }
        String s = Character.toString((char)currentUnicodeValue); // converts a hex value to a string

        char unicode = (char) currentUnicodeValue; // copy of Unicode as char
        UnicodeProvider unicodeProvider = new UnicodeProvider(type.toString().toLowerCase(), s, ascent, height, namespacedTexturePath); // add a new provider (pack witchcraft)

        currentUnicodeValue++; // add 1 to the Unicode value
        providers.add(unicodeProvider);
        return unicode;
    }

    public enum unicodeType {
        BITMAP
    }


}
