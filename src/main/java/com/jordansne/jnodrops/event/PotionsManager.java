/*
 * Copyright (C) 2013-2020 Jordan Sne.  All rights reserved.
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

package com.jordansne.jnodrops.event;

import com.jordansne.jnodrops.JNoDrops;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PotionsManager implements Listener {

	private JNoDrops plugin;

	public PotionsManager(JNoDrops plugin) {
		this.plugin = plugin;
	}

	/** Event triggered to block uses of potions. */
	@EventHandler
	public void onPotionDrop(PlayerInteractEvent event) {
		String message = plugin.getConfig().getString("potionDenyMessage");
		message = ChatColor.translateAlternateColorCodes('&', message);

		if (!event.getPlayer().hasPermission("jnodrops.canusepotion") &&
				!event.getPlayer().hasPermission("jnodrops.canusepotion." + event.getPlayer().getWorld().getName())) {
			try {
				Material mat = event.getItem().getType();

				if ((mat == Material.POTION) || (mat == Material.EXPERIENCE_BOTTLE)) {
					event.setCancelled(true);

					if (!message.equals("")) {
						event.getPlayer().sendMessage(message);
					}

				}
			} catch (Exception e) {
			}
		}

	}

	/** Event triggered to remove the empty bottle after use. */
	@EventHandler
	public void onPotionDrink(PlayerItemConsumeEvent event) {

		if (!event.getPlayer().hasPermission("jnodrops.savepotionbottle") &&
				!event.getPlayer().hasPermission("jnodrops.savepotionbottle." + event.getPlayer().getWorld().getName())) {
			try {
				Material mat = event.getItem().getType();

				if (mat == Material.POTION) {
					new PotionThread(event.getPlayer()).start();
				}

			} catch (Exception e) {
			}
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
				sleep(100);

				if (player.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE) {
					player.getInventory().setItemInMainHand(null);

				}

			} catch (InterruptedException e) {
			}
		}

	}


}

