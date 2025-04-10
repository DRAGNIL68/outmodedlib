package net.outmoded.outmodedlib.packer;


import net.outmoded.outmodedlib.Outmodedlib;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.reloadWhitelist;

public class ZipFileUtil {

    ZipOutputStream zipOutputStream;
    Path zipFilePath;
    FileSystem fileSystem;
    ResourcePack resourcePack;

    ZipFileUtil(String zipOutputPath, ResourcePack resourcePack) throws IOException {
        this.fileSystem = resourcePack.getFileSystem();
        this.resourcePack = resourcePack;
        Path zipPath = Paths.get(zipOutputPath);
        if (!Files.exists(zipPath.getParent())) {
            Files.createDirectories(zipPath);
        }

        Files.deleteIfExists(zipPath);

        zipFilePath = Files.createFile(Paths.get(zipOutputPath));
        zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFilePath));

        if (resourcePack.getDebugMode()) {
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Starting Build ==============================");
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Resource Pack Name: "+ChatColor.GOLD+resourcePack.getName()+ " : " + zipOutputPath);
    }   }

    public void addToZip(String pathToGetZipped){
        Path sourceDirPath = fileSystem.getPath(pathToGetZipped); // gets the path that was passed as a string

        try(Stream<Path> paths = Files.walk(sourceDirPath)) { // basically a loop
            paths
                    .filter(path -> !Files.isDirectory(path)) // checks if it is a file or path
                    .forEach(path -> {
                        try {
                            ZipEntry zipEntry = new ZipEntry(path.toString());

                            if (resourcePack.getDebugMode())
                                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "File Written To Pack: "+ChatColor.GOLD+path.getFileName()+ " : " + path);

                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(path, zipOutputStream);
                            zipOutputStream.closeEntry();


                        }
                        catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void endZip(){
        try {

            if (resourcePack.getDebugMode())
                getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "End Of Build ==============================");

            zipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
