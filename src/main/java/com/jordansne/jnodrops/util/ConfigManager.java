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

import java.io.File;

import com.jordansne.jnodrops.JNoDrops;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConfigManager {

	private JNoDrops plugin;
	private static String CONFIG_VERSION = "1";

	public ConfigManager(JNoDrops plugin) {
		this.plugin = plugin;

		this.loadConfig();
	}

	/** Loads the config into memory for settings. */
	public void loadConfig() {
		File config = new File(plugin.getDataFolder(), "config.yml");

		if (!config.exists()) {
			plugin.saveDefaultConfig();
		} else {
			this.checkIfOutdated();
		}
	}

	/** Called to refresh config settings. */
	public void reloadConfig() {
		this.loadConfig();
		plugin.reloadConfig();
	}

	/** Checks if the current version is outdated and if so, updates it. */
	public void checkIfOutdated() {
		// TODO Check Github Releases
		if (!plugin.getConfig().getString("version").equals(CONFIG_VERSION)) {
			plugin.getServer().getPluginManager().registerEvents(new Listener() {
				@EventHandler
				public void onJoin(PlayerJoinEvent event) {
					if (event.getPlayer().hasPermission("jnodrops.admin")) {
						event.getPlayer().sendMessage(plugin.getChatWrapper().getPluginPrefix() + "Erase your old config to allow new one to regenerate!");
						event.getPlayer().sendMessage(plugin.getChatWrapper().getPluginPrefix() + "(Save your old config to save old settings!)");
					}
				}
			}, plugin);
		}
	}

}
