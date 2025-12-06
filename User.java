public class User {
    private String name;
    private double balance;
    private Portfolio portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new Portfolio();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public Portfolio getPortfolio() { return portfolio; }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean buyStock(Stock stock, int qty) {
        double cost = stock.getPrice() * qty;
        if (cost > balance) {
            return false;
        }
        balance -= cost;
        portfolio.addTransaction(new Transaction(stock.getSymbol(), qty, stock.getPrice(), "BUY"));
        return true;
    }

    public boolean sellStock(Stock stock, int qty) {
        int available = portfolio.getStockQuantity(stock.getSymbol());
        if (qty > available) {
            return false;
        }
        balance += stock.getPrice() * qty;
        portfolio.addTransaction(new Transaction(stock.getSymbol(), qty, stock.getPrice(), "SELL"));
        return true;
    }
}
