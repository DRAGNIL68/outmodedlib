package net.outmoded.outmodedlib.GUIcontainers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ContainerManager {
    private final static Map<Inventory, ContainerHandler> loadedContainers = new HashMap<>();
    private final static ContainerManager containerManager = new ContainerManager();
    private ContainerManager(){

    }

    public static void registerHandledContainer(Inventory container, ContainerHandler handler){
        loadedContainers.put(container, handler);

    }

    public static void unregisterHandledContainer(Inventory container) {
        loadedContainers.remove(container);
    }

    public static void handleClick(InventoryClickEvent event){
        ContainerHandler handler = loadedContainers.get(event.getInventory());
        if (handler != null) {
            handler.onClick(event);
        }
    }

    public static void handleOpen(InventoryOpenEvent event){
        ContainerHandler handler = loadedContainers.get(event.getInventory());
        if (handler != null) {
            handler.onOpen(event);
        }
    }

    public static void handleClose(InventoryCloseEvent event){
        ContainerHandler handler = loadedContainers.get(event.getInventory());
        if (handler != null) {
            handler.onClose(event);
        }
        unregisterHandledContainer(event.getInventory());
    }

    public static void handleDrag(InventoryDragEvent event){
        ContainerHandler handler = loadedContainers.get(event.getInventory());
        if (handler != null) {
            handler.onDrag(event);
        }
    }

    public static ContainerManager getInstance(){
        return containerManager;
    }

}
