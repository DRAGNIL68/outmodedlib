package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Campfire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VanillaCampfirePrevention implements Listener {


    @EventHandler(priority = EventPriority.LOW) // low so plugins can still overwrite it
    public void onPlayerInteractEvent(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (event.getClickedBlock().getType() == Material.CAMPFIRE){
            Campfire campfire = (Campfire) event.getClickedBlock().getState();
            ItemStack itemStack = event.getItem();
            if (ItemManager.getInstance().isCustomItemStack(itemStack)){
                event.setCancelled(true);

            }


        }
    }

}