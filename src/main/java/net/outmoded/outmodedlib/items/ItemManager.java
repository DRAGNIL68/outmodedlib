package net.outmoded.outmodedlib.items;

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

    /**
     * lets you register a custom item
     * @param customItemStack
     */
    public void registerCustomItemStack(CustomItemStack customItemStack){
            if (!itemRegistry.containsKey(customItemStack.getNamespaceId())) {
                RegisteredItemEvent event = new RegisteredItemEvent(customItemStack.getNamespaceId(), customItemStack);
                Bukkit.getPluginManager().callEvent(event);

                if (!event.isCancelled()){
                    itemRegistry.put(customItemStack.getNamespaceId() , customItemStack.clone());
                }


            }
            else{
                Outmodedlib.getInstance().getLogger().warning(" Item already registered with namespaceId "+customItemStack.getNamespaceId());
            }





    }

    /**
     * lets you unregister a custom item
     * @param namespaceId
     */
    public void unregisterCustomItemStack(String namespaceId){
        CustomItemStack customItemStack = itemManagerInstance.getCustomItemStack(namespaceId);

        if (customItemStack == null){
            return;
        }

        RegisteredItemEvent event = new RegisteredItemEvent(customItemStack.getNamespaceId(), customItemStack);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            itemRegistry.remove(namespaceId);
        }


    }

    /**
     * get a custom item
     * @param namespaceId
     * @return
     */
    public CustomItemStack getCustomItemStack(String namespaceId){
        if (itemRegistry.containsKey(namespaceId)){
            return itemRegistry.get(namespaceId).clone();
        }
        return null;

    }

    /**
     * check if a {@link CustomItemStack} exists
     * @param namespaceId
     * @return
     */
    public boolean customItemStackExists(String namespaceId){
        return itemRegistry.containsKey(namespaceId);
    }

    public String[] getAllCustomItemStacksNamespacedIds(){
        return itemRegistry.keySet().toArray(String[]::new);

    }

    public CustomItemStack[] getAllCustomItemStacks(){
        return itemRegistry.values().toArray(CustomItemStack[]::new);

    }


    /**
     * checks if a {@link ItemStack} is a custom item, it uses the NamespacedId nbt tag
     * @param itemStack
     * @return
     */
    public boolean isCustomItemStack(ItemStack itemStack) {
        if (itemStack == null)
            return false;

        NamespacedKey namespacedIdKey = new NamespacedKey(Outmodedlib.getInstance(), "namespacedId");
        String namespacedId = null;
        if (itemStack.getPersistentDataContainer().has(namespacedIdKey)) {
            namespacedId = itemStack.getPersistentDataContainer().get(namespacedIdKey, PersistentDataType.STRING);

        }
        if (namespacedId == null)
            return false;

        if (customItemStackExists(namespacedId))
            return true;

        else
            return false;

    }

    /**
     * checks if a {@link ItemStack} is 100% an exact copy of a registered custom item
     * @param itemStack
     * @return
     */
    public boolean isExactlyCustomItemStack(ItemStack itemStack){
        if (!isCustomItemStack(itemStack))
            return false;

        if (itemStack.isSimilar(getCustomItemStack(ItemManager.getInstance().getCustomItemStackNamespaceId(itemStack)).asItemStack())){
            return true;

        }

        return false;
    }

    /**
     * get namespaced id of a custom item
     * @param itemStack
     * @return
     */
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

    /**
     * convert a custom item back into a {@link CustomItemStack}
     * @param itemStack
     * @return
     */
    public CustomItemStack convertToCustomItemStack(ItemStack itemStack){
        if (itemManagerInstance.isCustomItemStack(itemStack)){
            String namespaceId = getCustomItemStackNamespaceId(itemStack);

            CustomItemStack customItemStack = new CustomItemStack(itemStack.getType(), namespaceId);

            customItemStack.asItemStack().setItemMeta(itemStack.getItemMeta());

            return customItemStack;
        }
        return null;
    }
}
