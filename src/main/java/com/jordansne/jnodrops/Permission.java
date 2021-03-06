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

public class Permission {
    private static final String PLUGIN_PREFIX = "jnodrops.";

    public static final String ADMIN              = PLUGIN_PREFIX + "admin";

    public static final String ITEM_DROP          = PLUGIN_PREFIX + "dropitem";
    public static final String ITEM_PICKUP        = PLUGIN_PREFIX + "pickupitem";
    public static final String ITEM_KEEP_DROPPED  = PLUGIN_PREFIX + "keepdroppeditem";
    public static final String ITEM_DROP_ON_DEATH = PLUGIN_PREFIX + "dropinventoryondeath";

    public static final String POTION_USE         = PLUGIN_PREFIX + "usepotion";
    public static final String POTION_KEEP_BOTTLE = PLUGIN_PREFIX + "keeppotionbottle";
}
