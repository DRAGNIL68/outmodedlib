package net.outmoded.outmodedlib.packer;

import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.items.ItemManager;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeEmpty;
import net.outmoded.outmodedlib.packer.jsonObjects.ItemDefinitions.ItemModelDefinitionTypeModel;
import net.outmoded.outmodedlib.packer.jsonObjects.models.GeneratedItemModel;
import org.bukkit.Material;

import java.io.File;

public class InternalContent { // internal content added to all texture packs

    public static void addInternalPackContent(ResourcePack resourcePack){

        File file = new File(Outmodedlib.getInstance().getDataFolder(), "pack/invisible.png");

        resourcePack.copyFileFromDisk(file.toPath(), "assets/outmodedlib/textures/item/invisible.png");

        ItemModelDefinitionTypeEmpty itemModelDefinitionTypeEmpty = new ItemModelDefinitionTypeEmpty("outmodedlib:invisible" );

        resourcePack.writeJsonObject(itemModelDefinitionTypeEmpty);
    }
    public static void registerInternalCustomContent(){
        CustomItemStack customItemStack = new CustomItemStack(Material.PAPER, "outmodedlib:invisible_placeholder");
        customItemStack.setModel("outmodedlib:invisible");
        customItemStack.asItemStack().getItemMeta().setHideTooltip(true);
        customItemStack.setName("");
        ItemManager.getInstance().registerCustomItemStack(customItemStack);

    }



}
