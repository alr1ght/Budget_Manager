package budget.analyzer;

import budget.Purchase;

import java.text.DecimalFormat;
import java.util.*;

public class Sorting {

    private static final DecimalFormat df = new DecimalFormat(".00");
    public static void sortAllPurchases(List<Purchase> all) {

        if (all.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
            return;
        }

        Map<String, Double> allMap = new LinkedHashMap<>();

        for (var elem : all) {
            allMap.put(elem.getPurchaseName(), elem.getPrice());
        }

        System.out.println("\nAll:");

        allMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " $" + df.format(entry.getValue())));

    }
    public static void sortByType(List<Purchase> all, List<Purchase> food,
                                  List<Purchase> entertainment, List<Purchase> clothes,
                                  List<Purchase> other) {

        Map<String, Double> sortMap = new TreeMap<>();

        double foodTotal = food.stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        sortMap.put("Food", foodTotal);

        double clothesTotal = clothes.stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        sortMap.put("Clothes", clothesTotal);

        double entertainmentTotal = entertainment.stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        sortMap.put("Entertainment", entertainmentTotal);

        double otherTotal = other.stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        sortMap.put("Other", otherTotal);

        System.out.println("\nTypes:");

        sortMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " - $" + df.format(entry.getValue())));

        double totalSum = all.stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        System.out.println("Total sum: $" + df.format(totalSum));
    }
    public static void sortCertainType(String category, List<Purchase> list) {

        if (list.isEmpty()) {
            System.out.println("\nPurchase list is empty!");
            return;
        }

        Map<String, Double> sortMap = new TreeMap<>();

        for (var elem : list) {
            sortMap.put(elem.getPurchaseName(), elem.getPrice());
        }

        System.out.println("\n" + category);

        sortMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " $" + df.format(entry.getValue())));

        System.out.println("Total sum: $" + df.format(list.stream().mapToDouble(Purchase::getPrice).sum()));
    }
}
