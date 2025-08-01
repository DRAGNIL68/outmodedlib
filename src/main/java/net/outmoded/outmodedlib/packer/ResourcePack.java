package net.outmoded.outmodedlib.packer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import net.outmoded.outmodedlib.InternalPackContent;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;
import org.jetbrains.annotations.ApiStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

/**
 * the place where all cool things start
 */
public class ResourcePack {
    FileSystem fileSystem;
    private final String name;
    private boolean debugMode = false;

    public ResourcePack(String name){
        fileSystem = Jimfs.newFileSystem(Configuration.unix());
        this.name = name;
        createPath("/assets");

        InternalPackContent.addContent(this); // adds stuff that all packs need

    }
    public String getName(){
        return name;

    }

    @ApiStatus.Internal
    /**
     * lets you mess with the virtual file system
     */
    public FileSystem getFileSystem(){
        return fileSystem;
    }

    /**
     * used to copy a file from your computers drive to the resource pack
     * @param filePath
     * @param pastePath
     * @return
     */
    public boolean copyFileFromDisk(String filePath, String pastePath) {
        try {
            Path directory = fileSystem.getPath(pastePath); // gets path in virtual file system
            Path path = Paths.get(filePath); // gets file from disk
            if (!Files.exists(path)){
                return false;


            }
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Files.copy(path, directory, StandardCopyOption.REPLACE_EXISTING);

            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * copy a file from your plugins resources folder to the resource pack
     * @param inputStream
     * @param pastePath
     * @return
     */
    public boolean copyFileFromResources(InputStream inputStream, String pastePath) { // use something like this as input: InputStream inputStream = MyPlugin.getInstance().getResource("plugin.yml");
        try {

            Path directory = fileSystem.getPath(pastePath); // gets path in virtual file system
            if (inputStream == null){
                return false;
            }

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Files.copy(inputStream, directory, StandardCopyOption.REPLACE_EXISTING);

            return true;

        } catch (IOException e) {
            throw new RuntimeException("failed to copy file from resources");

        }
    }

    /**
     * used to copy a file from your computers drive to the resource pack
     * @param filePath
     * @param pastePath
     * @return
     */
    public boolean copyFileFromDisk(Path filePath, String pastePath) {
        try {
            Path directory = fileSystem.getPath(pastePath); // gets path in virtual file system
            Path path = filePath; // gets file from disk
            if (!Files.exists(path)){
                return false;


            }
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Files.copy(path, directory, StandardCopyOption.REPLACE_EXISTING);

            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * this has no real use
     * @param path
     * @return
     */
    public boolean createPath(String path){
        try {
            Path directory = fileSystem.getPath(path);

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
                return true;
            }
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * used to create a generic file like a text file
     * @param filePath
     * @param contents
     */
    public void createGenericFile(String filePath, String contents){
        try {

            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

            Path directory = fileSystem.getPath(filePath);

            if (directory.getParent() != null) {
                Files.createDirectories(directory.getParent());
            }

            if (!Files.exists(directory)) {
                Files.createFile(directory);
            }

            Files.write(directory, ImmutableList.of(contents), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * needs rewriting
     * @param filePath
     * @param textureAsBase64
     */
    public void base64ToTexture(String filePath, String textureAsBase64 ) {
        try {
            Path directory = fileSystem.getPath(filePath);

            byte[] decodedBytes = Base64.getDecoder().decode(textureAsBase64);


            if (!Files.exists(directory.getParent())) {
                Files.createDirectories(directory.getParent());
            }

            Path file = directory.resolve(directory.getFileName());

            Files.write(file, decodedBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if a file exists in the resource pack
     * @param filePath
     * @return
     */
    public boolean hasFile(String filePath){
        Path directory = fileSystem.getPath(filePath);
        return Files.exists(directory);
    }

    public Path getPath(String path){
        if (path == null)
            return null;


        Path directory = fileSystem.getPath(path);

        return directory;

    }


    /**
     * lets you write a component like Unicode File or Item Model Definition to the resource pack
     * @param object
     * @return
     */
    public ResourcePack writeJsonObject(Writable object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createGenericFile(object.getFilePath(), objectMapper.writeValueAsString(object));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    /**
     * creates a zip file
     * @param outputFilePath
     */
    public void build(String outputFilePath) {
        try {

            if (fileSystem == null){
                throw new RuntimeException("Resource pack has already been closed");
            }

            ZipFileUtil zipFileUtil = new ZipFileUtil(outputFilePath, this);
            zipFileUtil.addToZip("");
            //zipFileTest.addToZip("pack.mcmeta");
            if (!hasFile("pack.mcmeta")){
                throw new RuntimeException("Resource pack dose not have pack.mcmeta");
            };
            zipFileUtil.endZip();

        } catch (IOException e) {
            throw new RuntimeException("could not build resource pack");
        }


    }

    /**
     * closes a resource packs virtual file system, will throw errors if you try to use it after its closed
     */
    public void closeFileSystem(){
        try {
            fileSystem.close();
            fileSystem = null;
        } catch (IOException e) {
            throw new RuntimeException("Resource pack already closed");
        }
    }

    /**
     * prints everything added to console
     * @param debugMode
     */
    public void setDebugMode(boolean debugMode){
        this.debugMode = debugMode;
    }

    public boolean getDebugMode(){
        return debugMode;
    }








}
