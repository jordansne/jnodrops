package com.jsne10.jnodrops.util;

import org.bukkit.ChatColor;

public class ChatWrapper {
	
	public String getPluginPrefix() {
		return ChatColor.DARK_GRAY + "[" + ChatColor.RED + "JNoDrops" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
	}
	
	public String getPluginPrefixError() {
		return ChatColor.DARK_GRAY + "[" + ChatColor.RED + "JNoDrops" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_RED;
	}

}
