package net.outmoded.outmodedlib.items;

import com.google.common.collect.Multimap;
import de.tr7zw.changeme.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import static net.outmoded.outmodedlib.packer.PackerUtils.splitNamespaceId;

public class CustomItemStack {
    private final ItemStack itemStack; // vanilla item used
    private final String namespaceId; // I.e. test:cool_item


    private String model; // test_namespace:cool_model

    public CustomItemStack(Material material, String namespaceId) {
        itemStack = new ItemStack(material);
        itemStack.getItemMeta();
        if (namespaceId.indexOf(":") != namespaceId.lastIndexOf(":")){
            throw new RuntimeException("Cannot use ':' in item namespaceId except for separating namespace and id");
        }
        this.namespaceId = namespaceId;

        NBT.modify(itemStack, nbt -> {
            nbt.setString("outmodedlib_namespaceId", namespaceId);
        });
        ItemMeta meta = itemStack.getItemMeta();
        meta.setAttributeModifiers(null);
    }

    public void setName(String itemName){
        final Component component = MiniMessage.miniMessage().deserialize(itemName);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.itemName(component);
        itemStack.setItemMeta(itemMeta);
    }

    public Component getName(){
        if (itemStack.getItemMeta().hasDisplayName()){
            return itemStack.getItemMeta().displayName();

        }

        return null;
    }

    public void setDurability(int durablity){
        ItemMeta meta = itemStack.getItemMeta();
        Damageable damageable = (Damageable) meta;
        damageable.setDamage(durablity);
        itemStack.setItemMeta(damageable);
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }
    // setModel(namespace_cool, )
    public void setModel(String namespacedModelDefPath){ // TODO: merge namespace with path
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

    public void setAttribute(Attribute attribute,AttributeModifier.Operation operation, Double value){
        ItemMeta meta = itemStack.getItemMeta();
        NamespacedKey key = new NamespacedKey("outmodedlib", "attack_speed");
        Multimap<Attribute, AttributeModifier> modifiers = meta.getAttributeModifiers();
        AttributeModifier attributeModifier = new AttributeModifier(key, value, operation);
        meta.addAttributeModifier(attribute, attributeModifier);

        if (modifiers != null){


            for (AttributeModifier modifier : modifiers.get(attribute)) {
                meta.addAttributeModifier(attribute, attributeModifier);
            }
        }
        itemStack.setItemMeta(meta);
    }

}