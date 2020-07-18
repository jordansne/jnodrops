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

package com.jordansne.jnodrops.util;

import com.jordansne.jnodrops.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminUpdateAlerter implements Listener {

    private String messageLine1;
    private String messageLine2;

    public AdminUpdateAlerter(String messageLine1, String messageLine2) {
        this.messageLine1 = messageLine1;
        this.messageLine2 = messageLine2;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission(Permission.ADMIN)) {
            player.sendMessage(ChatHelper.PLUGIN_PREFIX + messageLine1);
            player.sendMessage(ChatHelper.PLUGIN_PREFIX + messageLine2);
        }
    }

}
