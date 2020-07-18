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

import com.jordansne.jnodrops.Config;
import com.jordansne.jnodrops.JNoDrops;
import com.jordansne.jnodrops.Permission;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PotionsManager implements Listener {

    private JNoDrops plugin;

    public PotionsManager(JNoDrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType() == Material.POTION) {
            if (!player.hasPermission(Permission.POTION_USE)) {
                event.setCancelled(true);
                sendDenyMessage(player);

            } else if (!player.hasPermission(Permission.POTION_KEEP_BOTTLE) && player.getGameMode() != GameMode.CREATIVE) {
                player.getServer().getScheduler().runTask(plugin, (task) -> {
                    if (player.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE) {
                        player.getInventory().setItemInMainHand(null);

                    } else if (player.getInventory().getItemInOffHand().getType() == Material.GLASS_BOTTLE) {
                        player.getInventory().setItemInOffHand(null);

                    } else {
                        // If the player tries to exploit the delay of clearing the bottle by swapping to another
                        // item selection, manually find it and delete it from their inventory.
                        ItemStack[] inventoryItems = player.getInventory().getContents();

                        for (ItemStack inventoryItem : inventoryItems) {
                            if (inventoryItem != null && inventoryItem.getType() == Material.GLASS_BOTTLE) {
                                inventoryItem.setAmount(inventoryItem.getAmount() - 1);
                                break;
                            }
                        }
                    }
                });
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.SPLASH_POTION
                && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && !player.hasPermission(Permission.POTION_USE)) {
            event.setCancelled(true);
            sendDenyMessage(player);
        }
    }

    private void sendDenyMessage(Player player) {
        String rawMessage = plugin.getConfig().getString(Config.POTION_DENY_MESSAGE);

        if (rawMessage != null && !rawMessage.equals("")) {
            String message = ChatColor.translateAlternateColorCodes('&', rawMessage);
            player.sendMessage(message);
        }
    }

}

