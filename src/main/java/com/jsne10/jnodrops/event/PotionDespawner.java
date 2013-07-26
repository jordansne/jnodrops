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

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PotionDespawner implements Listener {
	
	@EventHandler
	/** Event triggered to remove the empty bottles after use. */
	public void onPotionDrop(PlayerItemConsumeEvent event) {

		if (!event.getPlayer().hasPermission("jnodrops.savePotionBottle") &&
				!event.getPlayer().hasPermission("jnodrops.savePotionBottle." + event.getPlayer().getWorld().getName())) {
			try {
				Material mat = event.getItem().getType();

				if (mat == Material.POTION) {
					new PotionThread(event.getPlayer()).start();
				}
				
			} catch (Exception e) {}
		}

	}
	
	private class PotionThread extends Thread {
		
		private Player player;
		
		public PotionThread(Player player) {
			super();
			this.player = player;
		}
		
		@Override
		public void run() {
			try {
				sleep(300);
				
				if (player.getItemInHand().getType() == Material.GLASS_BOTTLE) {
					player.setItemInHand(null);
				}
				
			} catch (InterruptedException e) {}
		}
		
	}
}
