package net.outmoded.outmodedlib.items;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemStack {
    private final ItemStack item;
    private final String namespaceId;
    private final Type type;

    public CustomItemStack(Material material, Type type, String namespaceId) {
        item = new ItemStack(material);

        this.type = type;
        if (namespaceId.indexOf(":") != namespaceId.lastIndexOf(":")){
            throw new RuntimeException("Cannot use ':' in item namespaceId except for separating namespace and id");
        }
        this.namespaceId = namespaceId;

        NBT.modify(item, nbt -> {
            nbt.setString("outmodedlib.namespaceid", namespaceId);
            nbt.setString("outmodedlib.type", String.valueOf(type));

        });
    }

    public void setDisplayName(String name){
        item.getItemMeta().setDisplayName(name);
    }

    public void setDurability(int startDurablity){
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public void getDurability(int startDurablity){
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public void setMaxDurability(String namespaceId){
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public void getType(int startDurablity){
        // needs looking into see:https://github.com/PluginBugs/Issues-ItemsAdder/issues/3536
    }

    public ItemStack getAsItemStack(){
        return item;
    }

    public String getNamespaceId(){
        return NBT.get(item, nbt -> (String) nbt.getString("outmodedlib.namespaceId"));
    }

    enum Type{
        ITEM,
        BLOCK
    }

}
// ItemStack
// OutmodedlibItemSack
// CustomStack
// CustomItemStack