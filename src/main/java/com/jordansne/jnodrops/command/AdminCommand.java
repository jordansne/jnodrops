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

package com.jordansne.jnodrops.command;

import com.jordansne.jnodrops.JNoDrops;
import com.jordansne.jnodrops.util.ChatHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class AdminCommand implements CommandExecutor {

    private JNoDrops plugin;

    public AdminCommand(JNoDrops plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatHelper.PLUGIN_PREFIX + "Usage: /jnodrops <reload/about>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatHelper.PLUGIN_PREFIX + "Config Reloaded.");
            return true;
        }

        if (args[0].equalsIgnoreCase("about")) {
            PluginDescriptionFile pluginDescription = plugin.getDescription();
            String name = pluginDescription.getName();
            String version = pluginDescription.getVersion();
            String description = pluginDescription.getDescription();
            String authors = String.join(", ", pluginDescription.getAuthors());

            sender.sendMessage(ChatHelper.PLUGIN_PREFIX + name + " v" + version + " by: " + authors);
            sender.sendMessage(ChatHelper.PLUGIN_PREFIX + description);
            return true;
        }

        sender.sendMessage(ChatHelper.PLUGIN_PREFIX_ERROR + "Usage: /jnodrops <reload/about>");
        return true;
    }

}
