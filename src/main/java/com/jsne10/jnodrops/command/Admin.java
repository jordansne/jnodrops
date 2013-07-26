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

package com.jsne10.jnodrops.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jsne10.jnodrops.JNoDrops;
import com.jsne10.jnodrops.util.ConfigManager;

public class Admin implements CommandExecutor {
	
	private JNoDrops plugin = JNoDrops.getPlugin();
	private ConfigManager config = plugin.getConfigManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Help/no command
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"Usage: /jnodrops <reload/about>");
			return true;
		}
		
		// Reload config command.
		if (args[0].equalsIgnoreCase("reload")) {
			config.reloadConfig();
			sender.sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"Config Reloaded.");
			return true;
		}
		
		// Gives a bit of background info about the plugin.
		if (args[0].equalsIgnoreCase("about")) {
			sender.sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"Verion: " + plugin.getDescription().getVersion());
			sender.sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"A plugin by jsne10. " + plugin.getDescription().getDescription());
			return true;
		}
		
		// If none of the commands are entered.
		sender.sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"Usage: /jnodrops <reload/about>");
		return true;
		
	}

}
