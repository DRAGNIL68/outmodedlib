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
        ContainerManager.getInstance().handleClick(event);

    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        ContainerManager.getInstance().handleOpen(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        ContainerManager.getInstance().handleClose(event);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        ContainerManager.getInstance().handleDrag(event);
    }
}
