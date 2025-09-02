package net.outmoded.outmodedlib.packer.jsonObjects.sounds;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;

import java.util.HashMap;
import java.util.Map;

public class Sound extends NonWritable {

    @JsonProperty("subtitle")
    private String subtitle = null;

    @JsonProperty("sounds")
    protected final Map<String, Object> sounds = new HashMap<>();

    /**
     * <a href="https://minecraft.wiki/w/Sounds.json">...</a>
     */
    public Sound(){}

    /**
     * Translated as the subtitle of the sound if Show Subtitles is enabled ingame. Accepts formatting codes and displays them properly in-game. Optional.
     * <a href="https://minecraft.wiki/w/Formatting_codes">...</a>
     * @param
     */
    public Sound setSubtitle(String subtitle){
        this.subtitle = subtitle;
        return this;
    }


    /**
     * The volume for playing this sound. Value is a decimal greater than 0.0. If undefined, defaults to 1.0.
     * @param
     */
    public Sound setVolume(Double volume){
        sounds.put("volume", volume);
        return this;
    }

    /**
     * Plays the pitch at the specified value. Value is a decimal greater than 0.0. If undefined, defaults to 1.0, but higher and lower values can be chosen.
     * @param
     */
    public Sound setPitch(Double pitch){
        sounds.put("pitch", pitch);
        return this;
    }

    /**
     * The chance that this sound is selected to play when this sound event is triggered. Defaults to 1. An example: putting 2 in for the value would be like placing in the name twice. Only accepts integers.
     * @param
     */
    public Sound setWeight(Integer weight){
        sounds.put("weight", weight);
        return this;
    }

    /**
     * true/false. True if this sound should be streamed from its file. It is recommended that this is set to "true" for sounds that have a duration longer than a few seconds to avoid lag. Used for all sounds in the "music" and "record" categories (except Note Block sounds), as (almost) all the sounds that belong to those categories are over a minute long. Optional. If undefined, defaults to "false". Setting this to false allows many more instances of the sound to be run at the same time while setting it to true only allows 4 instances (of that type) to be run at the same time.
     * @param
     */
    public Sound setStream(Boolean stream){
        sounds.put("stream", stream);
        return this;
    }

    /**
     * true/false. True if this sound should be loaded when loading the pack instead of when the sound is played. Used by the underwater ambience. Defaults to "false".
     * @param
     */
    public Sound setPreload(Boolean preload){
        sounds.put("preload", preload);
        return this;
    }

    /**
     * The path to this sound file from the "namespace/sounds" folder (excluding the .ogg file extension).
     * The namespace defaults to minecraft, but it can be changed by prepending a namespace and separating it with a :. Uses
     * forward slashes instead of backslashes. May instead be the name of another sound event (according to value of "type").
     * If the sound file has one channel (mono), it can be played locationally (sound volume decreases the farther you are from the source).
     * If the file has two channels (stereo), the volume does not change (for example music, ambient sounds).
     * Names are also not allowed to contain whitespace characters.
     * @param name
     */
    public Sound setName(String name){
        sounds.put("name", name);
        return this;
    }


    public enum type{
        FILE,
        EVENT,

    }
}
