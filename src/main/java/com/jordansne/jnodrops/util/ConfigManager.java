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

public class ConfigManager {

    private JNoDrops plugin;
    private static final int CONFIG_VERSION = 1;

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

        if (existingVersion != CONFIG_VERSION) {
            // When the day comes where there's a config update:
            // switch (existingVersion) {
            // 	case 1:
            // 		// Perform 1 to 2 update.. etc..
            // 	case 2:
            // 		// Perform 2 to 3 update.. etc..
            // }
        }
    }

}
