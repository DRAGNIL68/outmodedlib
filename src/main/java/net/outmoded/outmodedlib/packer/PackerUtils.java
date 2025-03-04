package net.outmoded.outmodedlib.packer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public abstract class PackerUtils {



    public static String getNegativeOffsetCharFromInt(Integer offset){ // TODO: implement this. will probably use AmberWat's negative spacing pack
        return "offset";
    }
    public static char getUnicodeCharFromInt(int integer){
        return (char)integer;
    }

    public static String textureToBase64(String filePath){
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            return Base64.getEncoder().encodeToString(fileContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void base64ToTexture(String textureAsBase64, String path){
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(textureAsBase64);
            FileUtils.writeByteArrayToFile(new File(path), decodedBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String[] splitNamespaceId(String namespaceId){
        String id = namespaceId.substring(namespaceId.lastIndexOf(':') + 1);
        String namespace = namespaceId.replace(":" + id, "");

        return new String[]{namespace, id};


    }

}
