package mediator;

public enum Symbol {
    IBM("IBM"),APPLE("AAPL"),GOOGLEA("GOOGl"),GOOGLEC("GOOG"),TESLA("TSLA"),FACEBOOK("FB"),PAYPAL("PYPL"),
    MICROSOFT("MSFT"),AMAZON("AMZN");

    private String symbol;

    private Symbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
