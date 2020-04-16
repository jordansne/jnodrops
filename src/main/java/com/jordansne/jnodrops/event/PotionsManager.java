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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PotionsManager implements Listener {

    private JNoDrops plugin;

    public PotionsManager(JNoDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null) {
            Material material = event.getItem().getType();

            if (material == Material.POTION && !player.hasPermission("jnodrops.canusepotion")
                    && !player.hasPermission("jnodrops.canusepotion." + world)) {
                event.setCancelled(true);

                String rawMessage = plugin.getConfig().getString("potionDenyMessage");
                if (rawMessage != null && !rawMessage.equals("")) {
                    String message = ChatColor.translateAlternateColorCodes('&', rawMessage);
                    event.getPlayer().sendMessage(message);
                }
            }
        }
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        String world = player.getWorld().getName();

        if (!player.hasPermission("jnodrops.savepotionbottle")
                && !player.hasPermission("jnodrops.savepotionbottle." + world)) {
            Material material = event.getItem().getType();

            if (material == Material.POTION) {
                new PotionThread(event.getPlayer()).start();
            }
        }
    }

    private static class PotionThread extends Thread {
        private Player player;

        public PotionThread(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            try {
                sleep(100);

                if (player.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE) {
                    player.getInventory().setItemInMainHand(null);
                }
            } catch (InterruptedException ignored) {}
        }
    }

}

