package com.jsne10.nodrops.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DropOnDeathDisable implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (!event.getEntity().hasPermission("jnodrops.dropondeath") && !event.getEntity().hasPermission("jnodrops.dropondeath." + event.getEntity().getWorld().getName())) {
				event.getDrops().clear();
			
		}
	}
	
}
