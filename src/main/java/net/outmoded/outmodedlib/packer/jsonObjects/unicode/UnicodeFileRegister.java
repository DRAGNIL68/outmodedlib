package net.outmoded.outmodedlib.packer.jsonObjects.unicode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.ResourcePack;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers.BitmapUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers.SpaceUnicodeProvider;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers.TtfUnicodeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class UnicodeFileRegister extends Writable {
    private final ArrayList<BaseUnicodeProvider> providers;

    @JsonIgnore
    private ResourcePack resourcePack;
    @JsonIgnore
    public final String namespacedWritePath ;
    @JsonIgnore
    public UnicodeFileRegister(String namespacedWritePath) {
        this.namespacedWritePath = namespacedWritePath;
        String fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/font/"+splitNamespaceId(namespacedWritePath)[1]+".json";
        setFilePath(fullPath);

        providers = new ArrayList<BaseUnicodeProvider>();
    }

    public <T> void addUnicodeProvider(BaseUnicodeProvider unicodeProvider){
        providers.add(unicodeProvider);

    }


}
