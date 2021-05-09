package mediator;

/**
 * Symbol represents a symbol for the company
 */

public enum Symbol {
    IBM("IBM"),APPLE("AAPL"),GOOGLEA("GOOGl"),GOOGLEC("GOOG"),TESLA("TSLA"),FACEBOOK("FB"),PAYPAL("PYPL"),
    MICROSOFT("MSFT"),AMAZON("AMZN");

    private String symbol;

    /**
     * Constructor initialising instance variable
     * @param symbol company symbol
     */

    private Symbol(String symbol){
        this.symbol = symbol;
    }

    /**
     * gets the company's symbol
     * @return symbol
     */

    public String getSymbol() {
        return symbol;
    }
}
