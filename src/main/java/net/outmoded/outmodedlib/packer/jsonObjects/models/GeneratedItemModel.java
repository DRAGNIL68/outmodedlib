package net.outmoded.outmodedlib.packer.jsonObjects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;
import java.util.HashMap;
import java.util.Map;

public class GeneratedItemModel extends Writable {

    private String parent;
    private final Map<String, String> textures = new HashMap<>();



    @JsonIgnore
    public GeneratedItemModel(String namespace, String texturePath, modelType modelType ,String writePath){
        super(writePath);
        textures.put("layer0", namespace + ":" + texturePath);
        parent = "item/" + modelType.toString();
    }

    @JsonIgnore
    public String getTexture(){
        return textures.get("layer0");
    }



    public enum modelType{
        generated,
        handheld
    }

}
