package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final int SELLIN_DATE_ZERO = 0;
    public static final int SELLIN_DATE_FIVE = 5;
    public static final int SELLIN_DATE_TEN = 10;
    public static final int QUALITY_STEP_THREE = 3;
    public static final int QUALITY_STEP_TWO = 2;
    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.asList(items).forEach(item -> {
            EnumItem enumItem = EnumItem.getEnumFromName(item.name);
            int qualityValue = enumItem.getValue();
            int maxQuality = enumItem.getMaxQuality();
            int minQuality = enumItem.getMinQuality();

            if (EnumItem.BACKSTAGE.equals(enumItem)) {
                if (item.sellIn <= SELLIN_DATE_ZERO) {
                    maxQuality = SELLIN_DATE_ZERO; //Quality drops to 0 after the concert
                } else if (item.sellIn <= SELLIN_DATE_FIVE) {
                    qualityValue = QUALITY_STEP_THREE; //Quality increases by 3 when there are 5 days or less
                } else if (item.sellIn <= SELLIN_DATE_TEN) {
                    qualityValue = QUALITY_STEP_TWO; //Quality increases by 2 when there are 10 days or less
                }

            } else if (EnumItem.OTHER.equals(enumItem) && item.sellIn <= SELLIN_DATE_ZERO) {
                qualityValue = -QUALITY_STEP_TWO; //Once the sell by date has passed, Quality degrades twice as fast
            }

            if (!enumItem.isLegendary()) {
                --item.sellIn;
            }
            updateQualityValue(item, qualityValue, minQuality, maxQuality);

        });
    }


    private void updateQualityValue(Item item, int value, int minQuality, int maxQuality) {
        if (item.quality + value < minQuality) {
            item.quality = minQuality; //maxQuality should not be less than the min 0
        } else if (item.quality + value > maxQuality) {
            item.quality = maxQuality; //maxQuality should not exceed the max 50
        } else if (item.quality > minQuality && item.quality < maxQuality) {
            item.quality = item.quality + value;
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
