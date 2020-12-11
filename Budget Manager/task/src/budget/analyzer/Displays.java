package budget.analyzer;

import budget.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static budget.analyzer.Sorting.*;
import static budget.Main.*;

public class Displays {
    public static int sortSelectionDisplay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");

        return scanner.nextInt();
    }
    public static int addPurchaseDisplay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the type of purchase\n" +
                "1) Food \n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) Back");

        return scanner.nextInt();
    }
    public static int displayHome() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose your action: \n" +
                "1) Add income \n" +
                "2) Add purchase \n" +
                "3) Show list of purchases \n" +
                "4) Balance \n" +
                "5) Save \n" +
                "6) Load \n" +
                "7) Analyze (Sort) \n" +
                "0) Exit");

        return scanner.nextInt();
    }
    public static int listPurchasesDisplay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the types of purchases\n" +
                "1) Food \n" +
                "2) Clothes \n" +
                "3) Entertainment \n" +
                "4) Other \n" +
                "5) All \n" +
                "6) Back");

        return scanner.nextInt();
    }
    public static void sortMenuDisplay() {
        while (true) {
            switch(sortSelectionDisplay()) {
                case 1:
                    sortAllPurchases(allPurchases);
                    break;
                case 2:
                    sortByType(allPurchases, foodPurchases, entertainmentPurchases,
                            clothesPurchases, otherPurchases);

                    break;
                case 3:
                    List<Purchase> selected = new ArrayList<>();
                    String category = "";
                    switch(certainTypeDisplay()) {
                        case 1:
                            selected = foodPurchases;
                            category = "Food:";
                            break;
                        case 2:
                            selected = clothesPurchases;
                            category = "Clothes:";
                            break;
                        case 3:
                            selected = entertainmentPurchases;
                            category = "Entertainment:";
                            break;
                        case 4:
                            selected = otherPurchases;
                            category = "Other:";
                            break;
                    }
                    sortCertainType(category, selected);
                    break;
                default:
                    return;
            }
        }
    }
    public static int certainTypeDisplay() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");

        return scanner.nextInt();
    }
}
