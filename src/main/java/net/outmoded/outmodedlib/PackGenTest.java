package net.outmoded.outmodedlib;

import net.outmoded.outmodedlib.packer.ResourcePack;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeModel;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeSelect;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties.ModelModelTypeTintProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.tintsProperties.TintConstant;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext.DisplayContextCase;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.DisplayContext.DisplayContext;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.selectProperties.SelectModelTypeProperties;
import net.outmoded.outmodedlib.packer.jsonObjects.McMeta;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.UnicodeFileRegister;
import net.outmoded.outmodedlib.packer.jsonObjects.unicode.providers.BitmapUnicodeProvider;

public class PackGenTest { // an example for generating a pack
    public static void runPack() {
        ResourcePack resourcePack = new ResourcePack("test_pack"); // <- create new resourcePack
        resourcePack.writeJsonObject(new McMeta("frogs", 42)); // <- creates pack.mcmeta
        resourcePack.setDebugMode(true);

        ItemModelDefinitionTypeSelect modelDefinitionTypeSelect = new ItemModelDefinitionTypeSelect("test_pack:test_select", SelectModelTypeProperties.DISPLAY_CONTEXT, new DisplayContext());
        ItemModelDefinitionTypeModel itemModelDefinitionTypeModel = new ItemModelDefinitionTypeModel("mypvpz:katana", "e");

        itemModelDefinitionTypeModel.addTint(ModelModelTypeTintProperties.CONSTANT, TintConstant.tintConstant().setValue(1).build());

        modelDefinitionTypeSelect.addCase(new DisplayContextCase(new DisplayContextCase.When[]{DisplayContextCase.When.FIXED, DisplayContextCase.When.GUI}, itemModelDefinitionTypeModel));

        modelDefinitionTypeSelect.
                addCase(new DisplayContextCase
                        (new DisplayContextCase.When[]{DisplayContextCase.When.FIXED, DisplayContextCase.When.GUI},
                        itemModelDefinitionTypeModel)
                );


        resourcePack.copyFileFromDisk("plugins/packtest/font.png", "assets/test_pack/textures/font.png");

        //UnicodeFileRegister unicodeFileRegister = new UnicodeFileRegister(0, "pack_test:test_font");

//        unicodeFileRegister.addUnicodeCharSpriteSheet(
//                UnicodeType.BITMAP,
//                2,
//                2,
//                "test_pack:font",
//                new TextureSize(163, 7),
//                new TextureSize(6, 7)
//        );

        //resourcePack.writeJsonObject(unicodeFileRegister);

        UnicodeFileRegister unicodeFileRegister = new UnicodeFileRegister("namespace:test_font");
        unicodeFileRegister.addUnicodeProvider(
                new BitmapUnicodeProvider
                ('a',
                        "namespace:fonts/texture.png",
                        3,
                        3
                ));

        resourcePack.writeJsonObject(unicodeFileRegister);

        resourcePack.writeJsonObject(modelDefinitionTypeSelect);
        //resourcePack.base64ToTexture("assets/frog.png", "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAAORJREFUeF7t1sERwzAMA0GputSc6pRxDfcQndn8YdMEgctea5317t8u4z9iCygbHKB1AcUEEdABShAFYLC06AAtDBYTYBAGYRAGYbC06AAtDBYTYBAGYRAGYbC06AAtDBYTYBAGYRAGYbC06AAtDBYTJmAwOVg+/tFawID/AS6gnnHRi4AIlPv5A+3VApqwPwuY4MLNGVzAze1PeLcLmOBCmeGc8yn611+ABbz5ArJ7e3/zM0p+qjYPbwEuQARyjGqOiz4PrwN0gA7IMSoZrto8vA7QATogx6jmuOjz8DpAB+QO+AGBF6RNi0nBcwAAAABJRU5ErkJggg==");
        resourcePack.build("plugins/" + resourcePack.getName() + ".zip"); // <- generates zip

    }

}
