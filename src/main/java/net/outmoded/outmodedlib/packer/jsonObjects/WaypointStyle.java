package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class WaypointStyle extends Writable {

    @JsonProperty("near_distance")
    private Double nearDistance;

    @JsonProperty("far_distance")
    private Double farDistance;

    @JsonProperty("sprites")
    private ArrayList<String> sprites;

    @JsonIgnore
    String fullPath;

    /**
     * namespace:waypoint_style/frog -> assets/namespace/waypoint_style/frog.json
     * <a href="https://minecraft.wiki/w/Waypoint_style">...</a>
     * @param namespacedWritePath
     */
    public WaypointStyle(String namespacedWritePath){
        fullPath = "assets/"+splitNamespaceId(namespacedWritePath)[0]+"/waypoint_style/"+splitNamespaceId(namespacedWritePath)[1]+".json";
        setFilePath(fullPath);

    }


    public void setNearDistance(Double nearDistance) {
        this.nearDistance = nearDistance;
    }

    public void setFarDistance(Double farDistance){
        this.farDistance = farDistance;
    }


    /**
     * add a sprite to the waypoint
     * @param namespacedSpritePath
     * @return
     */
    public WaypointStyle addSprite(String namespacedSpritePath){
        sprites.add(namespacedSpritePath);
        return this;

    }
}
