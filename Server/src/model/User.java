package model;

import mediator.Symbol;

public class User {
    private Balance balance;
    private UserName userName;
    private Password password;
    private Email email;
    private Stocks stocks;

    /**
     *
     *
     * @param userName
     * @param password
     */
    public User(UserName userName, Password password) {
        if (userName == null || password == null)
        {
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
     *
     * @param userName
     * @param password
     * @param passwordConfirm
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
     * @void
     */
    public void BuyStock(Stock stock){

        stocks.addStock(stock);
    }
    /**
     *
     * @void
     */
    public void SellStock(Stock stock){
        stocks.addStock(stock);
    }
    /**
     *
     * @Stocks
     */

    public Stocks getStocks() {
        return stocks;
    }

    /**
     *
     * @return
     */
    public Double getBalance() {
        return balance.getBalance();
    }

    /**
     *
     * @return
     */
    public UserName getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public Password getPassword() {
        return password;
    }

    /**
     *
     * @param amount
     */
    public void addCash(double amount){
        balance.add(amount);
    }

    /**
     *
     * @param amount
     */
    public void withDraw(double amount){
        balance.withDraw(amount);
    }

    public boolean UserOwnStock(Stock stock)
    {
        for(Stock stock1: stocks.getAllStocks())
        {
            if(stock1.equals(stock))
                return true;
        }
        return false;
    }


    public Email getEmail() {
        return email;
    }

    @Override public boolean equals(Object o){
        if (o == null){
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
