package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaceUnicodeProvider extends BaseUnicodeProvider {
    public final HashMap<String, Integer> advances = new HashMap<>();


    /**
     * Refer to https://minecraft.wiki/w/Resource_pack#Fonts for more info
     * must be before chars definition, or it will not work
     */
    @JsonIgnore
    public SpaceUnicodeProvider(){
        super(UnicodeType.SPACE);

    }

    public SpaceUnicodeProvider addAdvancesCase(@NotNull String key, @NotNull int value){
        advances.put(key, value);
        return this;
    }

    @Override
    public String getStringType() {
        return "space";
    }
}
