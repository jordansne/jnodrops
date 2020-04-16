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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropsManager implements Listener {

    private JNoDrops plugin;

    public DropsManager(JNoDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();

        if (!player.hasPermission("jnodrops.candropitem") && !player.hasPermission("jnodrops.candropitem." + world)) {
            if (!player.hasPermission("jnodrops.getitemback") && !player.hasPermission("jnodrops.getitemback." + world)) {
                event.getItemDrop().remove();
            } else {
                event.setCancelled(true);
            }

            sendAlert(player);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String world = player.getWorld().getName();

        if (!player.hasPermission("jnodrops.dropondeath") && !player.hasPermission("jnodrops.dropondeath." + world)) {
            event.getDrops().clear();
        }
    }

    private void sendAlert(Player player) {
        String rawMessage = plugin.getConfig().getString("dropDenyMessage");

        if (rawMessage != null && !rawMessage.equals("")) {
            String message = ChatColor.translateAlternateColorCodes('&', rawMessage);
            player.sendMessage(message);
        }
    }

}
