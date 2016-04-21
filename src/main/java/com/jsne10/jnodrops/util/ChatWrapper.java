/*
 * Copyright (C) 2013-2016 jsne10.  All rights reserved.
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
