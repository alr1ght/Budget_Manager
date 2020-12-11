package budget.serializing;

import budget.Purchase;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Serial implements Serializable {
    private static final long serialVersionUID = 9L;

    private final Map<String, List<Purchase>> innerMap;

    private final double balance;

    public Serial(Map<String, List<Purchase>> serialize, double balance) {
        this.innerMap = serialize;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, List<Purchase>> getInnerMap() {
        return innerMap;
    }

}
