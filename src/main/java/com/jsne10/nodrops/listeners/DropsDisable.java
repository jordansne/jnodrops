package com.jsne10.nodrops.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropsDisable implements Listener {

	@EventHandler
	public void onBlockDrop(PlayerDropItemEvent event) {
		if (!event.getPlayer().hasPermission("jnodrops.candrop")) {
			
			if (!event.getPlayer().hasPermission("jnodrops.candrop." + event.getPlayer().getWorld().getName())) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "[NoDrops] " + ChatColor.GRAY + "You must not share items!");
				return;
			} else {
				return;
			}
			
		}
	}
	
}
