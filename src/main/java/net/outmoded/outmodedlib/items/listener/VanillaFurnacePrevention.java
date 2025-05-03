package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.*;

public class VanillaFurnacePrevention implements Listener {

    @EventHandler
    public void ontst(FurnaceSmeltEvent event){
        if (ItemManager.getInstance().isCustomItemStack(event.getSource())){

            event.setCancelled(true);

            Furnace state = (Furnace) event.getBlock().getState();
            Container container = (Container) state;
            FurnaceInventory inventory = (FurnaceInventory) container.getInventory();
            inventory.setFuel(null);
        }

    }


    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareSmithingEvent(FurnaceBurnEvent event) {
        ItemStack fuel = event.getFuel();

        if (ItemManager.getInstance().isCustomItemStack(fuel)){
            event.setCancelled(true);

        }

        Furnace state = (Furnace) event.getBlock().getState();
        Container container = (Container) state;
        FurnaceInventory inventory = (FurnaceInventory) container.getInventory();

        if (ItemManager.getInstance().isCustomItemStack(inventory.getItem(0))){
            event.setCancelled(true);

        }
    }

}