package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ReferenceUnicodeProvider extends BaseUnicodeProvider {

    public final String id;


    /**
     * Refer to https://minecraft.wiki/w/Resource_pack#Fonts for more info
     */
    @JsonIgnore
    public ReferenceUnicodeProvider(String namespacedIdToFont){
        super(UnicodeType.REFERENCE);
        this.id = namespacedIdToFont;
    }


    @Override
    public String getStringType() {
        return "reference";
    }
}
