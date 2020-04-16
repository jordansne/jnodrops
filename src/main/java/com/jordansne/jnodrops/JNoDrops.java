/*
 * Copyright (C) 2013-2020 Jordan Sne.  All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation,  version 3.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.jordansne.jnodrops;

import com.jordansne.jnodrops.command.AdminCommand;
import com.jordansne.jnodrops.event.DropsManager;
import com.jordansne.jnodrops.event.PickupManager;
import com.jordansne.jnodrops.event.PotionsManager;
import com.jordansne.jnodrops.util.ChatHelper;
import com.jordansne.jnodrops.util.ConfigManager;
import com.jordansne.jnodrops.util.Metrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JNoDrops extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);

        getServer().getPluginManager().registerEvents(new DropsManager(this), this);
        getServer().getPluginManager().registerEvents(new PickupManager(), this);
        getServer().getPluginManager().registerEvents(new PotionsManager(this), this);

        getCommand("jnodrops").setExecutor(new AdminCommand(this));

        // Plugin Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();

            getLogger().info("Successfully hooked to Plugin Metrics.");
        } catch (IOException e) {
            getLogger().warning("Failed to hook to Plugin Metrics");
        }

        try {
            checkForUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForUpdate() throws IOException {
        if (!getConfig().getBoolean("checkForUpdates")) {
            return;
        }

        BufferedReader reader = null;

        try {
            URL url = new URL("https://raw.github.com/jordansne/JNoDrops/master/lastestversion");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            if (!reader.readLine().equals(getDescription().getVersion())) {
                getServer().getPluginManager().registerEvents(new Listener() {
                    @EventHandler
                    public void onJoin(PlayerJoinEvent event) {
                        if (event.getPlayer().hasPermission("jnodrops.admin")) {
                            event.getPlayer().sendMessage(ChatHelper.PLUGIN_PREFIX +
                                    "A new version of JNoDrops is available!");
                            event.getPlayer().sendMessage(ChatHelper.PLUGIN_PREFIX +
                                    "http://dev.bukkit.org/bukkit-plugins/jnodrops/");
                        }

                    }
                }, this);

                getLogger().info("A new version of JNoDrops is available! LINK: " +
                        "http://dev.bukkit.org/bukkit-plugins/jnodrops/");
            }
        } catch (IOException e) {
            getLogger().warning("Unable to check for updates.");
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}

