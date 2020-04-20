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

package com.jordansne.jnodrops;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordansne.jnodrops.command.AdminCommand;
import com.jordansne.jnodrops.event.DropsManager;
import com.jordansne.jnodrops.event.PickupManager;
import com.jordansne.jnodrops.event.PotionsManager;
import com.jordansne.jnodrops.util.ChatHelper;
import com.jordansne.jnodrops.util.ConfigManager;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class JNoDrops extends JavaPlugin {

    private static final int BSTATS_PLUGIN_ID = 7238;

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);

        getServer().getPluginManager().registerEvents(new DropsManager(this), this);
        getServer().getPluginManager().registerEvents(new PickupManager(), this);
        getServer().getPluginManager().registerEvents(new PotionsManager(this), this);

        Objects.requireNonNull(getCommand("jnodrops")).setExecutor(new AdminCommand(this));

        // BStats Metrics
        new Metrics(this, BSTATS_PLUGIN_ID);

        if (getConfig().getBoolean("checkForUpdates")) {
            checkForUpdate();
        }
    }

    private void checkForUpdate()  {
        String currentVersion = getDescription().getVersion();
        String latestVersion = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://api.github.com/repos/jordansne/jnodrops/releases/latest");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                HttpEntity entity  = response.getEntity();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(entity.getContent());
                latestVersion = node.get("name").asText();

                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            getLogger().warning("Unable to check for updates:" + e.getMessage());
        }

        if (latestVersion != null && !latestVersion.equals(currentVersion)) {
            String message1 = "A new version of JNoDrops is available!";
            String message2 = "Current: " + currentVersion + " Latest: " + latestVersion;

            getLogger().info(message1);
            getLogger().info(message2);

            getServer().getPluginManager().registerEvents(new Listener() {
                @EventHandler
                public void onJoin(PlayerJoinEvent event) {
                    Player player = event.getPlayer();

                    if (player.hasPermission(Permission.ADMIN)) {
                        player.sendMessage(ChatHelper.PLUGIN_PREFIX + message1);
                        player.sendMessage(ChatHelper.PLUGIN_PREFIX + message2);
                    }
                }
            }, this);
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}

