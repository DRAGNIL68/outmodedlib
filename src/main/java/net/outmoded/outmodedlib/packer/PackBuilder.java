package net.outmoded.outmodedlib.packer;

import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class PackBuilder {
    FileSystem fileSystem;


    public void startBuild(){
        // For a simple file system with Unix-style paths and behavior:
        try {

            fileSystem = Jimfs.newFileSystem(Configuration.unix());


            Path foo = fileSystem.getPath("/foo");

            Files.createDirectory(foo);

            Path hello = foo.resolve("hello.txt"); // /foo/hello.txt
            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);

        } catch (Exception e){
            throw new RuntimeException(e);

        }


    }


    public void endBuild(){
        // clear old pack
        // set new pack to active
        // clear generated pack variable

    }

    public void addNamespace(String namespace){
        try {
            Path directory = fileSystem.getPath("/" + namespace);
            Files.createDirectory(directory);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createPath(String path){
        try {
            Path directory = fileSystem.getPath(path);
            Files.createDirectory(directory);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void createMcmeta(String description){
        try {
            Path directory = fileSystem.getPath("/");
            Files.createDirectory(directory);

            Path hello = directory.resolve("pack.mcmeta"); // /foo/hello.txt
            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
