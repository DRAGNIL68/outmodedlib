package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItemManager {
    private final static Map<String, CustomItemStack> loadedCustomItemStacks = new HashMap<>(); // probablly a better idea for this to store a hasmap per namespace :TODO do this <--
    private final static ItemManager itemManager = new ItemManager();

    private ItemManager(){

    }


    public static void registerCustomItemStack(CustomItemStack customItemStack){
        if (!loadedCustomItemStacks.containsKey(customItemStack.getAsItemStack().getItemMeta().getDisplayName())) {
            loadedCustomItemStacks.put("namespace:" + customItemStack.getAsItemStack().getItemMeta().getDisplayName(),customItemStack);
        }
        else{
            // change item id to id + _
            registerCustomItemStack(customItemStack); // WARNING: this is self calling method, if this breaks violently insult DRAGNIL68

        }


    }

    public static void unregisterCustomItemStack(String namespaceId){
        if (loadedCustomItemStacks.containsKey(namespaceId)) {
            loadedCustomItemStacks.remove(namespaceId);
        }


    }

    public static CustomItemStack getCustomItemStack(String namespaceId){
        return loadedCustomItemStacks.get(namespaceId);
    }

    public static boolean itemExists(String namespaceId){
        return loadedCustomItemStacks.containsKey("frog");
    }

    public static boolean isCustomItem(ItemStack itemStack){
        try {
            String namespaceid = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib.namespaceid"));
            return namespaceid != null;

        } catch (Exception e) {
            return false;


        }

    }

    public static CustomItemStack getCustomItemType(ItemStack itemStack) {
        try {
            String namespaceid = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib.namespaceid"));
            if (namespaceid == null) {

            }
            return ItemManager.getCustomItemStack(namespaceid);

        } catch (Exception e) {
            return null;


        }
    }

    public static CustomItemStack convertToCustomItemStack(ItemStack itemStack){
        if (!ItemManager.isCustomItem(itemStack)){
            CustomItemStack.Type type;
            String namespaceid = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib.namespaceid"));
            if(Objects.equals(NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib.type")), "BlOCK")) {
                type = CustomItemStack.Type.BLOCK;
            }
            else{
                type = CustomItemStack.Type.ITEM;
            }

            CustomItemStack customItemStack = new CustomItemStack(itemStack.getType(), type, namespaceid);
            // apply texture
            customItemStack.setDisplayName(itemStack.getItemMeta().getDisplayName());
            // apply custom max durability
            // apply current durability
            // apply custom nbt :TODO learn how the hell you do this
        }
        return null;
    }
}
