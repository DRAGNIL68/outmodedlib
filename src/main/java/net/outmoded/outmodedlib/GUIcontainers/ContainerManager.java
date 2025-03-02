package net.outmoded.outmodedlib.GUIcontainers;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ContainerManager {
    private final static Map<Inventory, CustomContainer> loadedContainers = new HashMap<>();
    private ContainerManager(){

    }

    public static void registerHandledContainer(Inventory container, CustomContainer customContainer){
        loadedContainers.put(container, customContainer);

    }

    public static void unregisterHandledContainer(Inventory container) {
        loadedContainers.remove(container);
    }

    public static void handleClick(InventoryClickEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());

        if (customContainer != null) {
            boolean[] disabledSlots = customContainer.getDisabledSlots();

            if (disabledSlots[event.getSlot()] == true){ // jumps to the correct slot so no need to loop
                event.setCancelled(true);
                return;
            }
            customContainer.onClick(event);

        }
    }

    public static void handleOpen(InventoryOpenEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onOpen(event);
        }
    }

    public static void handleClose(InventoryCloseEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onClose(event);
        }
        unregisterHandledContainer(event.getInventory());
    }

    public static void handleDrag(InventoryDragEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onDrag(event);
        }
    }

}
