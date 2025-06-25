package net.outmoded.outmodedlib.items;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.DataComponentValue;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.outmoded.outmodedlib.Outmodedlib;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class CustomItemStack {
    private final ItemStack itemStack; // vanilla item used
    private final String namespaceId; // I.e. test:cool_item


    private String model; // test_namespace:cool_model

    public CustomItemStack(Material material, String namespaceId) {
        itemStack = new ItemStack(material);

        for (DataComponentType dt : itemStack.getDataTypes()) {
            itemStack.unsetData(dt);
        }

        if (namespaceId.indexOf(":") != namespaceId.lastIndexOf(":")){
            throw new RuntimeException("Cannot use ':' in item namespaceId except for separating namespace and id");
        }
        this.namespaceId = namespaceId;

        NamespacedKey namespacedId = new NamespacedKey(Outmodedlib.getInstance(), "namespacedId");
        itemStack.editMeta(meta -> {
            meta.getPersistentDataContainer().set(namespacedId, PersistentDataType.STRING, namespaceId);
        });

//        ItemMeta meta = itemStack.getItemMeta();
//
//        Multimap<Attribute, AttributeModifier> modifiers = ArrayListMultimap.create();
//        meta.setAttributeModifiers(modifiers); // removes attributes of vanilla item
//        itemStack.setItemMeta(meta);
    }


    public void setStackSize(int stackSize){
        itemStack.setData(DataComponentTypes.MAX_STACK_SIZE, stackSize);

    }

    public void setRarity(ItemRarity rarity){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setRarity(rarity);
        itemStack.setItemMeta(itemMeta);
    }

    public int getStackSize(){
        return itemStack.getMaxStackSize();
    }

    public void setName(String itemName){
        final Component component = MiniMessage.miniMessage().deserialize(itemName);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.itemName(component);
        itemStack.setItemMeta(itemMeta);
    }

    public void setName(Component itemName){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.itemName(itemName);
        itemStack.setItemMeta(itemMeta);
    }

    public Component getName(){
        if (itemStack.getItemMeta().hasDisplayName()){
            return itemStack.getItemMeta().displayName();

        }

        return null;
    }

    public void setDamage(int damage){ // sets damage I.e. max durability is 500 if you use set damage(300) the items durability will be 200
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(damage);
        itemStack.setItemMeta(damageable);
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public void setModel(String namespacedModelDefPath){ // sets the custom model of this item I.e. setModel("namespace:test_item")
        String namespace = splitNamespaceId(namespacedModelDefPath)[0];
        String modelDefPath = splitNamespaceId(namespacedModelDefPath)[1];

        NamespacedKey modelKey = new NamespacedKey(namespace, modelDefPath);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setItemModel(modelKey);
        itemStack.setItemMeta(meta);
        model = namespacedModelDefPath;
    }

    public String getModel(){
        return model;
    }


    public void setMaxDurability(int maxDurability){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setMaxDamage(maxDurability);
        itemStack.setItemMeta(damageable);
    }



    public ItemStack asItemStack(){
        return itemStack;
    }

    public String getNamespaceId(){
        return namespaceId;
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

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        CustomItemStack customItemStack = new CustomItemStack(itemStack.getType(), namespaceId);

        ItemStack itemStack = customItemStack.asItemStack();

        itemStack.setItemMeta(itemMeta);

        return customItemStack;
    }


    public void setAttribute(Attribute attribute, AttributeModifier.Operation operation, EquipmentSlotGroup equipmentSlot, Double value){ // sets an attribute and removes the existing one
        ItemMeta meta = itemStack.getItemMeta(); // get item meta
        NamespacedKey key = new NamespacedKey(Outmodedlib.getInstance(), "outmodedlib");
        Multimap<Attribute, AttributeModifier> modifiers = meta.getAttributeModifiers();
        AttributeModifier attributeModifier = new AttributeModifier(key, value, operation, equipmentSlot);

        if (meta.hasAttributeModifiers()) {
            meta.removeAttributeModifier(attribute);
        }

        meta.addAttributeModifier(attribute, attributeModifier);
        itemStack.setItemMeta(meta);
    }


}