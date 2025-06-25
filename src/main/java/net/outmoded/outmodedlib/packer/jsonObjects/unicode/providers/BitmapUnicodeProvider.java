package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;

import java.util.ArrayList;

public class BitmapUnicodeProvider extends BaseUnicodeProvider {

    public final int ascent;
    public final int height;
    public final String file;
    public final ArrayList<String> chars;

    @JsonIgnore
    public BitmapUnicodeProvider(char unicodeChar, String namespacedTexturePath ,int ascent, int height){
        super(UnicodeType.BITMAP);
        this.ascent = ascent;
        this.height = height;
        this.file = namespacedTexturePath;
        chars = new ArrayList<>();
        chars.add(String.valueOf(chars));

    }


// can be used to calculate a proper texture split for sprite sheets
//    ArrayList<String> chars = new ArrayList<String>();
//    ArrayList<String> returnChars = new ArrayList<String>();
//
//    int w = spriteSheetSize.width / spriteSheetGridSize.width;
//    int h = spriteSheetSize.height / spriteSheetGridSize.width;
//
//
//        for (int y = 0; y < h; y++) {
//        String rowOfChars = "";
//
//        for (int x = 0; x < w; x++) {
//            rowOfChars = rowOfChars.concat(String.valueOf((char)currentUnicodeValue));
//            returnChars.add(String.valueOf((char)currentUnicodeValue));
//            currentUnicodeValue++;
//        }
//
//        chars.add(rowOfChars);
//    }
    @JsonIgnore
    public BitmapUnicodeProvider(ArrayList<String> unicodeChars, String namespacedTexturePath, int ascent, int height){
        super(UnicodeType.BITMAP);
        this.ascent = ascent;
        this.height = height;
        chars = unicodeChars;
        this.file = namespacedTexturePath;
    }

    @Override
    public String getStringType() {
        return "bitmap";
    }
}
