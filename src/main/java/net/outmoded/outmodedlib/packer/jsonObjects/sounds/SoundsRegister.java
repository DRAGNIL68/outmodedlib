package net.outmoded.outmodedlib.packer.jsonObjects.sounds;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class SoundsRegister extends Writable {

    @JsonIgnore
    private final Map<String, Object> sounds = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return sounds;

    }

    @JsonIgnore
    String fullPath;

    /**
     * you can only have 1 sounds.json file resource pack
     * if you write 2 sounds.json files to the same resource pack the first file will be overwritten!
     */
    public SoundsRegister(){
        fullPath = "assets/sounds.json";
        setFilePath(fullPath);
    }


    public SoundsRegister addSound(Sound sound){
        if (!sound.sounds.containsKey("name")){
            return this;
        }

        sounds.put((String) sound.sounds.get("name"), sound);
        return this;

    }
}
