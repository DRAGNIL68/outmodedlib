package net.outmoded.outmodedlib.packer.jsonObjects.unicode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.outmoded.outmodedlib.packer.jsonObjects.NonWritable;
import org.jetbrains.annotations.NotNull;

public abstract class BaseUnicodeProvider extends NonWritable implements UnicodeProviderInterface{


    public final String type;
    public Filter filter;

    @JsonIgnore
    public BaseUnicodeProvider(UnicodeType type){
        this.type = String.valueOf(type).toLowerCase();
    }


    @JsonIgnore
    public void setFilter(@NotNull Filter filter){
        this.filter = filter;
    }

}
