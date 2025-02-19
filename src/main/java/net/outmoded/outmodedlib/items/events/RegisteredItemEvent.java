package net.outmoded.outmodedlib.items.events;

import net.outmoded.outmodedlib.items.CustomItemStack;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public final class RegisteredItemEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final String namespaceId;
    private final CustomItemStack customItemStack;

    public RegisteredItemEvent(String namespaceId, CustomItemStack customItemStack){
        this.namespaceId = namespaceId;
        this.customItemStack = customItemStack;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public CustomItemStack getCustomItemStack() {
        return customItemStack;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

}


