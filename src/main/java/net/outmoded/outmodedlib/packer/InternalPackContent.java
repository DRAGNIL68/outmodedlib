package net.outmoded.outmodedlib.packer;

import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.CustomItemStack;
import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;

public class InternalPackContent {


    public static void addContent(ResourcePack resourcePack){
        resourcePack.copyFileFromResources(Outmodedlib.getInstance().getResource("pack/invisible1x1.png"), "assets/_outmodedlib/textures/invisible1x1.png");
        resourcePack.copyFileFromResources(Outmodedlib.getInstance().getResource("pack/offset_font.json"), "assets/_outmodedlib/font/offset_font.json");


    }

    public static void registerInternalCustomContent(){
        CustomItemStack customItemStack = new CustomItemStack(Material.PAPER, "outmodedlib:invisible_placeholder");
        customItemStack.setModel("outmodedlib:invisible");
        customItemStack.asItemStack().getItemMeta().setHideTooltip(true);
        customItemStack.setName("");
        ItemManager.getInstance().registerCustomItemStack(customItemStack);

    }

}
