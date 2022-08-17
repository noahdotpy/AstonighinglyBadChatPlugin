package me.diligord.astonishinglybadchatplugin.listeners;

import me.diligord.astonishinglybadchatplugin.utils.ChatUtils;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.joinMessage(ChatUtils.makeNewJoinLeaveMessage(
                '+',
                NamedTextColor.GREEN,
                (TextComponent) e.getPlayer().displayName()
            )
        );
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.quitMessage(ChatUtils.makeNewJoinLeaveMessage(
                '-',
                NamedTextColor.RED,
                (TextComponent) e.getPlayer().displayName()
            )
        );
    }
}
