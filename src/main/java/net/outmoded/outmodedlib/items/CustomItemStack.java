package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemStack {
    private final ItemStack itemStack; // vanilla item used
    private final String namespaceId; // I.e. test:cool_item
    private final Type type; // if it can be placed or no not TODO: is this needed?

    private String model = "generated-16x"; // "generated-16x", "generated-32x" or "model path"
    private String texture = null; // "null = no texture" or "texture path"

    public CustomItemStack(Material material, Type type, String namespaceId) {
        itemStack = new ItemStack(material);
        itemStack.getItemMeta();
        this.type = type;
        if (namespaceId.indexOf(":") != namespaceId.lastIndexOf(":")){
            throw new RuntimeException("Cannot use ':' in item namespaceId except for separating namespace and id");
        }
        this.namespaceId = namespaceId;

        NBT.modify(itemStack, nbt -> {
            nbt.setString("outmodedlib.namespaceid", namespaceId);
            nbt.setString("outmodedlib.type", String.valueOf(type));

        });
    }

    public void setDisplayName(String name){
        itemStack.getItemMeta().setDisplayName(name);
    }

    public String getDisplayName(){
        return itemStack.getItemMeta().getDisplayName();
    }

    public void setDurability(int durablity){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(durablity);
        itemStack.setItemMeta(damageable);
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public void setTexture(String path){
        texture = path;
    }

    public void setModel(String path){
        model = path;
    }

    public String getTexture() {
        return texture;

    }
    public String getModel(){
        return model;
    }


    public void setMaxDurability(int maxDurabilty){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(maxDurabilty);
        itemStack.setItemMeta(damageable);
    }

    public Type getType(){
        return type;
    }

    public ItemStack getAsItemStack(){
        return itemStack;
    }

    public String getNamespaceId(){
        return NBT.get(itemStack, nbt -> (String) nbt.getString("outmodedlib.namespaceId"));
    }

    public int getDurability(){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        return damageable.getDamage();
    }

    public int getMaxDurability(){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        return damageable.getMaxDamage();
    }

    public CustomItemStack clone(){
        return ItemManager.convertToCustomItemStack(itemStack);
    }




    enum Type{ // this may or may not be completely useless
        ITEM,
        BLOCK
    }

}