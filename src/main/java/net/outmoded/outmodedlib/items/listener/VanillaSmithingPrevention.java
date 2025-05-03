package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

public class VanillaSmithingPrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareSmithingEvent(PrepareSmithingEvent event) {
        ItemStack template = event.getInventory().getInputTemplate();
        ItemStack equipment = event.getInventory().getInputEquipment();
        ItemStack mineral = event.getInventory().getInputMineral();


        if (template == null || equipment == null || mineral == null) {
            return;
        }

        if (event.getResult() != null) {
            if (ItemManager.getInstance().isCustomItemStack(template) || ItemManager.getInstance().isCustomItemStack(equipment) || ItemManager.getInstance().isCustomItemStack(mineral)) {
                event.setResult(null);

            }
        }
    }

}