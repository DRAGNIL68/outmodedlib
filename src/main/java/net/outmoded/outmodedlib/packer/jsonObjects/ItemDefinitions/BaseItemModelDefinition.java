package net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseItemModelDefinition extends Writable {
    @JsonProperty("oversized_in_gui")
    private Boolean oversizedInGui = null;

    @JsonProperty("hand_animation_on_swap")
    private Boolean handAnimationOnSwap = null;

    @JsonProperty("model")
    protected final Map<String, Object> baseProperties = new HashMap<>();

    public BaseItemModelDefinition(String namespacedWritePathOfModelDef){
        // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json
        String fullPath;

        fullPath = "assets/"+splitNamespaceId(namespacedWritePathOfModelDef)[0]+"/items/"+splitNamespaceId(namespacedWritePathOfModelDef)[1]+".json"; // test_namespace:frog_sword -> assets/test_namespace/items/frog_sword.json


        setFilePath(fullPath);
    }


    @JsonIgnore
    public Map<String, Object> getHashmap(){
        return baseProperties;
    }

    public void setHandAnimationOnSwap(boolean b){
        handAnimationOnSwap = b;

    }

    public void setOversizedInGui(boolean b){
        oversizedInGui = b;

    }

    public void setFallback(BaseItemModelDefinition modelDefinition){
        baseProperties.put("fallback", modelDefinition.getHashmap());
    }






}
