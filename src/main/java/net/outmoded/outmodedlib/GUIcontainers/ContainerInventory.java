package net.outmoded.outmodedlib.GUIcontainers;

import net.kyori.adventure.text.Component;
import net.outmoded.outmodedlib.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class ContainerInventory extends CustomContainer{


    public ContainerInventory(String title, @NotNull Integer size, int textureOffset, int[] disabledSlots, String texture) {
        super(title, size, textureOffset, disabledSlots, texture);
    }


    @Override
    public void onClick(InventoryClickEvent event){


    }
}


