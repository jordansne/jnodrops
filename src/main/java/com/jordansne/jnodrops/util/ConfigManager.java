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

package com.jordansne.jnodrops.util;

import com.jordansne.jnodrops.Config;
import com.jordansne.jnodrops.JNoDrops;

import java.util.logging.Level;

public class ConfigManager {

    private JNoDrops plugin;
    private static final int LATEST_CONFIG_VERSION = 2;

    public ConfigManager(JNoDrops plugin) {
        this.plugin = plugin;
        this.loadConfig();
    }

    private void loadConfig() {
        plugin.saveDefaultConfig();
        checkIfOutdated();
    }

    private void checkIfOutdated() {
        int existingVersion = plugin.getConfig().getInt(Config.VERSION);

        if (existingVersion < LATEST_CONFIG_VERSION) {
            if (existingVersion <= 0) {
                plugin.getLogger().log(Level.SEVERE, "* * * * ERROR: Invalid version found in config file!");
                plugin.getLogger().log(Level.SEVERE, "* * * * Plugin might not work correctly!");
                return;
            }

            switch (existingVersion) {
                case 1:
                    plugin.getLogger().info("Performing configuration update from v1 to v2...");
                    plugin.getConfig().addDefault(Config.SEND_UPDATE_ALERT_TO_ADMINS, true);
                    plugin.getConfig().set(Config.VERSION, 2);
            }

            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
            plugin.getLogger().info("Config update complete!");
        } else if (existingVersion > LATEST_CONFIG_VERSION) {
            plugin.getLogger().log(Level.WARNING, "* * * * WARNING: Found config version that is newer than what the plugin supports!");
            plugin.getLogger().log(Level.WARNING, "* * * * Plugin might not work correctly!");
        }
    }

}
