package net.outmoded.outmodedlib.packer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.UnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;
import static org.bukkit.Bukkit.getServer;

public class UnicodeFileRegister extends Writable {
    private final ArrayList <UnicodeProvider> providers;

    @JsonIgnore
    private int currentUnicodeValue = 0xF0001;

    @JsonIgnore
    private ResourcePack resourcePack;
    @JsonIgnore
    public final String namespacedWritePath ;
    @JsonIgnore
    public UnicodeFileRegister(int unicodeStartingPointOffset, String namespacedWritePath) {
        currentUnicodeValue++;
        this.namespacedWritePath = namespacedWritePath;
        String fullPath;
        fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/font/"+splitNamespaceId(namespacedWritePath)[1]+".json";
        setFilePath(fullPath);

        currentUnicodeValue += unicodeStartingPointOffset;
        providers = new ArrayList<UnicodeProvider>();
    }

    //UnicodeProvider(String namespacedWritePath, String type, String unicodeChar, int ascent, int height, String file
    @JsonIgnore
    public UnicodeProvider addUnicodeChar(UnicodeType type, int ascent, int height, String namespacedTexturePath){
        if (currentUnicodeValue > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)");

        }
         // converts a hex value to a string

        char unicode = (char) currentUnicodeValue; // copy of Unicode as char
        ArrayList<String> chars = new ArrayList<String>();

        chars.add(String.valueOf((char)currentUnicodeValue)); // only one Unicode

        UnicodeProvider unicodeProvider = new UnicodeProvider(namespacedWritePath, type, chars, ascent, height, namespacedTexturePath+".png"); // add a new provider (pack witchcraft)

        currentUnicodeValue++; // add 1 to the Unicode value
        providers.add(unicodeProvider);
        return unicodeProvider;
    }

    public String[] addUnicodeChar(UnicodeType type, int ascent, int height, String namespacedTexturePath, int currentUnicodeValueOverride){
        if (currentUnicodeValueOverride > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)");

        }
        // converts a hex value to a string

        char unicode = (char) currentUnicodeValue; // copy of Unicode as char
        ArrayList<String> chars = new ArrayList<String>();

        chars.add(String.valueOf((char)currentUnicodeValueOverride)); // only one Unicode

        UnicodeProvider unicodeProvider = new UnicodeProvider(namespacedWritePath, type, chars, ascent, height, namespacedTexturePath+".png"); // add a new provider (pack witchcraft)

        providers.add(unicodeProvider);

        return new String[]{namespacedWritePath, String.valueOf(unicode)}; // namespace:font|�
    }

    //UnicodeProvider(String type, String unicodeChar, int ascent, int height, String file
    @JsonIgnore
    public UnicodeProvider addUnicodeCharSpriteSheet(UnicodeType type, int ascent, int height, String namespacedTexturePath, TextureSize spriteSheetSize , TextureSize spriteSheetGridSize){ // must be dividable by 2

        if (currentUnicodeValue > 0xFFFFD){
            throw new RuntimeException("exceeded maximum unicode character limit (65,534)");

        }
        // converts a hex value to a string

        ArrayList<String> chars = new ArrayList<String>();
        ArrayList<String> returnChars = new ArrayList<String>();

        int w = spriteSheetSize.width / spriteSheetGridSize.width;
        int h = spriteSheetSize.height / spriteSheetGridSize.width;


        for (int y = 0; y < h; y++) {
            String rowOfChars = "";

            for (int x = 0; x < w; x++) {
                rowOfChars = rowOfChars.concat(String.valueOf((char)currentUnicodeValue));
                returnChars.add(String.valueOf((char)currentUnicodeValue));
                currentUnicodeValue++;
            }

            chars.add(rowOfChars);
        }

        UnicodeProvider unicodeProvider = new UnicodeProvider(namespacedWritePath ,type, chars, ascent, height, namespacedTexturePath+".png"); // add a new provider (pack witchcraft)

         // add 1 to the Unicode value
        providers.add(unicodeProvider);

        return unicodeProvider;
    }

    public int getCurrentUnicodeValue(){
        return currentUnicodeValue;
    }

    public enum UnicodeType {
        BITMAP,
        TTF
    }


}
