package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.gildedrose.GildedRoseConstants.*;

class GildedRoseTest {
    public static final String FOO = "foo";

    private static GildedRose initGildedRoseTest(String name, int sellIn, int sellIn1) {
        Item[] items = new Item[]{new Item(name, sellIn, sellIn1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

    @Test
    void testNormalProduct_WhenUpdate_NameShouldRemainTheSame() {
        GildedRose app = initGildedRoseTest(FOO, 0, 0);
        assertEquals(FOO, app.getItems()[0].name);
    }

    @Test
    void testNormalProduct_WhenUpdate_QualityShouldDecreaseByOne() {
        GildedRose app = initGildedRoseTest(FOO, 2, 10);
        assertEquals(9, app.getItems()[0].quality);
    }

    @Test
    void testNormalProduct_WhenSellInPassed_QualityShouldDecreaseTwice() {
        GildedRose app = initGildedRoseTest(FOO, 0, 10);
        assertEquals(8, app.getItems()[0].quality);
    }

    @Test
    void testNormalProduct_WhenUpdate_QualityNeverLessThanMin() {
        GildedRose app = initGildedRoseTest(FOO, 0, 0);
        assertEquals(0, app.getItems()[0].quality);
    }

    @Test
    void testNormalProduct_WhenUpdate_SellInShouldDecreaseByOne() {
        GildedRose app = initGildedRoseTest(FOO, 0, 0);
        assertEquals(-1, app.getItems()[0].sellIn);
    }

    @Test
    void testAgedBrie_WhenUpdate_ShouldIncreaseQualityByOne() {
        GildedRose app = initGildedRoseTest(AGED_BRIE_ITEM, 5, 10);
        assertEquals(4, app.getItems()[0].sellIn);
        assertEquals(11, app.getItems()[0].quality);
    }

    @Test
    void testAgedBrie_WhenUpdate_QualityShouldNeverBeMoreThanMax() {
        GildedRose app = initGildedRoseTest(AGED_BRIE_ITEM, 5, 50);
        assertEquals(4, app.getItems()[0].sellIn);
        assertEquals(50, app.getItems()[0].quality);
    }

    @Test
    void testLegendarySulfuras_WhenUpdate_ShouldNeverBeSold() {
        GildedRose app = initGildedRoseTest(SULFURAS_HAND_OF_RAGNAROS, 5, 80);
        assertEquals(5, app.getItems()[0].sellIn);
    }

    @Test
    void testLegendarySulfuras_WhenUpdate_QualityNeverDecrease() {
        GildedRose app = initGildedRoseTest(SULFURAS_HAND_OF_RAGNAROS, 5, 80);
        assertEquals(80, app.getItems()[0].quality);
    }

    @Test
    void testBackstagePasses_WhenSellInApproaches_IncreaseQualityByOne() {
        GildedRose app = initGildedRoseTest(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 15, 10);
        assertEquals(14, app.getItems()[0].sellIn);
        assertEquals(11, app.getItems()[0].quality);
    }

    @Test
    void testBackstagePasses_WhenSellInLessThanTen_IncreaseQualityByTwo() {
        GildedRose app = initGildedRoseTest(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 10, 15);
        assertEquals(9, app.getItems()[0].sellIn);
        assertEquals(17, app.getItems()[0].quality);
    }

    @Test
    void testBackstagePasses_WhenSellInLessThanFive_IncreaseQualityByThree() {
        GildedRose app = initGildedRoseTest(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 5, 10);
        assertEquals(4, app.getItems()[0].sellIn);
        assertEquals(13, app.getItems()[0].quality);
    }

    @Test
    void testBackstagePasses_WhenSellInZero_QualityDropToZero() {
        GildedRose app = initGildedRoseTest(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 0, 10);
        assertEquals(-1, app.getItems()[0].sellIn);
        assertEquals(0, app.getItems()[0].quality);
    }

}
