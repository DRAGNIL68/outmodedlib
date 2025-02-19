package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.nio.file.Paths;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Writable{

    @JsonIgnore
    private String filePath;

    @JsonIgnore
    public Writable(String filePath){
        this.filePath = filePath;
    }

    @JsonIgnore
    public String getFileName(){
        return Paths.get(filePath).getFileName().toString();

    }

    @JsonIgnore
    public String getFilePath(){
        return filePath;

    }

    @JsonIgnore
    public void setFilePath(String newFilePath){
        filePath = newFilePath;

    }

}
