package com.jsne10.nodrops.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateAlert implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("jnodrops.admin")) {
			event.getPlayer().sendMessage(ChatColor.RED + "[jNoDrops] " + ChatColor.GRAY +"A new version of jNoDrops is available!");
			event.getPlayer().sendMessage(ChatColor.RED + "[jNoDrops] " + ChatColor.GRAY +"http://dev.bukkit.org/bukkit-plugins/jnodrops/");
		}
	}

}
