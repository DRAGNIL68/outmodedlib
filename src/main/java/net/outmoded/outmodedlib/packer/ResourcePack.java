package net.outmoded.outmodedlib.packer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.packer.jsonObjects.Writable;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.bukkit.Bukkit.getServer;

public class ResourcePack {
    FileSystem fileSystem;
    private final Map<String, Namespace> namespaces = new HashMap<>();
    private final String name;

    public ResourcePack(String name){
        fileSystem = Jimfs.newFileSystem(Configuration.unix());
        this.name = name;
        createPath("/assets");

        InternalContent.addInternalPackContent(this);

    }
    public String getName(){
        return name;

    }

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

    public boolean copyFileFromPluginResources(String filePath, String pastePath) {
        Path file = new File(Outmodedlib.getInstance().getDataFolder(), "contents/invisible.png").toPath();
        return copyFileFromDisk(file, pastePath);
    }

    @ApiStatus.Internal
    public void registerNamespace(Namespace namespace){ // registers texture packs so I can keep track on namespaces
        namespaces.put(namespace.getNamespaceKey(), namespace);

    }

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

    public void createGenericFile(String filePath, String contents){
        try {

            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

            Path directory = fileSystem.getPath(filePath);

            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "frog: " + directory.getFileName().toString() + " : " + directory.getParent());

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


    public boolean namespaceExists(String namespace){
        if (namespaces.containsKey(namespace))
            return true;
        else{
            return false;
        }

    }

    public Namespace getNamespace(String namespace){
        if (namespaces.containsKey(namespace))
            return namespaces.get(namespace);

        return null;

    }

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



    public void writeJsonObject(Writable object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createGenericFile(object.getFilePath(), objectMapper.writeValueAsString(object));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void build(String outputFilePath) {
        try {
            ZipFileUtil zipFileUtil = new ZipFileUtil(outputFilePath, fileSystem);
            zipFileUtil.addToZip("");
            //zipFileTest.addToZip("pack.mcmeta");
            if (!hasFile("pack.mcmeta")){
                throw new RuntimeException("Resource pack dose not have pack.mcmeta!");
            };
            zipFileUtil.endZip();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }








}
