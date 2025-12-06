import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public int getStockQuantity(String symbol) {
        int total = 0;
        for (Transaction t : transactions) {
            if (t.getStockSymbol().equals(symbol)) {
                if (t.getType().equals("BUY"))
                    total += t.getQuantity();
                else
                    total -= t.getQuantity();
            }
        }
        return total;
    }

    public double getPortfolioValue(ArrayList<Stock> stocks) {
        double totalValue = 0;
        for (Stock s : stocks) {
            int qty = getStockQuantity(s.getSymbol());
            totalValue += qty * s.getPrice();
        }
        return totalValue;
    }

    public void showPortfolio(ArrayList<Stock> stocks) {
        System.out.println("\n------ PORTFOLIO ------");
        for (Stock s : stocks) {
            int qty = getStockQuantity(s.getSymbol());
            if (qty > 0) {
                System.out.println(s.getSymbol() + " | Qty: " + qty + " | Current Price: " + s.getPrice());
            }
        }
        System.out.println("------------------------");
    }
}
