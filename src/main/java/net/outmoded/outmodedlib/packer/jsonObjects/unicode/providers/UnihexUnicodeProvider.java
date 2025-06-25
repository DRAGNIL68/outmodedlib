package net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.BaseUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeType;

import java.util.ArrayList;

public class UnihexUnicodeProvider extends BaseUnicodeProvider {

    @JsonProperty("hex_file")
    public final String file;

    @JsonProperty("size_overrides")
    public ArrayList<UnihexSizeOverrides> sizeOverrides;

    @JsonIgnore
    public UnihexUnicodeProvider(String namespacedZipPath){
        super(UnicodeType.UNIHEX);
        this.file = namespacedZipPath;


    }

    public UnihexUnicodeProvider addSizeOverridesCase(UnihexSizeOverrides sizeOverrides){
        if (this.sizeOverrides == null)
            this.sizeOverrides = new ArrayList<>();

        this.sizeOverrides.add(sizeOverrides);
        return this;
    }


    @Override
    public String getStringType() {
        return "unihex";
    }


}


