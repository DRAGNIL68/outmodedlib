package net.outmoded.outmodedlib;

import net.outmoded.outmodedlib.packer.Namespace;
import net.outmoded.outmodedlib.packer.ResourcePack;
import net.outmoded.outmodedlib.packer.UnicodeRegister;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemModelDefinition;
import net.outmoded.outmodedlib.packer.jsonObjects.McMeta;

public class PackGenTest {
    public static void runPack() {
        ResourcePack resourcePack = new ResourcePack("test_pack"); // <- create new resourcePack
        resourcePack.writeJsonObject(new McMeta("frogs", 42)); // <- creates pack.mcmeta

        //Namespace test_pack = new Namespace("test_pack", resourcePack); // <- creates new namespace

        //test_pack.createGenericFile("frog.txt", "ogg"); // generic file

        //test_pack.copyFileFromDisk("plugins/packtest/katana.json", "models/item/katana.json"); // copy file from the disk to th texture pack
        //animatedSkript.copyFileFromDisk("plugins/packtest/katana.png", "textures/item/katana.png");
        //animatedSkript.writeJsonObject(new ItemModelDefinition("animated-skript", "katana"));

        UnicodeRegister unicodeRegister = new UnicodeRegister();
        unicodeRegister.addUnicodeChar(UnicodeRegister.unicodeType.BITMAP, 2, 2, "test_pack:font/frog.png");

        resourcePack.writeJsonObject(unicodeRegister);
        //resourcePack.base64ToTexture("assets/frog.png", "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAAORJREFUeF7t1sERwzAMA0GputSc6pRxDfcQndn8YdMEgctea5317t8u4z9iCygbHKB1AcUEEdABShAFYLC06AAtDBYTYBAGYRAGYbC06AAtDBYTYBAGYRAGYbC06AAtDBYTYBAGYRAGYbC06AAtDBYTJmAwOVg+/tFawID/AS6gnnHRi4AIlPv5A+3VApqwPwuY4MLNGVzAze1PeLcLmOBCmeGc8yn611+ABbz5ArJ7e3/zM0p+qjYPbwEuQARyjGqOiz4PrwN0gA7IMSoZrto8vA7QATogx6jmuOjz8DpAB+QO+AGBF6RNi0nBcwAAAABJRU5ErkJggg==");
        resourcePack.build("plugins/" + resourcePack.getName() + ".zip"); // <- generates zip
    }

}
