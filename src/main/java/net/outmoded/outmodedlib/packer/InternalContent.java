package net.outmoded.outmodedlib.packer;

import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.items.ItemManager;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemModelDefinition;
import net.outmoded.outmodedlib.packer.jsonObjects.models.GeneratedItemModel;
import org.bukkit.Material;

public class InternalContent {

    public static void addInternalPackContent(ResourcePack resourcePack){

        resourcePack.copyFileFromPluginResources("pack/invisible.png", "assets/outmodedlib/textures/items/invisible.png");

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
