package budget;

import budget.serializing.Serial;
import budget.serializing.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static budget.analyzer.Displays.*;

public class Main {

    private static final String fileName = "purchases.txt";

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        File file = new File(String.valueOf(Paths.get(fileName)));

        if (!file.exists()) {
            try {
                boolean ignored = file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (true) {
            switch (displayHome()) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    listOfPurchases();
                    break;
                case 4:
                    balance();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    sortMenuDisplay();
                    break;
                default:
                    exit();
                    return;
            }
        }

    }

    private static double balance;

    public static List<Purchase> allPurchases = new ArrayList<>();
    public static List<Purchase> foodPurchases = new ArrayList<>();
    public static List<Purchase> clothesPurchases = new ArrayList<>();
    public static List<Purchase> entertainmentPurchases = new ArrayList<>();
    public static List<Purchase> otherPurchases = new ArrayList<>();

    public static void addIncome() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter income: ");
        balance += scanner.nextFloat();

        System.out.println("Income was added!\n");
    }

    public static void addPurchase() {
        List<Purchase> selectedList = new ArrayList<>();

        int selection;

        while (true) {

            selection = addPurchaseDisplay();

            switch (selection) {
                case 1:
                    selectedList = foodPurchases;
                    break;
                case 2:
                    selectedList = clothesPurchases;
                    break;
                case 3:
                    selectedList = entertainmentPurchases;
                    break;
                case 4:
                    selectedList = otherPurchases;
                    break;
                case 5:
                    return;
            }

            Scanner scanner = new Scanner(System.in);

            System.out.println("\nEnter purchase name: ");
            String product = scanner.nextLine();

            System.out.println("Enter its price: ");
            double price = scanner.nextDouble();

            System.out.println("Purchase was added!\n");

            Purchase current = new Purchase(product, price);

            selectedList.add(current);
            allPurchases.add(current);

            switch (selection) {
                case 1:
                    foodPurchases = selectedList;
                    break;
                case 2:
                    clothesPurchases = selectedList;
                    break;
                case 3:
                    entertainmentPurchases = selectedList;
                    break;
                case 4:
                    otherPurchases = selectedList;
                    break;
            }
        }
    }

    public static void listOfPurchases() {

        while (true) {
            List<Purchase> displayList;

            if (allPurchases.isEmpty()) {
                System.out.println("\nPurchase list is empty\n");
                return;
            }

            String listType;

            switch (listPurchasesDisplay()) {
                case 1:
                    displayList = foodPurchases;
                    listType = "Food: ";
                    break;
                case 2:
                    displayList = clothesPurchases;
                    listType = "Clothes: ";
                    break;
                case 3:
                    displayList = entertainmentPurchases;
                    listType = "Entertainment: ";
                    break;
                case 4:
                    displayList = otherPurchases;
                    listType = "Other: ";
                    break;
                case 5:
                    displayList = allPurchases;
                    listType = "All: ";
                    break;
                default:
                    return;
            }

            System.out.println("\n" + listType);

            displayList.forEach(p -> System.out.print(p.getPurchaseName() + " $" + p.getPrice() + "0\n"));
            System.out.println("Total sum: $" + displayList
                    .stream()
                    .mapToDouble(Purchase::getPrice)
                    .sum() + "\n");
        }
    }

    public static void balance() {
        double negative = allPurchases
                .stream()
                .mapToDouble(Purchase::getPrice)
                .sum();

        balance -= negative;

        System.out.println("\nBalance: $" + Math.round(balance * 100.0) / 100.0 + "0\n");
    }

    public static void save() {
        try {
            Map<String, List<Purchase>> serial = new TreeMap<>();
            serial.put("all", allPurchases);
            serial.put("food", foodPurchases);
            serial.put("clothes", clothesPurchases);
            serial.put("entertainment", entertainmentPurchases);
            serial.put("other", otherPurchases);

            Serial map = new Serial(serial, balance);

            SerializationUtils.serialize(map, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nPurchases were saved!");
    }
    
    public static void load() {
        try {
            Serial deserialized = (Serial) SerializationUtils.deserialize(fileName);
            allPurchases = deserialized.getInnerMap().get("all");
            foodPurchases = deserialized.getInnerMap().get("food");
            clothesPurchases = deserialized.getInnerMap().get("clothes");
            entertainmentPurchases = deserialized.getInnerMap().get("entertainment");
            otherPurchases = deserialized.getInnerMap().get("other");

            balance = deserialized.getBalance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nPurchases were loaded!");
    }

    public static void exit() {
        System.out.println("\nBye!\n");
    }
}
