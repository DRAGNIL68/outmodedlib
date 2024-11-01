package net.outmoded.outmodedlib.outmodedlibGUIContainer;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ContainerManager {
    private final Map<Inventory, ContainerHandler> activeContainers = new HashMap<>();

    public void registerHandledContainer(Inventory container, ContainerHandler handler){
        this.activeContainers.put(container, handler);

    }

    public void unregisterHandledContainer(Inventory container) {
        this.activeContainers.remove(container);
    }

    public void handleClick(InventoryClickEvent event){
        ContainerHandler handler = this.activeContainers.get(event.getInventory());
        if (handler != null) {
            handler.onClick(event);
        }
    }

    public void handleOpen(InventoryOpenEvent event){
        ContainerHandler handler = this.activeContainers.get(event.getInventory());
        if (handler != null) {
            handler.onOpen(event);
        }
    }

    public void handleClose(InventoryCloseEvent event){
        ContainerHandler handler = this.activeContainers.get(event.getInventory());
        if (handler != null) {
            handler.onClose(event);
        }
        unregisterHandledContainer(event.getInventory());
    }

    public void handleDrag(InventoryDragEvent event){
        ContainerHandler handler = this.activeContainers.get(event.getInventory());
        if (handler != null) {
            handler.onDrag(event);
        }
    }

}
