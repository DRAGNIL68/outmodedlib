package net.outmoded.outmodedlib.GUIcontainers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ContainerListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        ContainerManager.handleClick(event);

    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        ContainerManager.handleOpen(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        ContainerManager.handleClose(event);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        ContainerManager.handleDrag(event);
    }
}
