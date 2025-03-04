package net.outmoded.outmodedlib.packer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.UnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;

public class UnicodeRegister extends Writable {
    private final ArrayList <UnicodeProvider> providers;

    @JsonIgnore
    private int currentUnicodeValue = 0xF0000;
    private ResourcePack resourcePack;


    @JsonIgnore
    public UnicodeRegister() {
        setFilePath("assets/minecraft/font/default.json");
        providers = new ArrayList<UnicodeProvider>();
    }

    //UnicodeProvider(String type, String unicodeChar, int ascent, int height, String file
    @JsonIgnore
    public char addUnicodeChar(UnicodeType type, int ascent, int height, String namespacedTexturePath){
        if (currentUnicodeValue > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)"); // this will never be exceeded lmao

        }
         // converts a hex value to a string
        ArrayList<String> chars = new ArrayList<String>();
        chars.add(String.valueOf((char)currentUnicodeValue));


        char unicode = (char) currentUnicodeValue; // copy of Unicode as char
        UnicodeProvider unicodeProvider = new UnicodeProvider(type.toString().toLowerCase(), chars, ascent, height, namespacedTexturePath); // add a new provider (pack witchcraft)

        currentUnicodeValue++; // add 1 to the Unicode value
        providers.add(unicodeProvider);
        return unicode;
    }

    //UnicodeProvider(String type, String unicodeChar, int ascent, int height, String file
    @JsonIgnore
    public char addUnicodeCharSpriteSheet(UnicodeType type, int ascent, int height, String namespacedTexturePath, TextureSize spriteSheetSize , TextureSize spriteSheetGridSize){ // must be dividable by 2

        if (currentUnicodeValue > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)"); // this will never be exceeded lmao

        }
        // converts a hex value to a string

        ArrayList<String> chars = new ArrayList<String>();

        int w = spriteSheetSize.width / spriteSheetGridSize.width;
        int h = spriteSheetSize.height / spriteSheetGridSize.width;


        for (int x = 0; x < w; x++) {
            String rowOfChars = "";

            for (int y = 0; y < h; y++) {
                rowOfChars = rowOfChars.concat(String.valueOf((char)currentUnicodeValue));
                currentUnicodeValue++;
            }

            chars.add(rowOfChars);
        }





        char unicode = (char) currentUnicodeValue; // copy of Unicode as char
        UnicodeProvider unicodeProvider = new UnicodeProvider(type.toString().toLowerCase(), chars, ascent, height, namespacedTexturePath); // add a new provider (pack witchcraft)

         // add 1 to the Unicode value
        providers.add(unicodeProvider);
        return unicode;
    }

    public enum UnicodeType {
        BITMAP
    }


}
