package net.outmoded.outmodedlib.packer.jsonObjects;

import java.util.HashMap;
import java.util.Map;

public class McMeta extends Writable {

    private final Map<String, Object> pack = new HashMap<>();
    public McMeta(String description, int version){
        setFilePath("pack.mcmeta");
        pack.put("pack_format", version);
        pack.put("description", description);
    }

}
