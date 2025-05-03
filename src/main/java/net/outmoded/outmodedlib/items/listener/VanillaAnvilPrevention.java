package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class VanillaAnvilPrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW) // set low to allow other plugins to still make custom anvil recipe
    public void onPrepareAnvilEvent(PrepareAnvilEvent event) { // this code is dead simple because anvils have not recipes
        ItemStack itemStack1 = event.getInventory().getFirstItem();
        ItemStack itemStack2 = event.getInventory().getSecondItem();

        if (itemStack1 == null || itemStack2 == null) {
            return;
        }

        if (event.getResult() != null) {
            if (ItemManager.getInstance().isCustomItemStack(itemStack1) || ItemManager.getInstance().isCustomItemStack(itemStack2)) {
                if (itemStack2.getType() == Material.ENCHANTED_BOOK){ // this line is here to allow custom items that can be chanted to be enchanted
                    return;
                }
                event.setResult(null);

            }
        }
    }

}