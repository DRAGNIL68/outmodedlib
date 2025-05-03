package net.outmoded.outmodedlib.items.listener;

import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import io.papermc.paper.event.player.PlayerPurchaseEvent;
import io.papermc.paper.event.player.PlayerTradeEvent;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class VanillaTradePrevention implements Listener { // prevents custom items from being used in vanilla trades

    @EventHandler
    public void onPlayerPurchaseEvent(PlayerPurchaseEvent event) {

        List<ItemStack> ingredients = event.getTrade().getIngredients();
        InventoryView inventoryView = event.getPlayer().getOpenInventory();

        List<ItemStack> items = new ArrayList<>();
        items.add(inventoryView.getItem(0));
        items.add(inventoryView.getItem(1));

        // loops items in grid:
        //      loops ingredients in recipe
        //          checks if items match with recipe
        //              stops the trade if some of the items are custom

        // still allows custom trades with custom items
        // NOTE: why is the trade system so shit? it's not that old so why dose it work so badly




        for (ItemStack itemStack : items) {
            String namespace = ItemManager.getInstance().getCustomItemStackNamespaceId(itemStack);
            if (ItemManager.getInstance().isCustomItemStack(itemStack)){



                for (Iterator<ItemStack> iterator = ingredients.iterator(); iterator.hasNext(); ) {
                    ItemStack ingredient = iterator.next();
                    String ingredientNamespace = ItemManager.getInstance().getCustomItemStackNamespaceId(ingredient);

                    if (namespace.equals(ingredientNamespace))
                        iterator.remove();
                }
            }
        }

        if (!ingredients.isEmpty()){ // checks if any of the ingredients where not matched
            // if an item is custom stop the trade, because it means that a custom item that was not matched is custom and should not be used in vanilla recipes
            if (ItemManager.getInstance().isCustomItemStack(inventoryView.getItem(0)) || ItemManager.getInstance().isCustomItemStack(inventoryView.getItem(1)))
                event.setCancelled(true);
        }
    }
}

