package me.diligord.astonishinglybadchatplugin.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.diligord.astonishinglybadchatplugin.AstonishinglyBadChatPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatListener implements Listener {

    private final AstonishinglyBadChatPlugin plugin;

    public PlayerChatListener(AstonishinglyBadChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(AsyncChatEvent e) {

        TextComponent message = (TextComponent) e.message();

        String playerRank = plugin.getConfig().getString("playerRanks." + e.getPlayer().getUniqueId());
        String formattedPlayerRank = plugin.getConfig().getString("ranks." + playerRank + ".formattedName");

        // TODO: Allow changing text decoration of the rank, bold, italics, etc..
        assert formattedPlayerRank != null;
        TextComponent newMessage = LegacyComponentSerializer.legacy('&')
                .deserialize(formattedPlayerRank);
        newMessage = newMessage.append(e.getPlayer().displayName());
        newMessage = newMessage.append(Component.text(":"));
        newMessage = newMessage.append(Component.text(" "));

        if (e.getPlayer().hasPermission("astonishinglyBadChatPlugin.coloredMessages")) {
            if (Component.text(ChatColor.translateAlternateColorCodes('&', message.content())).hasStyling()) {
                newMessage = newMessage.append(Component.text(
                        ChatColor.translateAlternateColorCodes('&', message.content())
                    )
                );
            } else {
                newMessage = newMessage.append(Component.text(
                        ChatColor.translateAlternateColorCodes('&', message.content())
                    ).color(NamedTextColor.WHITE)
                );
            }
        } else {
            newMessage = newMessage.append(
                Component.text(message.content()).color(NamedTextColor.WHITE)
            );
        }

        TextComponent finalNewMessage = newMessage;
        e.viewers().forEach(
                viewer -> viewer.sendMessage(finalNewMessage)
        );

        e.setCancelled(true);

    }
}
