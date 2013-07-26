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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.jsne10.jnodrops.JNoDrops;

public class DropsDisable implements Listener {
	
	private JNoDrops plugin = JNoDrops.getPlugin();

	@EventHandler
	/** Event trigger to block normal item drops. */
	public void onBlockDrop(PlayerDropItemEvent event) {
		
		if (!event.getPlayer().hasPermission("jnodrops.candropitem") &&
				!event.getPlayer().hasPermission("jnodrops.candropitem." + event.getPlayer().getWorld().getName())) {
			
			// If the player shouldn't get item back, then clear the drop stack.
			if (!event.getPlayer().hasPermission("jnodrops.getitemback") &&
					!event.getPlayer().hasPermission("jnodrops.getitemback." + event.getPlayer().getWorld().getName())) {
				event.getItemDrop().remove();
			} else {
				event.setCancelled(true);				
			}
			
			this.sendAlert(event.getPlayer());
		}
		
	}
	
	/** Sends the chosen message (from config) to the player. */
	public void sendAlert(Player player) {
		String message;
		
		// Get the message and colourize it.
		message = plugin.getConfig().getString("dropDenyMessage");
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		if (!message.equals("")) {
			player.sendMessage(message);				
		}
	}
	
}
