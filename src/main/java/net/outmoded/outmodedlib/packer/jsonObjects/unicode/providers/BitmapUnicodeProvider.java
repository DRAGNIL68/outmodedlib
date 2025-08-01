package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;

import java.util.ArrayList;

public class BitmapUnicodeProvider extends BaseUnicodeProvider {

    public final double ascent;
    public final double height;
    public final String file;
    public final String[] chars;


    /**
     * Refer to https://minecraft.wiki/w/Resource_pack#Fonts for more info
     */
    @JsonIgnore
    public BitmapUnicodeProvider(char unicodeChar, String namespacedTexturePath, double ascent, double height){
        super(UnicodeType.BITMAP);
        this.ascent = ascent;
        this.height = height;
        this.file = namespacedTexturePath;
        chars = new String[]{String.valueOf(unicodeChar)};
    }


    /** can be used to calculate a proper texture split for sprite sheets
    *    ArrayList<String> chars = new ArrayList<String>();
    *    ArrayList<String> returnChars = new ArrayList<String>();
    * <p>
    *   int w = spriteSheetSize.width / spriteSheetGridSize.width;
    *   int h = spriteSheetSize.height / spriteSheetGridSize.width;
    * <p>
    *
    *        for (int y = 0; y < h; y++) {
    *        String rowOfChars = "";
    * <p>
    *        for (int x = 0; x < w; x++) {
    *            rowOfChars = rowOfChars.concat(String.valueOf((char)currentUnicodeValue));
    *            returnChars.add(String.valueOf((char)currentUnicodeValue));
    *            currentUnicodeValue++;
    *        }
    * <p>
    *        chars.add(rowOfChars);
    *  }
    */
    @JsonIgnore
    public BitmapUnicodeProvider(String[] unicodeChars, String namespacedTexturePath, double ascent, double height){
        super(UnicodeType.BITMAP);
        this.ascent = ascent;
        this.height = height;
        chars = unicodeChars;
        this.file = namespacedTexturePath;
    }

    @JsonIgnore
    public String getStringType() {
        return "bitmap";
    }
}
