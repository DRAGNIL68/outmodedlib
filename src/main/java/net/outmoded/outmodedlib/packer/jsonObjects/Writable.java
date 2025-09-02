package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Writable{

    @JsonIgnore
    private String filePath; // the file path inside the virtual file system it will be written to

    @JsonIgnore
    public String getNamespace() {
        return namespace;
    }

    @JsonIgnore
    private String namespace; // the namespace so I can make my shitty system work with the namespace



    @JsonIgnore
    public String getFileName(){
        return Paths.get(filePath).getFileName().toString();

    }

    @JsonIgnore
    public @NotNull String getFilePath(){
        return filePath;

    }

    @JsonIgnore
    public void setFilePath(String newFilePath){
        filePath = newFilePath;

    }


    // namespace.writeJsonObject(someObject(lalal, custom_swords/sword1))
    // pack.writeJsonObject(someObject(lalal, "namespace:custom_swords/sword1"))

}
