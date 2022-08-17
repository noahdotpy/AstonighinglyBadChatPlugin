package me.diligord.astonishinglybadchatplugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ChatUtils {
    public static TextComponent makeNewJoinLeaveMessage(Character prefix, NamedTextColor color, TextComponent playerDisplayName) {
        return Component.text(prefix+" ")
                .color(color)
                .decoration(TextDecoration.BOLD, true)
            .append(playerDisplayName);
    }
}
