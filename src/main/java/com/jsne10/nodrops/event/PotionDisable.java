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
					event.getPlayer().sendMessage(message);
				}					
			} catch (Exception e) {}
		}

	}

}

