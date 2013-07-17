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

package com.jsne10.nodrops.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.jsne10.nodrops.Main;

public class PotionDisable implements Listener {

	@EventHandler
	public void onPotionDrop(PlayerInteractEvent event) {

		String message = Main.plugin.getConfig().getString("potionDenyMessage");
		message = ChatColor.translateAlternateColorCodes('&', message);

		if (!event.getPlayer().hasPermission("jnodrops.candroppotion") &&
				!event.getPlayer().hasPermission("jnodrops.candroppotion." + event.getPlayer().getWorld().getName())) {
			try {
				Material mat = event.getItem().getType();

				if ((mat == Material.POTION) || (mat == Material.EXP_BOTTLE)) {
					event.setCancelled(true);
					
					if (!message.equals("")) {
						event.getPlayer().sendMessage(message);				
					}
					
				}					
			} catch (Exception e) {}
		}

	}

}

