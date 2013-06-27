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
import com.jsne10.nodrops.listeners.*;
import com.jsne10.nodrops.util.Metrics;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	public Main() {
		plugin = this;
	}
	
	@Override
	public void onEnable() {
		
		//Checks to see if an old config is present, if so deletes it for new one.
		File file = new File(this.getDataFolder(), "config.yml");
		if (file.exists()) {
			if (this.getConfig().getDouble("version") != Double.parseDouble(this.getDescription().getVersion())) {
				file.delete();
			}
		}
		
		// Saves config file if not present.
		this.saveDefaultConfig();

		// Registers the Drop listener events.
		this.getServer().getPluginManager().registerEvents(new DropsDisable(), this);
		this.getServer().getPluginManager().registerEvents(new DropOnDeathDisable(), this);
		this.getServer().getPluginManager().registerEvents(new PotionDisable(), this);
		
		// Admin commands.
		this.getCommand("jnodrops").setExecutor(new Admin());
		
		// Plugin Metrics.
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    this.getLogger().info("Successfully hooked to Plugin Metrics.");
		} catch (IOException e) {
		    this.getLogger().warning("Failed to hook to Plugin Metrics");
		}

	}

	@Override
	public void onDisable() {}
	
	public static void loadConfig(Plugin pl) {
		plugin.reloadConfig();
	}

}

