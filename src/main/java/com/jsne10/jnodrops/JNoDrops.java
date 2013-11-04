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

package com.jsne10.jnodrops;

import com.jsne10.jnodrops.util.ChatWrapper;
import com.jsne10.jnodrops.util.ConfigManager;
import com.jsne10.jnodrops.command.Admin;
import com.jsne10.jnodrops.event.*;
import com.jsne10.jnodrops.util.Metrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.plugin.java.JavaPlugin;

public class JNoDrops extends JavaPlugin {

	private ConfigManager configManager;
	private ChatWrapper chatWrapper;
	
	@Override
	public void onEnable() {
		// Initialize object.
		configManager = new ConfigManager(this);
		chatWrapper = new ChatWrapper();

		// Registers the plugin events.
		getServer().getPluginManager().registerEvents(new DropsManager(this), this);
		getServer().getPluginManager().registerEvents(new PickupManager(), this);
		getServer().getPluginManager().registerEvents(new PotionsManager(this), this);
		
		// Register admin command.
		getCommand("jnodrops").setExecutor(new Admin(this));
		
		// Plugin Metrics.
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    getLogger().info("Successfully hooked to Plugin Metrics.");
		} catch (IOException e) {
		    getLogger().warning("Failed to hook to Plugin Metrics");
		}
		
		// Check for updates.
		checkForUpdate();
	}

	@Override
	public void onDisable() {}
	
	/** Called to check for updates. If one is available, then register an alter listener and log it in the console. */
	private void checkForUpdate() {
		if (getConfig().getBoolean("checkForUpdates")) {
			try {
				URL url = new URL("https://raw.github.com/jsne10/jNoDrops/master/lastestversion");
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

				if (!reader.readLine().equals(getDescription().getVersion())) {
					getServer().getPluginManager().registerEvents(new UpdateAlert(this), this);
					getLogger().info("A new version of JNoDrops is avialable! LINK: http://dev.bukkit.org/bukkit-plugins/jnodrops/");
				}

				reader.close();
			} catch (MalformedURLException e) {
				getLogger().warning("Unable to check for updates.");
			} catch (IOException e) {
				getLogger().warning("Unable to check for updates.");
			}
		}
	}
 
	/** Config Manager instance getter. */
	public ConfigManager getConfigManager() {
		return configManager;
	}
	
	/** Chat Wrapper instance getter. */
	public ChatWrapper getChatWrapper() {
		return chatWrapper;
	}
	
}

