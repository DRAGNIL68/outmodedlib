package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class VanillaAnvilPrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
        ItemStack itemStack1 = event.getInventory().getFirstItem();
        ItemStack itemStack2 = event.getInventory().getSecondItem();

        if (itemStack1 == null || itemStack2 == null) {
            return;
        }

        if (event.getResult() != null) {
            if (ItemManager.getInstance().isCustomItemStack(itemStack1) || ItemManager.getInstance().isCustomItemStack(itemStack2)) {
                if (itemStack2.getType() == Material.ENCHANTED_BOOK){
                    return;
                }
                event.setResult(null);

            }
        }
    }

}