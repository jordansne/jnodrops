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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jsne10.jnodrops.util.ChatWrapper;
import com.jsne10.jnodrops.util.ConfigManager;
import com.jsne10.jnodrops.JNoDrops;

public class Admin implements CommandExecutor {
	
	private JNoDrops plugin;
	private ConfigManager config = plugin.getConfigManager();
	private ChatWrapper chat = plugin.getChatWrapper();
	
	public Admin(JNoDrops plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Help or no command
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(chat.getPluginPrefix() + "Usage: /jnodrops <reload/about>");
			return true;
		}
		
		// Reload config command.
		if (args[0].equalsIgnoreCase("reload")) {
			config.reloadConfig();
			sender.sendMessage(chat.getPluginPrefix() + "Config Reloaded.");
			return true;
		}
		
		// Gives a bit of background info about the plugin.
		if (args[0].equalsIgnoreCase("about")) {
			sender.sendMessage(chat.getPluginPrefix() + "Verion: " + plugin.getDescription().getVersion());
			sender.sendMessage(chat.getPluginPrefix() + "A plugin by jsne10. " + plugin.getDescription().getDescription());
			return true;
		}
		
		// If none of the commands are entered.
		sender.sendMessage(chat.getPluginPrefixError() +"Usage: /jnodrops <reload/about>");
		return true;
		
	}

}
