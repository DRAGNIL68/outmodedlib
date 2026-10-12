package net.outmoded.outmodedlib.packer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class Test implements WritableInter{

    @JsonProperty("frog_test")
    int frog = 3;

    @Override
    public String getFileName() {
        return "";
    }

    @Override
    public @NotNull String getFilePath() {
        return "";
    }

    @Override
    public void setFilePath(String newFilePath) {

    }
}
