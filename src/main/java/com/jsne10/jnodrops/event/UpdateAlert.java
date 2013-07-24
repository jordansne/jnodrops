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

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateAlert implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("jnodrops.admin")) {
			event.getPlayer().sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"A new version of jNoDrops is available!");
			event.getPlayer().sendMessage(ChatColor.RED + "[JNoDrops] " + ChatColor.GRAY +"http://dev.bukkit.org/bukkit-plugins/jnodrops/");
		}
	}

}
