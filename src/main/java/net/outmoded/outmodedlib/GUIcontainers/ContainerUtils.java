package net.outmoded.outmodedlib.GUIcontainers;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ContainerUtils {

    public static void setAllowedClickSlots(int[] allowedSlots, InventoryClickEvent event){

        int eventSlot =  event.getSlot();
        Inventory eventInventory = event.getClickedInventory();

        for (int slot : allowedSlots) {
            if (slot > 89) {
                break;
            }
            if (slot == allowedSlots[slot]){
                event.setCancelled(true);
            }
            else {
                ItemStack disabledSlotItem = new ItemStack(Material.BARRIER);
                eventInventory.setItem(1, disabledSlotItem);
                event.setCancelled(true);
            }
        }

    }
}

