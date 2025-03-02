package net.outmoded.outmodedlib.packer.jsonObjects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;
import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class GeneratedItemModel extends Writable {

    private String parent;
    private final Map<String, String> textures = new HashMap<>();



    @JsonIgnore
    public GeneratedItemModel(String namespacedTexturePath, modelType modelType ,String namespacedWritePath){
        String fullPath;
        if (namespacedTexturePath.contains(":")){
            fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/models/"+splitNamespaceId(namespacedWritePath)[1]+".json"; // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        }
        else{

            fullPath = "models/"+splitNamespaceId(namespacedWritePath)[1]+".json";

        }





        setFilePath(fullPath);

        textures.put("layer0", namespacedTexturePath);
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
