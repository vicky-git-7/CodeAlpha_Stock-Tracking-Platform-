import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("AAPL", 150));
        market.add(new Stock("GOOG", 2800));
        market.add(new Stock("TSLA", 700));
        market.add(new Stock("AMZN", 3400));

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        User user = new User(name, 10000); // initial balance

        int choice = 0;

        while (true) {
            System.out.println("\n====== STOCK TRADING PLATFORM ======");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Show Portfolio Value");
            System.out.println("6. Update Market Prices");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            // ---- FIXED INPUT HANDLING ----
            String input = sc.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number from 1 to 7.");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock s : market) {
                        System.out.println(s.getSymbol() + " | Price: $" + s.getPrice());
                    }
                    break;

                case 2:
                    // BUY STOCK
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    String qtyInput = sc.nextLine();
                    int qty;

                    try {
                        qty = Integer.parseInt(qtyInput);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Quantity must be a valid number!");
                        break;
                    }

                    Stock stockToBuy = findStock(market, buySymbol);

                    if (stockToBuy != null) {
                        if (user.buyStock(stockToBuy, qty))
                            System.out.println("✔ BUY Successful!");
                        else
                            System.out.println("❌ Not enough balance!");
                    } else {
                        System.out.println("❌ Stock Not Found!");
                    }
                    break;

                case 3:
                    // SELL STOCK
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    String sellQtyInput = sc.nextLine();
                    int sellQty;

                    try {
                        sellQty = Integer.parseInt(sellQtyInput);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Quantity must be a valid number!");
                        break;
                    }

                    Stock stockToSell = findStock(market, sellSymbol);

                    if (stockToSell != null) {
                        if (user.sellStock(stockToSell, sellQty))
                            System.out.println("✔ SELL Successful!");
                        else
                            System.out.println("❌ You don't have enough stock to sell!");
                    } else {
                        System.out.println("❌ Stock Not Found!");
                    }
                    break;

                case 4:
                    user.getPortfolio().showPortfolio(market);
                    System.out.println("Available Balance: $" + user.getBalance());
                    break;

                case 5:
                    double value = user.getPortfolio().getPortfolioValue(market);
                    System.out.println("\nPortfolio Value: $" + value);
                    System.out.println("Total Wealth (Balance + Portfolio): $" + (value + user.getBalance()));
                    break;

                case 6:
                    System.out.println("\n--- Updating Prices Randomly ---");
                    for (Stock s : market) {
                        double newPrice = s.getPrice() + (Math.random() * 50 - 25);
                        if (newPrice < 1) newPrice = 1;
                        s.updatePrice(newPrice);
                    }
                    System.out.println("✔ Prices Updated!");
                    break;

                case 7:
                    System.out.println("Exiting the platform...");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice! Enter a number between 1 and 7.");
            }
        }
    }

    public static Stock findStock(ArrayList<Stock> market, String symbol) {
        for (Stock s : market) {
            if (s.getSymbol().equalsIgnoreCase(symbol)) {
                return s;
            }
        }
        return null;
    }
}
