package me.diligord.astonishinglybadchatplugin.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e) {
        // Don't do anything for cancelled events
        if (e.isCancelled()) return;


        TextComponent message = (TextComponent) e.message();

        TextComponent newMessage = Component.text("MEMBER ").color(NamedTextColor.DARK_GRAY);
        newMessage = newMessage.append(e.getPlayer().displayName().color(NamedTextColor.DARK_BLUE));
        newMessage = newMessage.append(Component.text(": ")).color(NamedTextColor.DARK_BLUE);

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
