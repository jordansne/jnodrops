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

package com.jsne10.jnodrops.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.jsne10.jnodrops.JNoDrops;
import com.jsne10.jnodrops.util.ChatWrapper;

public class ConfigAlert implements Listener {
	
	private ChatWrapper chat;
	
	public ConfigAlert(JNoDrops plugin) {
		chat = plugin.getChatWrapper();
	}
	
	/** Event triggered when an admin joins to alert them to update their config file. */
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("jnodrops.admin")) {
			event.getPlayer().sendMessage(chat.getPluginPrefix() + "Erase your old config to allow new one to regenerate!");
			event.getPlayer().sendMessage(chat.getPluginPrefix() + "(Save your old config to save old settings!)");
		}
	}
	
}
