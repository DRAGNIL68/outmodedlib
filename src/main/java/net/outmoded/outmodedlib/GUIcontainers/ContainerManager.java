package net.outmoded.outmodedlib.GUIcontainers;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link ContainerManager} manages all custom guis and handles events etc
 */
public class ContainerManager {
    private final  Map<Inventory, CustomContainer> loadedContainers = new HashMap<>();
    private static ContainerManager containerManagerInstance;
    private ContainerManager(){

    }

    public static ContainerManager getInstance() {
        if (containerManagerInstance == null) {
            containerManagerInstance = new ContainerManager();
        }
        return containerManagerInstance;
    }

    public void registerHandledContainer(Inventory container, CustomContainer customContainer){
        loadedContainers.put(container, customContainer);

    }

    public void unregisterHandledContainer(Inventory container) {
        loadedContainers.remove(container);
    }

    public void handleClick(InventoryClickEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());

        if (customContainer != null) {

//            if (event.getClickedInventory() != event.getView().getTopInventory()){
//                return;
//
//            }
            boolean[] disabledSlots = customContainer.getDisabledSlots();


            if (event.getSlot() != -999){

                if (disabledSlots[event.getSlot()]){ // jumps to the correct slot so no need to loop
                    event.setCancelled(true);
                }

            }


            customContainer.onClick(event);



        }
    }

    public void handleOpen(InventoryOpenEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onOpen(event);
        }
    }

    public void handleClose(InventoryCloseEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onClose(event);
        }
        unregisterHandledContainer(event.getInventory());
    }

    public void handleDrag(InventoryDragEvent event){
        CustomContainer customContainer = loadedContainers.get(event.getInventory());
        if (customContainer != null) {
            customContainer.onDrag(event);
        }
    }

}
