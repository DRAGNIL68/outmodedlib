package net.outmoded.outmodedlib.packer;

import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.items.ItemManager;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeModel;
import net.outmoded.outmodedlib.packer.jsonObjects.models.GeneratedItemModel;
import org.bukkit.Material;

import java.io.File;

public class InternalContent {

    public static void addInternalPackContent(ResourcePack resourcePack){

        File file = new File(Outmodedlib.getInstance().getDataFolder(), "pack/invisible.png");

        resourcePack.copyFileFromDisk(file.toPath(), "assets/outmodedlib/textures/item/invisible.png");

        GeneratedItemModel generatedItemModel = new GeneratedItemModel("outmodedlib:item/invisible", GeneratedItemModel.modelType.generated, "outmodedlib:invisible");
        ItemModelDefinitionTypeModel itemModelDefinitionTypeModel = new ItemModelDefinitionTypeModel("outmodedlib:invisible", "outmodedlib:invisible" );

        resourcePack.writeJsonObject(generatedItemModel);
        resourcePack.writeJsonObject(itemModelDefinitionTypeModel);
    }
    public static void registerInternalCustomContent(){
        CustomItemStack customItemStack = new CustomItemStack(Material.PAPER, "outmodedlib:invisible_placeholder");
        customItemStack.setModel("outmodedlib:invisible");
        customItemStack.asItemStack().getItemMeta().setHideTooltip(true);
        customItemStack.setName("");
        ItemManager.getInstance().registerCustomItemStack(customItemStack);

    }



}
