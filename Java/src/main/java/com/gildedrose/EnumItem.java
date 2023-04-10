package com.gildedrose;

import java.util.*;

import static com.gildedrose.GildedRoseConstants.*;


enum EnumItem {

    AGED_BRIE(AGED_BRIE_ITEM, 0, 50, 1, false),
    BACKSTAGE(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 0, 50, 1, false),
    SULFURAS(SULFURAS_HAND_OF_RAGNAROS, 80, 80, 0, true),
    OTHER(null, 0, 50, -1, false);

    private final String name;
    private final int minQuality;
    private final int maxQuality;
    private final int value;
    private final boolean isLegendary;


    private EnumItem(String name, int minQuality, int maxQuality, int value, boolean isLegendary) {
        this.name = name;
        this.minQuality = minQuality;
        this.maxQuality = maxQuality;
        this.value = value;
        this.isLegendary = isLegendary;
    }

    /**
     * return EnumItem from name else null
     *
     * @param name
     * @return EnumItem
     */
    public static EnumItem getEnumFromName(String name) {

        return name != null ? Arrays.asList(values()).stream().filter(element ->
                name.equalsIgnoreCase(element.getName()))
            .findFirst()
            .orElse(OTHER)
            : OTHER;

    }

    public String getName() {
        return name;
    }

    public int getMinQuality() {
        return minQuality;
    }

    public int getMaxQuality() {
        return maxQuality;
    }

    public int getValue() {
        return value;
    }

    public boolean isLegendary() {
        return isLegendary;
    }


}
