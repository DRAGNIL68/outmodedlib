package net.outmoded.outmodedlib.outmodedlibGUIContainer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ContainerListener implements Listener {
    private final ContainerManager containerManager; // active instance of the container manager


    public ContainerListener(ContainerManager containerManager) {
        this.containerManager = containerManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        this.containerManager.handleClick(event);

    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        this.containerManager.handleOpen(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        this.containerManager.handleClose(event);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        this.containerManager.handleDrag(event);
    }
}
