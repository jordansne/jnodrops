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

import com.jsne10.nodrops.listeners.*;
import com.jsne10.nodrops.util.Metrics;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class NoDrops extends JavaPlugin {
	
	public static NoDrops plugin;
	
	public NoDrops() {
		plugin = this;
	}

	@Override
	public void onLoad() {
	}
	
	@Override
	public void onEnable() {
		
		// Saves config file if not present.
		this.saveDefaultConfig();

		// Registers the Drop listener events.
		this.getServer().getPluginManager().registerEvents(new DropsDisable(), this);
		this.getServer().getPluginManager().registerEvents(new DropOnDeathDisable(), this);
		
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

}

