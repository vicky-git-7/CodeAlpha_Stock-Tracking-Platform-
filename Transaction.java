public class Transaction {
    private String stockSymbol;
    private int quantity;
    private double price;
    private String type; // BUY or SELL

    public Transaction(String stockSymbol, int quantity, double price, String type) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getType() { return type; }
}
