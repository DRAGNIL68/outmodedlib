package net.outmoded.outmodedlib.packer;

import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.items.ItemManager;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemModelDefinition;
import net.outmoded.outmodedlib.packer.jsonObjects.models.GeneratedItemModel;
import org.bukkit.Material;

import java.io.File;

public class InternalContent {

    public static void addInternalPackContent(ResourcePack resourcePack){

        File file = new File(Outmodedlib.getInstance().getDataFolder(), "pack/invisible.png");

        resourcePack.copyFileFromDisk(file.toPath(), "assets/outmodedlib/textures/items/invisible.png");

        GeneratedItemModel generatedItemModel = new GeneratedItemModel("outmodedlib:items/invisible", GeneratedItemModel.modelType.generated, "outmodedlib:invisible");
        ItemModelDefinition itemModelDefinition = new ItemModelDefinition("outmodedlib:invisible", "outmodedlib:invisible" );

        resourcePack.writeJsonObject(generatedItemModel);
        resourcePack.writeJsonObject(itemModelDefinition);
    }
    public static void registerInternalCustomContent(){
        CustomItemStack customItemStack = new CustomItemStack(Material.PAPER, "outmodedlib:inviable_placeholder");
        customItemStack.setModel("outmodedlib:invisible");
        customItemStack.asItemStack().getItemMeta().setHideTooltip(true);
        customItemStack.setName("");
        ItemManager.registerCustomItemStack(customItemStack);

    }



}
