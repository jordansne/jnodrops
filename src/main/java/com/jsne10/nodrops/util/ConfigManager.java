/*
 * Copyright (C) 2013 jsne10.  All rights reserved.
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

package com.jsne10.nodrops.util;

import java.io.File;

import com.jsne10.nodrops.JNoDrops;

public class ConfigManager {
	
	private JNoDrops plugin = JNoDrops.getPlugin();
	
	public ConfigManager() {
		this.loadConfig();
	}
	
	/**
	 * Loads the config into memory for settings.
	 */
	public void loadConfig() {
		File config = new File(plugin.getDataFolder(), "config.yml");
		
		if (!config.exists()) {
			plugin.saveDefaultConfig();			
		} else {
			this.checkIfOutdated();
		}
	}
	
	/**
	 * Called to refresh config settings.
	 */
	public void reloadConfig() {
		plugin.reloadConfig();	
	}
	
	/**
	 * Checks if the current version is outdated and if so, updates it.
	 */
	public void checkIfOutdated() {
		if (!plugin.getConfig().getString("version").equals(plugin.getDescription().getVersion())) {
			this.update();
		}
	}
	
	/**
	 * Updates config. WIP
	 */
	private void update() {
		plugin.getConfig().options().copyDefaults();
	}

}
