package net.outmoded.outmodedlib.packer.jsonObjects.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class EquipmentLayer extends NonWritable {

    @JsonIgnore
    final Equipment.LayerType layerType;

    @JsonProperty("texture")
    private String texture;

    @JsonProperty("dyeable")
    private Map<String, Integer> dyeable = null;


    /*
    A namespaced id pointing to a texture for this layer.
    namespace:path resolves to assets/<namespace>/textures/entity/equipment/<layer_type>/<path>.png.
    by namespaced it means the texture path will go from "mynamespace:folder/texture.png" to assets/mynamespace/textures/entity/equipment/<layer_type>/folder/texture.png
     */
    public EquipmentLayer(Equipment.LayerType layerType, String namespacedTexturePath) {
        this.layerType = layerType;
        this.texture = namespacedTexturePath;
    }


    /*
    makes the layer dyeable
     */
    public void setDyeable(){
        dyeable = new HashMap<>();
    }

    public void setColorWhenUndyed(Color color){
        if (dyeable == null){
            dyeable = new HashMap<>();
        }

        if (color == null)
            return;

        dyeable.put("color_when_undyed", color.asRGB());
    }





}



