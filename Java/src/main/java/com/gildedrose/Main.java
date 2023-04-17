package com.gildedrose;

import com.gildedrose.EnumItem;
import com.gildedrose.GildedRose;
import com.gildedrose.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Item[] items = new Item[1];
        Map<Integer, String> options = getAvailableOptions();

        printOptions(options);

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        while (!options.containsKey(choice)) {
            printOptions(options);
            int otherChoice = scanner.nextInt();
            if (options.containsKey(otherChoice)) {
                String name = options.get(otherChoice);

                System.out.println("Please enter the sell In Date for the item  : " + name);
                int sellinDate = scanner.nextInt();

                System.out.println("Please enter the current quality of the item : " + name);
                int quality = scanner.nextInt();

                items[0] = new Item(EnumItem.getEnumFromName(name).getName(), sellinDate, quality);
                new GildedRose(items).updateQuality();
                printResult(items);
            }
        }
        scanner.close();
    }

    private static void printOptions(Map<Integer, String> options) {
        System.out.println("Please select an item from the list : ");
        options.forEach((key, val) -> System.out.println(key + " : " + val));
    }

    private static Map<Integer, String> getAvailableOptions() {
        Map<Integer, String> options = new HashMap<>();
        options.put(1, "Sulfuras, Hand of Ragnaros");
        options.put(2, "Backstage passes to a TAFKAL80ETC concert");
        options.put(3, "Aged Brie");
        options.put(4, "Conjured Mana Cake");
        options.put(5, "Other");
        return options;
    }

    private static void printResult(Item[] items) {
        System.out.println("***********************************");
        System.out.println("Item Name : " + items[0].name);
        System.out.println("Sell in date of item " + items[0].name + " is : " + items[0].sellIn);
        System.out.println("Quality of item " + items[0].name + " is : " + items[0].quality);
        System.out.println("***********************************");
    }
}
