package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Campfire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LoomInventory;

public class VanillaLoomPrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW) // needs doing in 1.21.5
    public void onInventoryClickEvent(InventoryClickEvent event) {



//        if (event.getInventory().getType() == InventoryType.LOOM){
//            LoomInventory loomInventory = (LoomInventory) event.getClickedInventory();
//            ItemStack banner = event.getClickedInventory().getItem(0); // slot 0 = banner
//            ItemStack dye = event.getClickedInventory().getItem(1); // slot 1 = dye
//            ItemStack pattern = event.getClickedInventory().getItem(2); // slot 2 = pattern
//
//            if (event.getSlot() == 3){
//                if (ItemManager.getInstance().isCustomItemStack(banner) || ItemManager.getInstance().isCustomItemStack(dye) || ItemManager.getInstance().isCustomItemStack(pattern)) {
//
//
//                }
//
//            }
//
//
//        }
    }

}