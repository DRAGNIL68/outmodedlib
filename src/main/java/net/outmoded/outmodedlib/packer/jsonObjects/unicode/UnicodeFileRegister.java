package net.outmoded.outmodedlib.packer.jsonObjects.unicode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.ResourcePack;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class UnicodeFileRegister extends Writable {
    private final ArrayList<BaseUnicodeProvider> providers;

    @JsonIgnore
    private ResourcePack resourcePack;
    @JsonIgnore
    public final String namespacedWritePath ;


    /**
     * Refer to https://minecraft.wiki/w/Resource_pack#Fonts for more info
     */
    @JsonIgnore
    public UnicodeFileRegister(String namespacedWritePath) {
        this.namespacedWritePath = namespacedWritePath;
        String fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/font/"+splitNamespaceId(namespacedWritePath)[1]+".json";
        setFilePath(fullPath);

        providers = new ArrayList<>();
    }

    public <T> UnicodeFileRegister addUnicodeProvider(BaseUnicodeProvider unicodeProvider){
        providers.add(unicodeProvider);
        return this;
    }


}
