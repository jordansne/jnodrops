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

package com.jsne10.nodrops;

import com.jsne10.nodrops.command.Admin;
import com.jsne10.nodrops.event.*;
import com.jsne10.nodrops.util.Metrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public Main() {
		plugin = this;
	}
	
	@Override
	public void onEnable() {
		
		// Load the config.
		loadConfig();

		// Registers the Drop listener events.
		this.getServer().getPluginManager().registerEvents(new DropsDisable(), this);
		this.getServer().getPluginManager().registerEvents(new DropOnDeathDisable(), this);
		this.getServer().getPluginManager().registerEvents(new PotionDisable(), this);
		
		// Register admin commands.
		this.getCommand("jnodrops").setExecutor(new Admin());
		
		// Plugin Metrics.
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    this.getLogger().info("Successfully hooked to Plugin Metrics.");
		} catch (IOException e) {
		    this.getLogger().warning("Failed to hook to Plugin Metrics");
		}
		
		//Check if a new version of the plugin is available
		if (this.getConfig().getBoolean("checkForUpdates")) {
			this.checkForUpdate();			
		}

	}

	@Override
	public void onDisable() {}
	
	// Called to the load/reload the config.
	public static void loadConfig() {
		
		// Checks to see if an old config is present, if so deletes it for new one.
		File file = new File(plugin.getDataFolder(), "config.yml");
		if (file.exists()) {
			if (!plugin.getConfig().getString("version").equals(plugin.getDescription().getVersion())) {
				file.delete();
			}
		}
		
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
	}
	
	// Called to check for updates. If one is available, then register an alter listener and log it in the console.
	private void checkForUpdate() {
		try {
			URL url = new URL("https://raw.github.com/jsne10/jNoDrops/master/lastestversion");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			if (!reader.readLine().equals(this.getDescription().getVersion())) {
				this.getServer().getPluginManager().registerEvents(new UpdateAlert(), this);
				this.getLogger().info("A new version of jNoDrops is avialable! LINK: http://dev.bukkit.org/bukkit-plugins/jnodrops/");
			}
			
			reader.close();
		} catch (MalformedURLException e) {
			this.getLogger().warning("Unable to check for updates.");
		} catch (IOException e) {
			this.getLogger().warning("Unable to check for updates.");
			e.printStackTrace();
		}
	}
	
}

