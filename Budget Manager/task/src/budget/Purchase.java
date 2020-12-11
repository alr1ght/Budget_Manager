package budget;


import java.io.Serializable;

public class Purchase implements Serializable {
    private static final long serialVersionUID = 9L;

    private final String purchaseName;
    private final double price;

    public Purchase(String purchaseName, double price) {
        this.purchaseName = purchaseName;
        this.price = price;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public double getPrice() {
        return price;
    }
}