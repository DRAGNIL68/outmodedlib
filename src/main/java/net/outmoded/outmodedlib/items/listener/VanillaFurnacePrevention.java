package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class VanillaFurnacePrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareSmithingEvent(FurnaceStartSmeltEvent event) {

        FurnaceRecipe recipe = (FurnaceRecipe) event.getRecipe();


        //ItemStack fuel = recipe.getInputChoice();
        //ItemStack input = event.getInventory().getInputEquipment();


        //if (input == null) {
            return;
        //}


    }

}