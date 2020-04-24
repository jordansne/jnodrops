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
