package net.outmoded.outmodedlib.outmodedlibGUIContainer;


import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface ContainerHandler {
    void register(); // this is where you put startup info like inv size, name, etc...
    void onClick(InventoryClickEvent event);

    void onOpen(InventoryOpenEvent event);

    void onClose(InventoryCloseEvent event);

    void onDrag(InventoryDragEvent event);
}
