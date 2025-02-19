package net.outmoded.outmodedlib.packer;


import net.outmoded.outmodedlib.packer.jsonObjects.Writable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.bukkit.Bukkit.getServer;

public class Namespace {

    private final String namespace;
    private final String namespacePath;
    private final ResourcePack resourcePack;

    public Namespace(String namespace, ResourcePack resourcePack) {
        this.namespace = namespace;
        this.resourcePack = resourcePack;
        namespacePath = "assets/" + namespace + "/";
        resourcePack.registerNamespace(this);
    }


    public String getNamespacePathAsString() {
        return namespacePath;
    }

    public Path getNamespacePath() {
        return Paths.get(namespacePath);
    }

    public String getNamespaceKey() {
        return namespace;
    }

    public void writeJsonObject(Writable object){
        object.setFilePath(namespacePath + object.getFilePath());
        resourcePack.writeJsonObject(object);
    }

    public void createGenericFile(String filePath, String contents){
        resourcePack.createGenericFile(namespacePath + filePath, contents);
    }

    public boolean copyFileFromDisk(String copyPath, String  pastePath) {
        return resourcePack.copyFileFromDisk(copyPath, namespacePath + pastePath);
    }

    public boolean hasFile(String filePath){
        return resourcePack.hasFile(filePath);
    }

}
