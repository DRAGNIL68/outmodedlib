package net.outmoded.outmodedlib;

import net.outmoded.outmodedlib.packer.ResourcePack;

public class InternalPackContent {


    public static void addContent(ResourcePack resourcePack){
        resourcePack.copyFileFromResources(Outmodedlib.getInstance().getResource("pack/invisible1x1.png"), "assets/_outmodedlib/textures/invisible1x1.png");
        resourcePack.copyFileFromResources(Outmodedlib.getInstance().getResource("pack/offset_font.json"), "assets/_outmodedlib/font/offset_font.json");


    }

}
