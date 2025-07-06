package net.outmoded.outmodedlib.items.listener;

import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class VanillaCraftingPrevention implements Listener { // prevents custom items from being used in vanilla crafting recipes

    // this code was a royal pain in the ass to make
    // probably has more bugs

    @EventHandler(priority = EventPriority.LOW)
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {

        ItemStack[] matrixOnTable = event.getInventory().getMatrix();
        Recipe recipe = event.getRecipe();

        if (event.getRecipe() == null && !event.isRepair()){
            return;
        }

        if (event.isRepair()) { // stops custom items from being used in repair events (I.e. when you put 2 iron swords in a crafting grid and get a new sword with more durability)

            ItemStack itemStack1 = matrixOnTable[0];
            ItemStack itemStack2 = matrixOnTable[1];

            String itemStack1NamespacedId = null;
            String itemStack2NamespacedId = null;

            if (ItemManager.getInstance().isCustomItemStack(itemStack1)){
                itemStack1NamespacedId = ItemManager.getInstance().getCustomItemStackNamespaceId(itemStack1);
            }

            if (ItemManager.getInstance().isCustomItemStack(itemStack2)){
                itemStack2NamespacedId = ItemManager.getInstance().getCustomItemStackNamespaceId(itemStack2);
            }


            if (itemStack1NamespacedId != null || itemStack2NamespacedId != null){
                Outmodedlib.getInstance().getLogger().warning("error3 ");
                event.getInventory().setResult(null);
                return;
            }

        }


        int loopSlots = 9; // auto set up for a 3x3 crafting grid
        if (event.getInventory().getType() == InventoryType.CRAFTING){ // if it turns out it's the 2x2 in the players inv set it to 4
            loopSlots = 4;
            Outmodedlib.getInstance().getLogger().warning("2x2 grid");
        }



        if (event.getRecipe() != null && recipe instanceof ShapedRecipe shapedRecipe) {

            if (event.getRecipe() == null){
                return;
            }



            String[] shape = shapedRecipe.getShape();
            Map<Character, RecipeChoice> ingredients = shapedRecipe.getChoiceMap();

            // ############################# converts rows into a list from 0 to 4 or 9
            char[] chars = new char[loopSlots];

            int index = 0;
            for (String row : shape) {
                for (char c : row.toCharArray()) {
                    chars[index] = c;
                    index++;
                }
            }
            // #############################


            for (int i = 0; i < loopSlots; i++) {
                ItemStack itemStack = matrixOnTable[i];

                if (itemStack == null || itemStack.getType() == Material.AIR){
                    continue;
                }

                if (ingredients.get(chars[i]) instanceof RecipeChoice.MaterialChoice) { // checks if it's an instance of RecipeChoice.MaterialChoice
                    if (ItemManager.getInstance().isCustomItemStack(itemStack)) { // checks if the item in the grid is a custom item
                        Outmodedlib.getInstance().getLogger().warning("error2");
                        event.getInventory().setResult(null);
                        return;
                    }

                }
            }
        }

        else if (event.getRecipe() != null && recipe instanceof ShapelessRecipe shapelessRecipe) { // handles shapeless recipes

            ArrayList<RecipeChoice> ingredients = new ArrayList<RecipeChoice>(shapelessRecipe.getChoiceList());

            for (int i = 0; i < loopSlots; i++) {
                ItemStack itemStack = matrixOnTable[i];

                if (itemStack == null || itemStack.getType() == Material.AIR){
                    continue;
                }

                for (Iterator<RecipeChoice> iterator = ingredients.iterator(); iterator.hasNext(); ) {

                    RecipeChoice recipeChoice = iterator.next();

                    if (recipeChoice instanceof RecipeChoice.MaterialChoice) { // checks if it's an instance of RecipeChoice.MaterialChoice, if so stops the use of custom items
                        RecipeChoice.MaterialChoice materialChoice = (RecipeChoice.MaterialChoice) recipeChoice;
                        if (ItemManager.getInstance().isCustomItemStack(itemStack)) {
                            Outmodedlib.getInstance().getLogger().warning("error");
                            event.getInventory().setResult(null);
                            return;

                        }
                        else {
                            iterator.remove();
                        }

                    } else {

                        iterator.remove();

                    }
                }
            }
        }
    }
}

