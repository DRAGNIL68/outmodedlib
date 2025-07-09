package net.outmoded.outmodedlib.GUIcontainers;


import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.outmoded.outmodedlib.Outmodedlib;
import net.outmoded.outmodedlib.items.ItemManager;
import net.outmoded.outmodedlib.packer.PackerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class CustomContainer {


    public final Component title;
    public final Inventory inventory;
    public boolean[] disabledSlots = new boolean[54];


    /**
     * To change title create a new gui (prevets dupes and bugs),
     * <p>
     * If you open a new gui without closing the last one the player was in the players cursor does
     * not get reset to the middle of the screen
     *
     */
    public CustomContainer(Component title, @NotNull Integer size, int[] disabledSlots){
        if (size > 54){
            size = 54;
        }

        this.title = title;
        inventory = Bukkit.createInventory(null, size, title);

        ContainerManager.getInstance().registerHandledContainer(inventory, this);

        for (int i = 0; i < inventory.getSize(); i++) {
            this.disabledSlots[i] = false;
        }

        if (disabledSlots == null){
            return;
        }
        else{

            ItemStack itemStack = new ItemStack(Material.PAPER);
            NamespacedKey modelKey = new NamespacedKey("minecraft", "air");
            ItemMeta meta = itemStack.getItemMeta();
            meta.setItemModel(modelKey);
            itemStack.setItemMeta(meta);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemStack.setItemMeta(itemMeta);

            itemStack.setData(DataComponentTypes.HIDE_TOOLTIP);


            for (int slot : disabledSlots) {
                if (slot <= inventory.getSize()){
                    this.disabledSlots[slot] = true;

                    inventory.setItem(slot, itemStack); // TODO: replace with one setStorageContents call
                }
                else {



                    //Outmodedlib.getInstance().getLogger().getLogger.Info("tried to disable a nonexistent slot " + slot + " maximum allowed slot is " + inventory.getSize());

                }

            }
        }

    }

    public void openInventory(Player player){
        player.openInventory(inventory);
    }

    public void setDisabledSlots(int[] disabledSlots) {

        ItemStack itemStack = new ItemStack(Material.PAPER);
        NamespacedKey modelKey = new NamespacedKey("minecraft", "air");
        ItemMeta meta = itemStack.getItemMeta();
        meta.setItemModel(modelKey);
        itemStack.setItemMeta(meta);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.itemName(null);

        itemStack.setItemMeta(itemMeta);
        for (int slot : disabledSlots) {
            if (slot <= inventory.getSize()) {
                this.disabledSlots[slot] = true;

                inventory.setItem(slot, itemStack); // TODO: replace with one setStorageContents call
            } else {


                //Outmodedlib.getInstance().getLogger().getLogger.Info("tried to disable a nonexistent slot " + slot + " maximum allowed slot is " + inventory.getSize());

            }
        }
    }


    public @NotNull Inventory getInventory(){
        return inventory;
    };

    public void onClick(InventoryClickEvent event){};

    public void onOpen(InventoryOpenEvent event){};

    public void onClose(InventoryCloseEvent event){};

    public void onDrag(InventoryDragEvent event){};


    boolean[] getDisabledSlots(){
        return disabledSlots;
    }

    public Component getTitle() {
        return title;
    }

}
