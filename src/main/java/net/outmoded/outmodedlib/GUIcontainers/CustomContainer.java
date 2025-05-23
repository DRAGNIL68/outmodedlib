package net.outmoded.outmodedlib.GUIcontainers;


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
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public abstract class CustomContainer {


    private String title;
    private String texture;
    private int textureOffset;
    private Inventory inventory;
    private boolean[] disabledSlots = new boolean[54];



    public CustomContainer(String title, @NotNull Integer size, int textureOffset, int[] disabledSlots ,String texture){
        if (size > 54){
            size = 54;
        }

        this.title = title;
        this.texture = texture;
        this.textureOffset = textureOffset;
        inventory = Bukkit.createInventory(null, size, combinedTileAndTexture());
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
            itemMeta.itemName(null);

            itemStack.setItemMeta(itemMeta);
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

    private String combinedTileAndTexture(){
        Integer length = 1;
        if (textureOffset != 0){
            length++;

        }

        String titleOffset = PackerUtils.getNegativeOffsetCharFromInt(length);

        String textureOffset = PackerUtils.getNegativeOffsetCharFromInt(this.textureOffset);


        return textureOffset + texture + titleOffset + title;
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

    public String getTitle() {
        return title;
    }

    public String getTexture() {
        return texture;
    }

    public int getTextureOffset() {
        return textureOffset;
    }

}
