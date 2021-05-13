package model;

import mediator.Symbol;

public class User {
    private Balance balance;
    private UserName userName;
    private Password password;
    private Email email;
    private Stocks stocks;

    /**
     * A constructor which is setting instance variables for logging in.
     *
     * @param userName userName of the user
     * @param password password of the user
     */
    public User(UserName userName, Password password) {
        if (userName == null || password == null) {
            throw new IllegalArgumentException("Null username or password");
        }
        this.userName = userName;
        this.password = password;
        this.email = null;
        this.balance = new Balance();
        this.stocks = new Stocks();
        stocks.addStock(new Stock(Symbol.APPLE.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.AMAZON.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.MICROSOFT.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.IBM.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.TESLA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEC.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.FACEBOOK.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.PAYPAL.getSymbol(), userName.getName()));

    }

    /**
     * A constructor which is setting instance variables for registering a user
     *
     * @param userName        wanted userName of the user
     * @param password        wanted password of the user
     * @param passwordConfirm conformation of password
     * @param email           users email
     * @param emailConfirm    conformation of the email
     */
    public User(UserName userName, Password password, Password passwordConfirm, Email email, Email emailConfirm) {
        if (userName == null || password == null || passwordConfirm == null)
        {
            throw new IllegalArgumentException("Null username or password");
        }
        if (!password.equals(passwordConfirm)){
            throw new IllegalArgumentException("Passwords does not match");
        }
        if (!email.toString().equals(emailConfirm.toString())){
            throw new IllegalArgumentException("Emails does not match");
        }
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = new Balance();
        this.stocks = new Stocks();
        stocks.addStock(new Stock(Symbol.APPLE.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.AMAZON.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.MICROSOFT.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.IBM.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.TESLA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEC.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.FACEBOOK.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.PAYPAL.getSymbol(), userName.getName()));
    }

    /**
     *
     *
     * @param userName
     * @param password
     */
    public User(UserName userName, Password password, Email email, int balance) {
        if (userName == null || password == null)
        {
            throw new IllegalArgumentException("Null username or password");
        }
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = new Balance(balance);
        this.stocks = new Stocks();
        stocks.addStock(new Stock(Symbol.APPLE.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.AMAZON.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.MICROSOFT.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.IBM.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.TESLA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEC.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.GOOGLEA.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.FACEBOOK.getSymbol(), userName.getName()));
        stocks.addStock(new Stock(Symbol.PAYPAL.getSymbol(), userName.getName()));

    }

    /**
     * Buys stock
     *
     * @param stock the stock that is being bought
     */
    public void addStock(Stock stock){

        stocks.addStock(stock);
    }
    /**
     * Sells a stock
     *
     * @param stock the stock that is being sold
     */
    public void SellStock(Stock stock) {
        stocks.addStock(stock);
    }
    /**
     * gets the stock
     *
     * @return a stock
     */

    public Stocks getStocks() {
        return stocks;
    }

    /**
     * gets the balance
     *
     * @return a balace
     */
    public Double getBalance() {
        return balance.getBalance();
    }

    /**
     * gets the userName
     *
     * @return userName
     */
    public UserName getUserName() {
        return userName;
    }

    /**
     * gets the password
     *
     * @return password
     */
    public Password getPassword() {
        return password;
    }

    /**
     * adds the amount
     *
     * @param amount amount that is being added
     *
     */
    public void addCash(double amount){
        balance.add(amount);
    }

    /**
     * withdraws the amount
     *
     * @param amount amount that is being withdrawn
     */
    public void withDraw(double amount) {
        balance.withDraw(amount);
    }

    /**
     * goes thro the stocks and checks if the user holds a stock
     *
     * @param stock the stock that is being checked
     * @return returns true if the user holds a stock , else it returns false
     */

    public boolean UserOwnStock(Stock stock) {
        for (Stock stock1 : stocks.getAllStocks()) {
            if (stock1.equals(stock))
                return true;
        }
        return false;
    }

    /**
     * gets a email
     *
     * @return returns an email
     *
     */

    public Email getEmail() {
        return email;
    }

    /**
     *  comparing an object to the user
     * @param o the object that is getting compared with
     * @return returns userName and password
     */

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this){
            return true;
        }
        if (o instanceof User){
            User other = (User) o;
            return userName.equals(other.userName) && password.equals(other.password);
        }
        return false;
    }


}
