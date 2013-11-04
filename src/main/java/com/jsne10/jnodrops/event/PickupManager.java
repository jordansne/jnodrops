package com.jsne10.jnodrops.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupManager implements Listener {

	/** Event trigger to block normal item drops. */
	@EventHandler
	public void onBlockDrop(PlayerPickupItemEvent event) {
		if (!event.getPlayer().hasPermission("jnodrops.canpickupitem") &&
				!event.getPlayer().hasPermission("jnodrops.canpickupitem." + event.getPlayer().getWorld().getName())) {
			event.setCancelled(true);
		}
	}

}
