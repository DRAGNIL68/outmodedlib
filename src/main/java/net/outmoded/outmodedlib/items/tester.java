package net.outmoded.outmodedlib.items;

import org.bukkit.Material;

public class tester{


    public void test(){
        CustomItemStack customItem = new CustomItemStack(Material.ACACIA_BOAT, CustomItemStack.Type.ITEM, "tester:test_item");
        ItemManager.registerCustomItemStack(customItem);


    }
    //ItemManager.

}
