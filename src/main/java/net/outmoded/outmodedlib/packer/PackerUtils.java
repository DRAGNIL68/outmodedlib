package net.outmoded.outmodedlib.packer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.outmoded.outmodedlib.Outmodedlib;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public abstract class PackerUtils {



    public static String getOffsetValue(Integer offset){

        char[] positiveOffsets = new char[]{0xE001, 0xE002, 0xE003, 0xE004, 0xE005, 0xE006, 0xE007, 0xE008, 0xE009, 0xE010, 0xE011, 0xE012, 0xE013, 0xE014, 0xE015, 0xE016, 0xE017};
        char[] negativeOffsets = new char[]{0xE018, 0xE019, 0xE020, 0xE021, 0xE022, 0xE023, 0xE024, 0xE025, 0xE026, 0xE027, 0xE028, 0xE029, 0xE030, 0xE031, 0xE032, 0xE033, 0xE034};
        String finalOffset = "";

        if (offset >= 1){


            if (offset / 10 != 0){ // checks if it needs to add 10 spacing to it
                int remainder = offset % 10;
                int divided = offset / 10;


                for (int i = 0; i < divided; i++){
                    finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[10]));

                }

                if (remainder != 0){
                    for (int i = 0; i < remainder; i++){
                        finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[1]));

                    }

                }

            }
            else { // if 1- spacing is not possible just use 1 spacing
                for (int i = 0; i < offset; i++){
                    finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[1]));

                }


            }


        }
        else if (offset <= -1){


            if (Math.abs(offset) / 10 != 0){ // checks if it needs to add 10 spacing to it
                int remainder = Math.abs(offset) % 10;
                int divided = Math.abs(offset) / 10;


                for (int i = 0; i < divided; i++){
                    finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[10]));

                }

                if (remainder != 0){
                    for (int i = 0; i < remainder; i++){
                        finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[1]));

                    }

                }

            }
            else { // if 1- spacing is not possible just use 1 spacing
                for (int i = 0; i < Math.abs(offset); i++){
                    finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[1]));

                }


            }


        }
        return finalOffset;
    }

    public static Component getOffset(int offset){
        return MiniMessage.miniMessage().deserialize("<font:_outmodedlib:offset_font>"+getOffsetValue(offset)+"</font>");
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
