package me.diligord.astonishinglybadchatplugin;

import me.diligord.astonishinglybadchatplugin.listeners.PlayerChatListener;
import me.diligord.astonishinglybadchatplugin.listeners.PlayerJoinLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AstonishinglyBadChatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config file stuff
        saveDefaultConfig();

        // Plugin startup logic
        PluginManager plMan = Bukkit.getPluginManager();

        plMan.registerEvents(new PlayerChatListener(), this);
        plMan.registerEvents(new PlayerJoinLeaveListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
