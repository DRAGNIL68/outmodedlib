package net.outmoded.outmodedlib.packer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.outmoded.outmodedlib.Outmodedlib;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public abstract class PackerUtils {


    // stolen from stackoverflow
    @ApiStatus.Internal
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @ApiStatus.Internal
    public static String getOffsetValue(double offsetValue){
        // 0 to 18
        char[] positiveOffsets = new char[]{0xE001, 0xE002, 0xE003, 0xE004, 0xE005, 0xE006, 0xE007, 0xE008, 0xE009, 0xE010, 0xE011, 0xE012, 0xE013, 0xE014, 0xE015, 0xE016, 0xE017, 0xE037, 0xE038};
        char[] negativeOffsets = new char[]{0xE018, 0xE019, 0xE020, 0xE021, 0xE022, 0xE023, 0xE024, 0xE025, 0xE026, 0xE027, 0xE028, 0xE029, 0xE030, 0xE031, 0xE032, 0xE033, 0xE034, 0xE035, 0xE036};
        String finalOffset = "";

        if (offsetValue >= 0.01) {
            // all numbers are multiplied by 100 to stop floating point errors
            // number must only go to 2 dp

            int offset = (int) (round(offsetValue, 2) * 100);

            // 10 * 100 = 1000
            int remainder = offset % 1000;
            int divided = (offset / 1000); // should be a number full number like 6

            // 1 * 100 = 100
            int remainder1 = remainder % 100;
            int divided1 = (remainder / 100);


            // 0.1 * 100 = 10
            int remainder2 = remainder1 % 10;
            int divided2 = (remainder1 / 10);

            // 0.01 * 100 = 1
            int divided3 = (remainder2);

            for (int i = 0; i < (int) divided; i++){finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[9]));}

            for (int i = 0; i < (int) divided1; i++){finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[1]));}

            for (int i = 0; i < (int) divided2; i++){finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[17]));}

            for (int i = 0; i < (int) divided3; i++){finalOffset = finalOffset.concat(String.valueOf(positiveOffsets[18]));}

        }
        if (offsetValue <= -0.01) {

            offsetValue = Math.abs(offsetValue);


            // all numbers are multiplied by 100 to stop floating point errors
            // number must only go to 2 dp

            int offset = (int) (round(offsetValue, 2) * 100);

            // 10 * 100 = 1000
            int remainder = offset % 1000;

            int divided = (offset / 1000) - (remainder / 1000); // should be a number full number like 6

            // 1 * 100 = 100
            int remainder1 = remainder % 100;
            int divided1 = (remainder / 100);

            // 0.1 * 100 = 10
            int remainder2 = remainder1 % 10;
            int divided2 = (remainder1 / 10);


            // 0.01 * 100 = 1
            int divided3 = (remainder2);


            for (int i = 0; i < divided; i++){
                finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[9]));

            }

            for (int i = 0; i < divided1; i++){finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[1]));}

            for (int i = 0; i < divided2; i++){finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[17]));}

            for (int i = 0; i < divided3; i++){finalOffset = finalOffset.concat(String.valueOf(negativeOffsets[18]));}

        }

        return finalOffset;
    }

    public static Component getOffset(Double offset){
        return MiniMessage.miniMessage().deserialize("<font:_outmodedlib:offset_font>"+getOffsetValue(offset)+"</font><font:default>");
    }

    public static UUID resourcePackIdToUuid(String resourcePackId){

        byte[] seedBytes = resourcePackId.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(seedBytes);
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

    /**
     * splits a string into its parts, example "test:cool_sword" = [0]"test" [1]"cool_sword"
     * @param namespaceId
     * @return
     */
    public static String[] splitNamespaceId(String namespaceId){
        String id = namespaceId.substring(namespaceId.lastIndexOf(':') + 1);
        String namespace = namespaceId.replace(":" + id, "");

        return new String[]{namespace, id};


    }

}
