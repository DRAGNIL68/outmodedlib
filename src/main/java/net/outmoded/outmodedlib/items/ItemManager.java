package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class ItemManager {
    private final static Map<String, CustomItemStack> loadedCustomItemStacks = new HashMap<>(); // probablly a better idea for this to store a hashmap per namespace :TODO do this <--
    private final static ItemManager itemManager = new ItemManager();

    private ItemManager(){


    }


    public static void registerCustomItemStack(CustomItemStack customItemStack){
        if (!loadedCustomItemStacks.containsKey(customItemStack.getNamespaceId())) {
            loadedCustomItemStacks.put(customItemStack.getNamespaceId() ,customItemStack);
        }
        else{
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Item already registered with namespaceId " + customItemStack.getNamespaceId());

        }


    }

    public static void unregisterCustomItemStack(String namespaceId){
        loadedCustomItemStacks.remove(namespaceId);

    }

    public static CustomItemStack getCustomItemStack(String namespaceId){
        CustomItemStack customItemStack = loadedCustomItemStacks.get(namespaceId);
        return customItemStack.clone();
    }

    public static boolean customItemStackExists(String namespaceId){
        return loadedCustomItemStacks.containsKey(namespaceId);
    }

    public static boolean isCustomItemStack(ItemStack itemStack){
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
        if (!ItemManager.isCustomItemStack(itemStack)){
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
            customItemStack.getAsItemStack().setItemMeta(itemStack.getItemMeta());
            return customItemStack;
        }
        return null;
    }
}
