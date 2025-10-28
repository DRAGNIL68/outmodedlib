package net.outmoded.outmodedlib.commands;

import net.outmoded.outmodedlib.config.HostingConfig;
import net.outmoded.outmodedlib.packer.ResourcePackServer.ResourcePackManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OutmodedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {

            if (!sender.hasPermission("outmodedlib")) {
                sender.sendMessage("You Cannon Use This Command");
                return true;
            }

            if (Objects.equals(args[0], "reload")) {
                HostingConfig.load();
                sender.sendMessage("[outmodedlib] Reloaded!");
                return true;
            }
            else if (Objects.equals(args[0], "apply-packs")) {
                List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());

                for (Player player : list){
                    ResourcePackManager.getInstance().applyAllResourcePacksToPlayer(player);

                }


                sender.sendMessage("[outmodedlib] Applied Resource Packs");
                return true;

            }


        }


        return true;
    }
}
