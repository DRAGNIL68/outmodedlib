package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.events.RegisteredItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private final Map<String, CustomItemStack> itemRegistry = new HashMap<>(); // probably a better idea for this to store a hashmap per namespace :TODO do this <--
    private static ItemManager itemManagerInstance;


    private ItemManager(){

    }

    public static ItemManager getInstance() {
        if (itemManagerInstance == null) {
            itemManagerInstance = new ItemManager();
        }
        return itemManagerInstance;
    }


    public void registerCustomItemStack(CustomItemStack customItemStack){
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

    public void unregisterCustomItemStack(String namespaceId){
        CustomItemStack customItemStack = itemManagerInstance.getCustomItemStack(namespaceId);
        RegisteredItemEvent event = new RegisteredItemEvent(customItemStack.getNamespaceId(), customItemStack);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            itemRegistry.remove(namespaceId);
        }


    }

    public CustomItemStack getCustomItemStack(String namespaceId){
        if (itemRegistry.containsKey(namespaceId)){
            return itemRegistry.get(namespaceId).clone();
        }
        return null;

    }

    public boolean customItemStackExists(String namespaceId){
        return itemRegistry.containsKey(namespaceId);
    }

    public String[] getAllCustomItems(){
        return itemRegistry.keySet().toArray(String[]::new);

    }

    public boolean isCustomItemStack(ItemStack itemStack){
        if (itemStack == null)
            return false;

        NamespacedKey namespacedIdKey = new NamespacedKey(Outmodedlib.getInstance(), "namespacedId");
        String namespacedId = null;
        if (itemStack.getPersistentDataContainer().has(namespacedIdKey)){
            namespacedId = itemStack.getPersistentDataContainer().get(namespacedIdKey, PersistentDataType.STRING);

        }
        if (namespacedId == null)
            return false;

        if (customItemStackExists(namespacedId))
            return true;

        else
            return false;

    }

    public String getCustomItemStackNamespaceId(ItemStack itemStack) {

        NamespacedKey namespacedIdKey = new NamespacedKey(Outmodedlib.getInstance(), "namespacedId");

        String namespacedId = null;

        if (itemStack.getPersistentDataContainer().has(namespacedIdKey)){
            namespacedId = itemStack.getPersistentDataContainer().get(namespacedIdKey, PersistentDataType.STRING);

        }

        if (namespacedId == null) {
            return null;
        }
        return namespacedId;

    }

    public CustomItemStack convertToCustomItemStack(ItemStack itemStack){
        if (!itemManagerInstance.isCustomItemStack(itemStack)){
            String namespaceId = getCustomItemStackNamespaceId(itemStack);

            CustomItemStack customItemStack = new CustomItemStack(itemStack.getType(), namespaceId);

            customItemStack.asItemStack().setItemMeta(itemStack.getItemMeta());

            return customItemStack;
        }
        return null;
    }
}
