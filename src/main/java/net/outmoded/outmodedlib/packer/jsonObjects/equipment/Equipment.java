package net.outmoded.outmodedlib.packer.jsonObjects.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class Equipment extends Writable {

    @JsonProperty("subtitle")
    private String subtitle = null;

    @JsonProperty("layers")
    protected final Map<String, ArrayList<EquipmentLayer>> equpimentLayerHashMap = new HashMap<>();

    /**
     * <a href="https://minecraft.wiki/w/Equipment">...</a>
     * Equipment definitions are stored in the assets/<namespace>/equipment directory of a resource pack.
     * A good example would be "mynamespace:custom_armour" == "assets/mynamespace/equipment/custom_armour.json
     */
    public Equipment(String namespacedFilePath){
        String fullPath = "assets/"+splitNamespaceId(namespacedFilePath)[0]+"/equipment/"+splitNamespaceId(namespacedFilePath)[1]+".json";
        setFilePath(fullPath);
    }


    public void addEquipmentLayer(EquipmentLayer equipmentLayer){

        if (!equpimentLayerHashMap.containsKey(equipmentLayer.layerType.toString())){

            ArrayList<EquipmentLayer> equipmentLayers = new ArrayList<>();
            equipmentLayers.add(equipmentLayer);
            equpimentLayerHashMap.put(equipmentLayer.layerType.toString(), equipmentLayers);

        }
        else {
            ArrayList<EquipmentLayer> equipmentLayers = equpimentLayerHashMap.get(equipmentLayer.layerType.toString());

            equipmentLayers.add(equipmentLayer);
            equpimentLayerHashMap.put(equipmentLayer.layerType.toString(), equipmentLayers);

        }
    }


    public enum LayerType{
        wolf_body,
        horse_body,
        llama_body,
        humanoid,
        humanoid_leggings,
        wings,
        pig_saddle,
        strider_saddle,
        camel_saddle,
        horse_saddle,
        donkey_saddle,
        mule_saddle,
        skeleton_horse_saddle,
        zombie_horse_saddle,
        happy_ghast_body,
        nautilus_body,
        nautilus_saddle,
        camel_husk_saddle

    }



}
