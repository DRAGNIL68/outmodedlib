package net.outmoded.outmodedlib.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlayerInteracts(PlayerInteractEvent event){

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            ItemStack itemStack = event.getItem();
            //code

        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = event.getItem();
            ItemManager.isCustomItem(itemStack);
            //code

        }
    }

}
