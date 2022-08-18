package me.diligord.astonishinglybadchatplugin.commands;

import me.diligord.astonishinglybadchatplugin.AstonishinglyBadChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SetRankCommand implements CommandExecutor {

    private final AstonishinglyBadChatPlugin plugin;

    public SetRankCommand(AstonishinglyBadChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Must be run as player.");
            return true;
        }
        if (args.length != 2) {
            return false;
        }

        Player targetPlayer = Bukkit.getPlayerExact(args[0]);

        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        UUID targetPlayerUuid = targetPlayer.getUniqueId();
        String selectedRank = args[1];

        String rankName = plugin.getConfig().getString("ranks." + selectedRank + ".basicName");

        if (rankName == null) {
            sender.sendMessage(ChatColor.RED + "Rank not found.");
            return true;
        }

        plugin.getConfig().set("playerRanks." + targetPlayerUuid, rankName);
        plugin.saveConfig();
        return true;
    }
}
