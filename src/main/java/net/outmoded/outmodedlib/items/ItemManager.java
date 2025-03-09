package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.items.events.RegisteredItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private final static Map<String, CustomItemStack> itemRegistry = new HashMap<>(); // probably a better idea for this to store a hashmap per namespace :TODO do this <--



    public static void registerCustomItemStack(CustomItemStack customItemStack){
            if (!itemRegistry.containsKey(customItemStack.getNamespaceId())) {
                RegisteredItemEvent event = new RegisteredItemEvent(customItemStack.getNamespaceId(), customItemStack);
                Bukkit.getPluginManager().callEvent(event);

                if (!event.isCancelled()){
                    itemRegistry.put(customItemStack.getNamespaceId() , customItemStack.clone());
                }


            }
            else{
                throw new RuntimeException("Item already registered with namespaceId " + customItemStack.getNamespaceId());
            }





    }

    public static void unregisterCustomItemStack(String namespaceId){
        CustomItemStack customItemStack = ItemManager.getCustomItemStack(namespaceId);
        RegisteredItemEvent event = new RegisteredItemEvent(customItemStack.getNamespaceId(), customItemStack);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            itemRegistry.remove(namespaceId);
        }


    }

    public static CustomItemStack getCustomItemStack(String namespaceId){
        if (itemRegistry.containsKey(namespaceId)){
            return itemRegistry.get(namespaceId).clone();
        }
        return null;

    }

    public static boolean customItemStackExists(String namespaceId){
        return itemRegistry.containsKey(namespaceId);
    }

    public static boolean isCustomItemStack(ItemStack itemStack){
        try {
            String namespaceId = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib_namespaceId"));
            if (namespaceId == null)
                return false;

            if (customItemStackExists(namespaceId))
                return true;

            else
                return false;

        }
        catch (Exception e) {
            return false;


        }

    }

    public static String getCustomItemStackNamespaceId(ItemStack itemStack) {
        try {
            String namespaceId = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib_namespaceId"));
            if (namespaceId == null) {
                return null;
            }
            return namespaceId;

        } catch (Exception e) {
            return null;


        }
    }

    public static CustomItemStack convertToCustomItemStack(ItemStack itemStack){
        if (!ItemManager.isCustomItemStack(itemStack)){
            String namespaceId = NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib_namespaceId"));

            CustomItemStack customItemStack = new CustomItemStack(itemStack.getType(), namespaceId);

            customItemStack.asItemStack().setItemMeta(itemStack.getItemMeta());

            return customItemStack;
        }
        return null;
    }
}
