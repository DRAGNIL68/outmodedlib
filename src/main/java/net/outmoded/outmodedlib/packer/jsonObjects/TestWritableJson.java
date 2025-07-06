package net.outmoded.outmodedlib.packer.jsonObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


// an example on how to add extra json types to a resource pack
public class TestWritableJson extends Writable {
    @JsonIgnore // use @JsonIgnore if you don't want it do be added to a json file (it will add private variables if you don't)
    private int num;


    private HashMap<String, Object> example = new HashMap<>(); // I sometimes use Object in my hashmaps as it lets you add more than one type

    private HashMap<String, TestNonWritableClass> example1 = new HashMap<>(); // you can add extra sections inside a json file by making a class that extends the NonWritableClass


    private TestNonWritableClass testNonWritableClass;

    @JsonProperty("renamedField")
    private String frog;

    public TestWritableJson(String frog){
        setFilePath(frog + ".txt"); // you must set the path, or it will not work (it's not a super call to allow for grater flexibility.)
        // (it needs setting before it is written to the back, so it could be in a method or something if you so wish)

        this.frog = frog;

        testNonWritableClass = new TestNonWritableClass(frog);
        example1.put("key", testNonWritableClass);

    }

    // if a section of json is in {} use a hashmap, if a section of json is in [] use a list

}
